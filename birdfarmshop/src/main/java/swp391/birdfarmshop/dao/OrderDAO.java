/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dao;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import swp391.birdfarmshop.dto.CartDTO;
import swp391.birdfarmshop.dto.OrderItemDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdCustomer;
import swp391.birdfarmshop.model.Order;
import swp391.birdfarmshop.model.OrderItem;
import swp391.birdfarmshop.model.OrderedAccessoryItem;
import swp391.birdfarmshop.model.OrderedBirdItem;
import swp391.birdfarmshop.model.OrderedBirdPairItem;
import swp391.birdfarmshop.util.DBUtils;
import swp391.birdfarmshop.util.VNPAYUtils;

/**
 *
 * @author tlminh
 */
public class OrderDAO {

    public String error = null;

    public int createNewOrder(String order_id, String username, String status, String name_receiver, String phone_receiver,
            String address_receiver, String payment_status, CartDTO cart, String reason, String payment_type, String vnp_TxnRef, String vnp_date, String vnp_transaction) {
        int result = 0;
        Connection con = null;
        OrderItemDAO oid = new OrderItemDAO();
        AccessoryDAO ad = new AccessoryDAO();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String insertOrder = "INSERT INTO [ORDER]([order_id]\n"
                        + ",[customer]\n"
                        + ",[order_date]\n"
                        + ",[order_status]\n"
                        + ",[name_receiver]\n"
                        + ",[phone_receiver]\n"
                        + ",[address_receiver]\n"
                        + ",[payment_status]\n"
                        + ",[payment_type]\n"
                        + ",[total_price]\n"
                        + ",[cancel_reason]\n"
                        + ",[vnp_txnRef]\n"
                        + ",[vnp_date]\n"
                        + ",[vnp_transaction])\n"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(insertOrder);
                pst.setString(1, order_id);
                pst.setString(2, username);
                LocalDateTime customDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                String sqlDate = customDateTime.format(formatter);
                pst.setString(3, sqlDate);
                pst.setString(4, status);
                pst.setString(5, name_receiver);
                pst.setString(6, phone_receiver);
                pst.setString(7, address_receiver);
                pst.setString(8, payment_status);
                pst.setString(9, payment_type);
                pst.setInt(10, cart.getCartTotalPrice());
                pst.setString(11, reason);
                pst.setString(12, vnp_TxnRef);
                pst.setString(13, vnp_date);
                pst.setString(14, vnp_transaction);
                result = pst.executeUpdate();
                boolean checkBird = true;
                boolean checkAccessory = true;
                boolean checkBirdPair = true;
                if (result > 0) {
                    HashMap<String, OrderedBirdItem> birdList = (HashMap<String, OrderedBirdItem>) cart.getBirdList();
                    for (OrderedBirdItem ob : birdList.values()) {
                        Bird b = ob.getBird();
                        int realPrice = b.getPrice();
                        if (b.getDiscount() > 0) {
                            realPrice = b.getPrice() - b.getPrice() * b.getDiscount() / 100;
                        }
                        String checkOrderBird = "SELECT [status]\n" +
                                "FROM [BirdFarmShop].[dbo].[Bird] WHERE [bird_id] = ? AND [status] <> N'Còn hàng'";
                        pst = con.prepareStatement(checkOrderBird);
                        pst.setString(1, b.getBird_id());
                        rs = pst.executeQuery();
                        if (rs != null && rs.next()) {
                            error = b.getBird_name() + " đã bán";
                            checkBird = false;
                            break;
                        } else {
                            String updateBird = "UPDATE [Bird]\n"
                                    + "SET [status] = N'Hết hàng'\n"
                                    + "WHERE [bird_id] = ?";
                            pst = con.prepareStatement(updateBird);
                            pst.setString(1, b.getBird_id());
                            result = pst.executeUpdate();
                            if (result == 0) {
                                checkBird = false;
                                break;
                            } else {
                                String insertBird = "INSERT INTO [OrderItem]([order_id],[bird_id],\n"
                                        + "		  [unit_price],[order_quantity])\n"
                                        + "   VALUES(?,?,?,?)";
                                pst = con.prepareStatement(insertBird);
                                pst.setString(1, order_id);
                                pst.setString(2, b.getBird_id());
                                pst.setInt(3, realPrice);
                                pst.setInt(4, 1);
                                result = pst.executeUpdate();
                                if (result == 0) {
                                    checkBird = false;
                                    break;
                                }
                            }
                        }
                    }
                    HashMap<String, OrderedAccessoryItem> accessoryList = (HashMap<String, OrderedAccessoryItem>) cart.getAccessoryList();
                    for (OrderedAccessoryItem oa : accessoryList.values()) {
                        Accessory a = oa.getAccessory();
                        int realPrice = a.getUnit_price();
                        if (a.getDiscount() > 0) {
                            realPrice = a.getUnit_price() - a.getUnit_price() * a.getDiscount() / 100;
                        }
                        int quantity = oa.getOrder_quantity();
                        if (oa.getFree_order() > 0) {
                            quantity = oa.getFree_order() + oa.getOrder_quantity();
                        }
                        String getStockAccessory = "SELECT [stock_quantity]\n"
                                + "FROM [BirdFarmShop].[dbo].[Accessory]\n"
                                + "WHERE [accessory_id] = ?";
                        pst = con.prepareStatement(getStockAccessory);
                        pst.setString(1, a.getAccessory_id());
                        rs = pst.executeQuery();
                        if (rs != null && rs.next()) {
                            int numberAccessory = rs.getInt("stock_quantity");
                            if (numberAccessory == 0) {
                                error = a.getAccessory_name() + " đã hết hàng";
                                checkAccessory = false;
                                break;
                            } else if (numberAccessory < quantity) {
                                error = a.getAccessory_name() + " không đủ số lượng trong kho";
                                checkAccessory = false;
                                break;
                            } else {
                                int newStock = numberAccessory - quantity;
                                String updateQuantity = "UPDATE [Accessory]\n"
                                        + "SET [stock_quantity] = ?\n"
                                        + "WHERE [accessory_id] = ?";
                                pst = con.prepareStatement(updateQuantity);
                                pst.setInt(1, newStock);
                                pst.setString(2, a.getAccessory_id());
                                result = pst.executeUpdate();
                                if (result == 0) {
                                    checkAccessory = false;
                                    break;
                                } else {
                                    String insertAccessory = "INSERT INTO [OrderItem]([order_id],\n"
                                            + "		  [accessory_id],[unit_price],[order_quantity])\n"
                                            + "               VALUES(?,?,?,?)";
                                    pst = con.prepareStatement(insertAccessory);
                                    pst.setString(1, order_id);
                                    pst.setString(2, a.getAccessory_id());
                                    pst.setInt(3, realPrice);
                                    pst.setInt(4, quantity - oa.getFree_order());
                                    result = pst.executeUpdate();
                                    if (result == 0) {
                                        checkAccessory = false;
                                        break;
                                    }
                                    if (oa.getFree_order() > 0) {
                                        insertAccessory = "INSERT INTO [OrderItem]([order_id],\n"
                                                + "		  [accessory_id],[unit_price],[order_quantity])\n"
                                                + "               VALUES(?,?,?,?)";
                                        pst = con.prepareStatement(insertAccessory);
                                        pst.setString(1, order_id);
                                        pst.setString(2, a.getAccessory_id());
                                        pst.setInt(3, 0);
                                        pst.setInt(4, oa.getFree_order());
                                        result = pst.executeUpdate();
                                        if (result == 0) {
                                            checkAccessory = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            error = "Không tìm thấy sản phẩm";
                            break;
                        }
                    }
                    HashMap<String, OrderedBirdPairItem> birdPairList = (HashMap<String, OrderedBirdPairItem>) cart.getBirdPairList();
                    if (birdPairList != null) {
                        for (OrderedBirdPairItem opi : birdPairList.values()) {
                            Bird male = opi.getBirdMale();
                            Bird female = opi.getBirdFemale();
                            BirdCustomer customer = opi.getBirdCustomer();
                            Bird birdShop = opi.getBirdShop();
                            if (customer != null && birdShop != null) {
                                String checkBirdShop = "SELECT *\n"
                                        + "FROM [BirdFarmShop].[dbo].[Bird]\n"
                                        + "WHERE [bird_id] = ? AND [status] = N'Còn hàng'";
                                pst = con.prepareStatement(checkBirdShop);
                                pst.setString(1, birdShop.getBird_id());
                                rs = pst.executeQuery();
                                if (rs != null && rs.next()) {
                                    String checkBirdCustomer = "SELECT *\n"
                                            + "FROM [BirdFarmShop].[dbo].[CustomerBird]\n"
                                            + "WHERE [bird_id] = ? AND [status] = N'Chưa ghép cặp'";
                                    pst = con.prepareStatement(checkBirdCustomer);
                                    pst.setInt(1, customer.getBird_id());
                                    rs = pst.executeQuery();
                                    if (rs != null && rs.next()) {
                                        String insertBirdPair = "INSERT INTO [BirdPair]([order_id],[young_bird_price],[bird_customer],[male_bird_id],[female_bird_id],[status])\n"
                                                + "VALUES(?,?,?,?,?,?)";
                                        pst = con.prepareStatement(insertBirdPair);
                                        pst.setString(1, order_id);
                                        int realPrice = birdShop.getPrice() * 20 / 100;
                                        pst.setInt(2, realPrice);
                                        pst.setInt(3, customer.getBird_id());
                                        if (birdShop.isGender()) {
                                            pst.setString(4, birdShop.getBird_id());
                                            pst.setString(5, null);
                                        } else {
                                            pst.setString(4, null);
                                            pst.setString(5, birdShop.getBird_id());
                                        }
                                        pst.setString(6, "Chờ lấy chim");
                                        result = pst.executeUpdate();
                                        if (result == 0) {
                                            error = "Sản phẩm này không thể ghép cặp";
                                            checkBirdPair = false;
                                            break;
                                        } else {
                                            String updateBirdCustomer = "UPDATE [CustomerBird]\n"
                                                    + "SET [status] = N'Đang ghép cặp'\n"
                                                    + "WHERE bird_id = ?";
                                            pst = con.prepareStatement(updateBirdCustomer);
                                            pst.setInt(1, customer.getBird_id());
                                            result = pst.executeUpdate();
                                            if (result == 0) {
                                                checkBirdPair = false;
                                                break;
                                            }
                                            String updateBirdShop = "UPDATE [Bird]\n"
                                                    + "SET [status] = N'Đang sinh sản'\n"
                                                    + "WHERE bird_id = ?";
                                            pst = con.prepareStatement(updateBirdShop);
                                            pst.setString(1, birdShop.getBird_id());
                                            result = pst.executeUpdate();
                                            if (result == 0) {
                                                checkBirdPair = false;
                                                break;
                                            }
                                            String selectBirdPair = "SELECT TOP 1 [pair_id]\n"
                                                    + "FROM [BirdFarmShop].[dbo].[BirdPair]\n"
                                                    + "WHERE [order_id] = ?";
                                            pst = con.prepareStatement(selectBirdPair);
                                            pst.setString(1, order_id);
                                            rs = pst.executeQuery();
                                            if (rs != null && rs.next()) {
                                                int pair_id = rs.getInt("pair_id");
                                                String insertTracking = "INSERT INTO [PairTracking]([pair_id],[content],[date_content])\n"
                                                        + "VALUES(?,?,?)";
                                                pst = con.prepareStatement(insertTracking);
                                                pst.setInt(1, pair_id);
                                                pst.setString(2, "Chờ khách hàng giao chim để ghép cặp");
                                                pst.setString(3, sqlDate);
                                                if (result == 0) {
                                                    checkBirdPair = false;
                                                    break;
                                                }
                                                String insertBirdPairOrderItem = "INSERT INTO [OrderItem]([order_id],[pair_id],\n"
                                                        + "		  [unit_price],[order_quantity])\n"
                                                        + "   VALUES(?,?,?,?)";
                                                pst = con.prepareStatement(insertBirdPairOrderItem);
                                                pst.setString(1, order_id);
                                                pst.setInt(2, pair_id);
                                                pst.setInt(3, 2000000);
                                                pst.setInt(4, 1);
                                                result = pst.executeUpdate();
                                                if (result == 0) {
                                                    checkBirdPair = false;
                                                    break;
                                                }
                                            } else {
                                                checkBirdPair = false;
                                                break;
                                            }
                                        }
                                    } else {
                                        error = customer.getName() + " không thể ghép cặp";
                                        checkBirdPair = false;
                                        break;
                                    }
                                } else {
                                    error = birdShop.getBird_name() + " không thể ghép cặp";
                                    checkBirdPair = false;
                                    break;
                                }
                            }
                            if (male != null && female != null) {
                                String checkBirdMale = "SELECT *\n"
                                        + "FROM [BirdFarmShop].[dbo].[Bird]\n"
                                        + "WHERE [bird_id] = ? AND [status] = N'Còn hàng'";
                                pst = con.prepareStatement(checkBirdMale);
                                pst.setString(1, male.getBird_id());
                                rs = pst.executeQuery();
                                if (rs != null && rs.next()) {
                                    String checkBirdFemale = "SELECT *\n"
                                            + "FROM [BirdFarmShop].[dbo].[Bird]\n"
                                            + "WHERE [bird_id] = ? AND [status] = N'Còn hàng'";
                                    pst = con.prepareStatement(checkBirdFemale);
                                    pst.setString(1, female.getBird_id());
                                    rs = pst.executeQuery();
                                    if (rs != null && rs.next()) {
                                        String insertBirdPair = "INSERT INTO [BirdPair]([order_id],[young_bird_price],[male_bird_id],[female_bird_id],[status])\n"
                                                + "VALUES(?,?,?,?,?)";
                                        pst = con.prepareStatement(insertBirdPair);
                                        pst.setString(1, order_id);
                                        long malePrice = (long) male.getPrice() * 20 / 100;
                                        long femalePrice = (long) female.getPrice() * 20 / 100;
                                        pst.setInt(2, Math.round((int) (malePrice + femalePrice) / 100000) * 100000);
                                        pst.setString(3, male.getBird_id());
                                        pst.setString(4, female.getBird_id());
                                        pst.setString(5, "Chờ lấy chim");
                                        result = pst.executeUpdate();
                                        if (result == 0) {
                                            break;
                                        } else {
                                            String updateBirdMale = "UPDATE [Bird]\n"
                                                    + "SET [status] = N'Đang sinh sản'\n"
                                                    + "WHERE bird_id = ?";
                                            pst = con.prepareStatement(updateBirdMale);
                                            pst.setString(1, male.getBird_id());
                                            result = pst.executeUpdate();
                                            if (result == 0) {
                                                error = "Sản phẩm này không thể ghép cặp";
                                                checkBirdPair = false;
                                                break;
                                            }
                                            String updateBirdFemale = "UPDATE [Bird]\n"
                                                    + "SET [status] = N'Đang sinh sản'\n"
                                                    + "WHERE bird_id = ?";
                                            pst = con.prepareStatement(updateBirdFemale);
                                            pst.setString(1, female.getBird_id());
                                            result = pst.executeUpdate();
                                            if (result == 0) {
                                                checkBirdPair = false;
                                                break;
                                            }
                                            String selectBirdPair = "SELECT TOP 1 [pair_id]\n"
                                                    + "FROM [BirdFarmShop].[dbo].[BirdPair]\n"
                                                    + "WHERE [order_id] = ?";
                                            pst = con.prepareStatement(selectBirdPair);
                                            pst.setString(1, order_id);
                                            rs = pst.executeQuery();
                                            if (rs != null && rs.next()) {
                                                int pair_id = rs.getInt("pair_id");
                                                String insertTracking = "INSERT INTO [PairTracking]([pair_id],[content],[date_content])\n"
                                                        + "VALUES(?,?,?)";
                                                pst = con.prepareStatement(insertTracking);
                                                pst.setInt(1, pair_id);
                                                pst.setString(2, "Đang chuẩn bị chim ghép cặp");
                                                pst.setString(3, sqlDate);
                                                result = pst.executeUpdate();
                                                if (result == 0) {
                                                    checkBirdPair = false;
                                                    break;
                                                }
                                                String insertBirdPairOrderItem = "INSERT INTO [OrderItem]([order_id],[pair_id],\n"
                                                        + "		  [unit_price],[order_quantity])\n"
                                                        + "   VALUES(?,?,?,?)";
                                                pst = con.prepareStatement(insertBirdPairOrderItem);
                                                pst.setString(1, order_id);
                                                pst.setInt(2, pair_id);
                                                pst.setInt(3, 2000000);
                                                pst.setInt(4, 1);
                                                result = pst.executeUpdate();
                                                if (result == 0) {
                                                    checkBirdPair = false;
                                                    break;
                                                }
                                            } else {
                                                checkBirdPair = false;
                                                break;
                                            }
                                        }
                                    } else {
                                        error = female.getBird_name() + " không thể ghép cặp";
                                        checkBirdPair = false;
                                        break;
                                    }
                                } else {
                                    error = male.getBird_name() + " không thể ghép cặp";
                                    checkBirdPair = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (checkBird && checkAccessory && checkBirdPair) {
                    result = 1;
                    con.commit();
                } else {
                    result = 0;
                    con.rollback();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public Order getOrderLatest() {
        Order o = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 [order_id]\n"
                        + "      ,[customer]\n"
                        + "      ,[order_date]\n"
                        + "      ,[order_status]\n"
                        + "      ,[name_receiver]\n"
                        + "      ,[phone_receiver]\n"
                        + "      ,[address_receiver]\n"
                        + "      ,[payment_status]\n"
                        + "      ,[total_price]\n"
                        + "      ,[cancel_reason]\n"
                        + "  FROM [BirdFarmShop].[dbo].[Order]\n"
                        + "  ORDER BY order_date DESC";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs != null && rs.next()) {
                    String order_id = rs.getString("order_id");
                    String customer = rs.getString("customer");
                    Date order_date = rs.getDate("order_date");
                    String order_status = rs.getString("order_status");
                    String name_receiver = rs.getString("name_receiver");
                    int phone_receiver = rs.getInt("phone_receiver");
                    String address_receiver = rs.getString("address_receiver");
                    String payment_status = rs.getString("payment_status");
                    int total_price = rs.getInt("total_price");
                    String cancel_reason = rs.getString("cancel_reason");
                    o = new Order(order_id, customer, order_date, order_status, name_receiver, phone_receiver, address_receiver, payment_status, total_price, cancel_reason);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }

    public OrderItem getOrderByBirdId(String bird_id) {
        OrderItem oi = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [order_item_id]\n"
                        + "      ,[order_id]\n"
                        + "      ,[bird_id]\n"
                        + "      ,[nest_id]\n"
                        + "      ,[accessory_id]\n"
                        + "      ,[unit_price]\n"
                        + "      ,[order_quantity]\n"
                        + "  FROM [BirdFarmShop].[dbo].[OrderItem]\n"
                        + "  WHERE [bird_id] = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, bird_id);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int order_item_id = rs.getInt("order_item_id");
                    String order_id = rs.getString("order_id");
                    String nest_id = rs.getString("nest_id");
                    String accessory_id = rs.getString("accessory_id");
                    int unit_price = rs.getInt("unit_price");
                    int order_quantity = rs.getInt("order_quantity");
                    oi = new OrderItem(order_item_id, order_id, bird_id, nest_id, accessory_id, unit_price, order_quantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return oi;
    }

    public ArrayList<Order> getAllOfOrder(String date, String startDay, String endDay,
            String status, String search, String page, int recordsPerPage) throws SQLException {
        ArrayList<Order> orderList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Order order;
        LocalDate today = LocalDate.now();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT [order_id],[customer],[order_date],[order_status],[name_receiver],\n"
                        + "[phone_receiver],[address_receiver],[payment_status],[total_price],[cancel_reason]\n"
                        + "FROM [BirdFarmShop].[dbo].[Order] WHERE 1 = 1 ";
                if (date != null && !date.isEmpty()) {
                    switch (date) {
                        case "today":
                            query += "AND [order_date] = '" + today + "'";
                            break;
                        case "yesterday":
                            LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
                            query += "AND [order_date] = '" + yesterday + "'";
                            break;
                        case "thisWeek":
                            LocalDate firstDayOfWeek = today.minusDays(today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
                            LocalDate lastDayOfWeek = firstDayOfWeek.plusDays(6);
                            query += "AND ([order_date] >= '" + firstDayOfWeek + "' AND [order_date] <= '" + lastDayOfWeek + "')";
                            break;
                        case "thisMonth":
                            LocalDate firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);
                            LocalDate lastDayOfMonth = YearMonth.from(today).atEndOfMonth();
                            query += "AND ([order_date] >= '" + firstDayOfMonth + "' AND [order_date] <= '" + lastDayOfMonth + "')";
                            break;
                        case "thisYear":
                            LocalDate firstDayOfYear = LocalDate.of(today.getYear(), 1, 1);
                            LocalDate lastDayOfYear = LocalDate.of(today.getYear(), 12, 31);
                            query += "AND ([order_date] >= '" + firstDayOfYear + "' AND [order_date] <= '" + lastDayOfYear + "')";
                            break;
                    }
                }
                if (startDay != null && endDay != null) {
                    if (!startDay.isEmpty() && !endDay.isEmpty()) {
                        query += "AND ([order_date] >= '" + startDay + "' AND [order_date] <= '" + endDay + "')";
                    } else {
                        if (!startDay.isEmpty()) {
                            query += "AND ([order_date] >= '" + startDay + "')";
                        }
                        if (!endDay.isEmpty()) {
                            query += "AND ([order_date] <= '" + endDay + "')";
                        }
                    }
                } else {
                    if (startDay != null) {
                        if (!startDay.isEmpty()) {
                            query += "AND ([order_date] >= '" + startDay + "')";
                        }
                    }
                    if (endDay != null) {
                        if (!endDay.isEmpty()) {
                            query += "AND ([order_date] <= '" + endDay + "')";
                        }
                    }
                }
                if (status != null && !status.isEmpty()) {
                    switch (status) {
                        case "wait":
                            query += "AND ([order_status] = N'Chờ xử lý')";
                            break;
                        case "inProgress":
                            query += "AND ([order_status] = N'Đang xử lý')";
                            break;
                        case "delivering":
                            query += "AND ([order_status] = N'Đang giao hàng')";
                            break;
                        case "delivered":
                            query += "AND ([order_status] = N'Đã giao hàng')";
                            break;
                        case "rated":
                            query += "AND ([order_status] = N'Đã đánh giá')";
                            break;
                        case "cancel":
                            query += "AND ([order_status] = N'Đã hủy')";
                            break;
                    }
                }
                if (search != null && !search.isEmpty()) {
                    query += "AND ([order_id] LIKE '%" + search + "%' OR [customer] LIKE '%" + search + "%')";
                }
                if (page != null) {
                    int pageNumber = Integer.parseInt(page);
                    int start = (pageNumber - 1) * recordsPerPage;
                    query += "ORDER BY [order_date] DESC OFFSET " + start + " ROWS FETCH NEXT " + recordsPerPage + " ROWS ONLY";
                }
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String order_id = rs.getString("order_id");
                    String customer = rs.getString("customer");
                    Date order_date = rs.getDate("order_date");
                    String order_status = rs.getString("order_status");
                    String name_receiver = rs.getString("name_receiver");
                    int phone_receiver = rs.getInt("phone_receiver");
                    String address_receiver = rs.getString("address_receiver");
                    String payment_status = rs.getString("payment_status");
                    int total_price = rs.getInt("total_price");
                    String cancel_reason = rs.getString("cancel_reason");
                    order = new Order(order_id, customer, order_date, order_status, name_receiver,
                            phone_receiver, address_receiver, payment_status, total_price, cancel_reason);
                    orderList.add(order);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return orderList;
    }

    public int numberOfOrder(String date, String startDay, String endDay,
            String status, String search) throws SQLException {
        int total = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        LocalDate today = LocalDate.now();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT COUNT([order_id]) AS numberOfOrder\n"
                        + "FROM [BirdFarmShop].[dbo].[Order] WHERE 1 = 1 ";
                if (date != null) {
                    switch (date) {
                        case "today":
                            query += "AND [order_date] = '" + today + "'";
                            break;
                        case "yesterday":
                            LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
                            query += "AND [order_date] = '" + yesterday + "'";
                            break;
                        case "thisWeek":
                            LocalDate firstDayOfWeek = today.minusDays(today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
                            LocalDate lastDayOfWeek = firstDayOfWeek.plusDays(6);
                            query += "AND ([order_date] >= '" + firstDayOfWeek + "' AND [order_date] <= '" + lastDayOfWeek + "')";
                            break;
                        case "thisMonth":
                            LocalDate firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonth(), 1);
                            LocalDate lastDayOfMonth = YearMonth.from(today).atEndOfMonth();
                            query += "AND ([order_date] >= '" + firstDayOfMonth + "' AND [order_date] <= '" + lastDayOfMonth + "')";
                            break;
                        case "thisYear":
                            LocalDate firstDayOfYear = LocalDate.of(today.getYear(), 1, 1);
                            LocalDate lastDayOfYear = LocalDate.of(today.getYear(), 12, 31);
                            query += "AND ([order_date] >= '" + firstDayOfYear + "' AND [order_date] <= '" + lastDayOfYear + "')";
                            break;
                    }
                }
                if (startDay != null && endDay != null) {
                    if (!startDay.isEmpty() && !endDay.isEmpty()) {
                        String dateFormatPattern = "\\d{4}-\\d{2}-\\d{2}";
                        if (startDay.matches(dateFormatPattern) && endDay.matches(dateFormatPattern)) {
                            query += "AND ([order_date] >= '" + startDay + "' AND [order_date] <= '" + endDay + "')";
                        } else {
                            if (startDay.matches(dateFormatPattern)) {
                                query += "AND ([order_date] >= '" + startDay + "')";
                            }
                            if (endDay.matches(dateFormatPattern)) {
                                query += "AND ([order_date] <= '" + endDay + "')";
                            }
                        }
                    } else {
                        if (!startDay.isEmpty()) {
                            query += "AND ([order_date] >= '" + startDay + "')";
                        }
                        if (!endDay.isEmpty()) {
                            query += "AND ([order_date] <= '" + endDay + "')";
                        }
                    }
                }
                if (status != null && !status.isEmpty()) {
                    query += "AND ([order_status] = '" + status + "')";
                }
                if (search != null && !search.isEmpty()) {
                    query += "AND ([order_id] LIKE '%" + search + "%' OR [customer] LIKE '%" + search + "%')";
                }
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    total = rs.getInt("numberOfOrder");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return total;
    }

    public Order getOrderById(String order_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Order order = new Order();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT [customer],[order_date],[order_status],[name_receiver],\n"
                        + "[phone_receiver],[address_receiver],[payment_status],[total_price],[cancel_reason]"
                        + ",[vnp_txnRef],[vnp_date],[vnp_transaction],[payment_type]\n"
                        + "FROM [BirdFarmShop].[dbo].[Order] WHERE [order_id] = ?";
                stm = con.prepareStatement(query);
                stm.setString(1, order_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String customer = rs.getString("customer");
                    Date order_date = rs.getDate("order_date");
                    String order_status = rs.getString("order_status");
                    String name_receiver = rs.getString("name_receiver");
                    int phone_receiver = rs.getInt("phone_receiver");
                    String address_receiver = rs.getString("address_receiver");
                    String payment_status = rs.getString("payment_status");
                    int total_price = rs.getInt("total_price");
                    String cancel_reason = rs.getString("cancel_reason");
                    String vnpTxnref = rs.getString("vnp_TxnRef");
                    String vnpDate = rs.getString("vnp_date");
                    String vnpTransaction = rs.getString("vnp_transaction");
                    String paymentType = rs.getString("payment_type");
                    order = new Order(order_id, customer, order_date, order_status, name_receiver,
                            phone_receiver, address_receiver, payment_status, total_price, cancel_reason, vnpTxnref, vnpDate, vnpTransaction, paymentType);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return order;
    }

    public ArrayList<String> getOrderStatus() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<String> statuses = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT DISTINCT [order_status]\n"
                        + "  FROM [BirdFarmShop].[dbo].[Order]";
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String status = rs.getString("order_status");
                    statuses.add(status);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return statuses;
    }

    public boolean updateOrderStatus(String order_id, String reason, String status, HttpServletRequest r) throws SQLException, ParseException, IOException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean isUpdated = false;
        OrderItemDAO itemDao = new OrderItemDAO();
        BirdDAO birdDao = new BirdDAO();
        AccessoryDAO accessoryDao = new AccessoryDAO();
        BirdNestDAO nestDao = new BirdNestDAO();
        OrderDAO orderDao = new OrderDAO();
        BirdCustomerDAO birdCustomerDao = new BirdCustomerDAO();
        TrackingBirdPairDAO trackingDao = new TrackingBirdPairDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                switch (status) {
                    case "Đã giao hàng":
                        orderDao.updatePaymentStatus(order_id, "Đã thanh toán");
                        break;
                    case "Đã hủy":
                        ArrayList<OrderItemDTO> orderDetails = itemDao.getItemByOrderId(order_id);
                        Order o = orderDao.getOrderById(order_id);
                        boolean check = true;
                        for (OrderItemDTO orderDetail : orderDetails) {
                            if (orderDetail.getBird() != null) {
                                birdDao.updateBirdStatus("Còn hàng", orderDetail.getBird().getBird_id());
                            }
                            if (orderDetail.getAccessory() != null) {
                                String stockQuantity = String.valueOf(orderDetail.getAccessory().getStock_quantity() + orderDetail.getOrder_quantity());
                                accessoryDao.updateAccessoryQuantity(orderDetail.getAccessory().getAccessory_id(), stockQuantity);
                            }
                            if (orderDetail.getBirdNest() != null) {
                                int baby_quantity = orderDetail.getBirdNest().getBaby_quantity() + orderDetail.getOrder_quantity();
                                nestDao.updateBirdNestBaby(baby_quantity, orderDetail.getBirdNest().getNest_id());
                            }
                            if (orderDetail.getBirdPair() != null) {
                                if (orderDetail.getBirdPair().getMale_bird() != null) {
                                    birdDao.updateBirdStatus("Còn hàng", orderDetail.getBirdPair().getMale_bird().getBird_id());
                                }
                                if (orderDetail.getBirdPair().getFemale_bird() != null) {
                                    birdDao.updateBirdStatus("Còn hàng", orderDetail.getBirdPair().getFemale_bird().getBird_id());
                                }
                                if (orderDetail.getBirdPair().getBirdCustomer() != null) {
                                    birdCustomerDao.updateBirdCustomerStatus(orderDetail.getBirdPair().getUsername(), "Chưa ghép cặp");
                                }
                                trackingDao.updateTrackingBirdPair(orderDetail.getBirdPair().getPair_id() + "", "Đơn hàng huỷ do khách hàng không xác nhận thanh toán", "Đã hủy");
                                if(!orderDetail.getBirdPair().getStatus().equals("Chờ lấy chim")){
                                   check = false;
                                }
                            }
                        }
                        if (o != null && check && "Chuyển khoản".equals(o.getPaymentType())) {
                            VNPAYUtils.refundMoney(o.getVnpTeref(), o.getTotal_price() + "", o.getVnpDate(), o.getVnpTransaction(), r);
                        }
                        break;
                }
                String query = "UPDATE [dbo].[Order] SET [order_status] = ?, [cancel_reason] = ? WHERE [order_id] = ?";
                stm = con.prepareStatement(query);
                stm.setString(1, status);
                stm.setString(2, reason);
                stm.setString(3, order_id);
                int rs = stm.executeUpdate();
                if (rs > 0) {
                    isUpdated = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return isUpdated;
    }

    public ArrayList<Order> getOrderByCustomer(String customer) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Order order;
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT [order_id],[customer],[order_date],[order_status],[name_receiver],\n"
                        + "[phone_receiver],[address_receiver],[payment_status],[total_price],[cancel_reason]\n"
                        + "FROM [BirdFarmShop].[dbo].[Order] WHERE [customer] = ? ORDER BY [order_date] DESC";
                stm = con.prepareStatement(query);
                stm.setString(1, customer);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String order_id = rs.getString("order_id");
                    Date order_date = rs.getDate("order_date");
                    String order_status = rs.getString("order_status");
                    String name_receiver = rs.getString("name_receiver");
                    int phone_receiver = rs.getInt("phone_receiver");
                    String address_receiver = rs.getString("address_receiver");
                    String payment_status = rs.getString("payment_status");
                    int total_price = rs.getInt("total_price");
                    String cancel_reason = rs.getString("cancel_reason");
                    order = new Order(order_id, customer, order_date, order_status, name_receiver,
                            phone_receiver, address_receiver, payment_status, total_price, cancel_reason);
                    orderList.add(order);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return orderList;
    }

    public int updateOrder(String order_id, int totalPrice) {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public ArrayList<Object> getRevenue(LocalDate startDay, LocalDate endDay) throws SQLException {
        ArrayList<Object> r = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT SUM(CASE WHEN (order_status = N'Đã giao hàng' OR order_status = N'Đã đánh giá') AND payment_status = N'Đã thanh toán' THEN total_price END) AS [total_price], \n"
                        + "       COUNT(order_id) AS[amount_order],\n"
                        + "       COUNT(CASE WHEN order_status = N'Đã hủy' THEN 1 END) AS [cancel_order]\n"
                        + "FROM [BirdFarmShop].[dbo].[Order]\n";
                if (startDay != null && endDay == null) {
                    query += "WHERE [order_date] >= '" + startDay + " 00:00:00.000'";
                } else if (endDay != null && startDay == null) {
                    query += "WHERE [order_date] <= '" + endDay + " 00:00:00.000'";
                } else if (endDay != null && startDay != null) {
                    query += "WHERE [order_date] >= '" + startDay + " 00:00:00.000'\n"
                            + " AND [order_date] <= '" + endDay + " 00:00:00.000'";
                }
                st = con.createStatement();
                rs = st.executeQuery(query);
                if (rs != null && rs.next()) {
                    r.add(rs.getLong("total_price"));
                    r.add(rs.getInt("amount_order"));
                    r.add(rs.getInt("cancel_order"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return r;
    }

    public ArrayList<Integer> getProductSale(LocalDate startDay) throws SQLException {
        ArrayList<Integer> p = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT COUNT(order_item_id) AS totalProduct,\n"
                        + "       COUNT( bird_id ) AS bird,\n"
                        + "       COUNT( accessory_id ) AS accessory,\n"
                        + "       (SELECT COUNT(oi.pair_id)\n"
                        + "		FROM [BirdFarmShop].[dbo].[OrderItem] oi\n"
                        + "		LEFT JOIN [BirdFarmShop].[dbo].BirdPair bp\n"
                        + "		ON oi.pair_id = bp.pair_id\n"
                        + "		RIGHT JOIN [BirdFarmShop].[dbo].[Order] o\n"
                        + "		ON oi.order_id = o.order_id\n"
                        + "		WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá')) \n";
                if (startDay != null) {
                    query += "		AND o.order_date >= '" + startDay + "'\n";
                }
                query += "	 	)AS pair\n"
                        + "FROM [BirdFarmShop].[dbo].[OrderItem] oi\n"
                        + "RIGHT JOIN [BirdFarmShop].[dbo].[Order] o\n"
                        + "ON oi.order_id = o.order_id\n"
                        + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))";
                if (startDay != null) {
                    query += "WHERE o.order_date >= '" + startDay + "'";
                }
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    p.add(rs.getInt("totalProduct"));
                    p.add(rs.getInt("bird"));
                    p.add(rs.getInt("accessory"));
                    p.add(rs.getInt("pair"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return p;
    }

    public ArrayList<Integer> getProductSale(LocalDate startDay, LocalDate endDay) throws SQLException {
        ArrayList<Integer> p = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT COUNT( bird_id ) AS bird,\n"
                        + "       SUM(CASE WHEN oi.unit_price > 0 AND oi.accessory_id IS NOT NULL THEN oi.order_quantity END) AS accessory,\n"
                        + "       (SELECT COUNT(oi.pair_id)\n"
                        + "		FROM [BirdFarmShop].[dbo].[OrderItem] oi\n"
                        + "		LEFT JOIN [BirdFarmShop].[dbo].BirdPair bp\n"
                        + "		ON oi.pair_id = bp.pair_id\n"
                        + "		RIGHT JOIN [BirdFarmShop].[dbo].[Order] o\n"
                        + "		ON oi.[order_id] = o.[order_id]\n"
                        + "		WHERE (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá') \n";
                if (startDay != null && endDay == null) {
                    query += "	AND o.[order_date] >= '" + startDay + " 00:00:00.000'\n";
                } else if (endDay != null && startDay == null) {
                    query += "	AND o.[order_date] <= '" + endDay + " 00:00:00.000'\n";
                } else if (startDay != null && endDay != null) {
                    query += "	AND   (order_date >= '" + startDay + " 00:00:00.000' AND order_date < = '" + endDay + " 00:00:00.000')\n";
                }
                query += "	 	)AS pair\n"
                        + "FROM [BirdFarmShop].[dbo].[OrderItem] oi\n"
                        + "RIGHT JOIN [BirdFarmShop].[dbo].[Order] o\n"
                        + "ON oi.[order_id] = o.[order_id]\n"
                        + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n";
                if (startDay != null && endDay == null) {
                    query += "AND o.[order_date] >= '" + startDay + " 00:00:00.000'\n";
                } else if (endDay != null && startDay == null) {
                    query += "		AND o.[order_date] <= '" + endDay + " 00:00:00.000'\n";
                } else if (startDay != null && endDay != null) {
                    query += "		AND (order_date >= '" + startDay + " 00:00:00.000' AND order_date < = '" + endDay + " 00:00:00.000')\n";
                }
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    p.add(rs.getInt("bird")+rs.getInt("accessory")+rs.getInt("pair"));
                    p.add(rs.getInt("bird"));
                    p.add(rs.getInt("accessory"));
                    p.add(rs.getInt("pair"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return p;
    }

    public ArrayList<Object> getMoneyByWeek(LocalDate startDay, String typeMoney) throws SQLException {
        ArrayList<Object> r = new ArrayList<>();
        ArrayList<String> dateWeek = new ArrayList<>();
        ArrayList<Long> money = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        LocalDate dateNow = LocalDate.now();
        String[] date = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"};
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (int i = 0; i < date.length - 1; i++) {
                    String query = "SELECT SUM(o.total_price) AS [total_price]\n"
                            + "FROM [BirdFarmShop].[dbo].[Order] o \n"
                            + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n";
                    if (startDay != null) {
                        query += "       AND (o.order_date >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.order_date < = '" + startDay + " 23:59:59.999')\n";
                    }
                    if (typeMoney != null && typeMoney.equals("online")) {
                        query += "   AND o.payment_type = N'Chuyển khoản'\n";
                    } else if (typeMoney != null && typeMoney.equals("receiver")) {
                        query += "   AND o.payment_type = N'Tiền mặt'\n";
                    }
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    if (rs != null && rs.next()) {
                        money.add(rs.getLong("total_price"));
                        dateWeek.add(date[i]);
                    }
                    if (startDay.getDayOfWeek() == dateNow.getDayOfWeek()) {
                        break;
                    }
                    startDay = startDay.plusDays(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        r.add(dateWeek);

        r.add(money);
        return r;
    }

    public ArrayList<Long> getMoneyByWeek(LocalDate startDay, String typeMoney, boolean bird, boolean accessory, boolean birdPair) throws SQLException {
        ArrayList<Long> money = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        LocalDate dateNow = LocalDate.now();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (int i = 0; i < 6; i++) {
                    String query = "SELECT SUM(oi.[unit_price]*oi.[order_quantity]) AS [total_price]\n"
                            + "       FROM [BirdFarmShop].[dbo].[Order] o\n"
                            + "	   LEFT JOIN [BirdFarmShop].[dbo].[OrderItem] oi\n"
                            + "	   ON o.[order_id] = oi.[order_id]\n"
                            + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n";
                    if (startDay != null) {
                        query += "       AND (o.[order_date] >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.[order_date] < = '" + startDay + " 23:59:59.999')\n";
                    }
                    if (typeMoney != null && typeMoney.equals("online")) {
                        query += "   AND o.payment_type = N'Chuyển khoản'\n";
                    } else if (typeMoney != null && typeMoney.equals("receiver")) {
                        query += "   AND o.payment_type = N'Tiền mặt'\n";
                    }
                    if (bird) {
                        query += " AND oi.bird_id IS NOT NULL\n";
                    } else if (accessory) {
                        query += "AND oi.accessory_id IS NOT NULL\n";
                    } else {
                        query = "SELECT SUM(oi.[unit_price]*oi.[order_quantity]+CASE WHEN bp.number_young_bird IS NOT NULL THEN bp.young_bird_price * bp.number_young_bird ELSE 0 END) AS [total_price]\n"
                                + "  FROM [BirdFarmShop].[dbo].[Order] o\n"
                                + "   LEFT JOIN [BirdFarmShop].[dbo].[OrderItem] oi\n"
                                + "   ON o.[order_id] = oi.[order_id]\n"
                                + "   LEFT JOIN BirdPair bp \n"
                                + "   ON oi.pair_id = bp.pair_id\n"
                                + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n"
                                + "       AND (o.[order_date] >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.[order_date] < = '" + startDay + " 23:59:59.999')\n"
                                + "   AND oi.pair_id IS NOT NULL";
                    }
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    if (rs != null && rs.next()) {
                        money.add(rs.getLong("total_price"));
                    }
                    if (startDay.getDayOfWeek() == dateNow.getDayOfWeek()) {
                        break;
                    }
                    startDay = startDay.plusDays(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return money;
    }

    public ArrayList<Object> getMoneyByMonth(LocalDate startDay, String typeMoney) throws SQLException {
        ArrayList<Object> r = new ArrayList<>();
        ArrayList<Integer> dateMonth = new ArrayList<>();
        ArrayList<Long> money = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        LocalDate dateNow = LocalDate.now();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (int i = 0; i < 31; i++) {
                    String query = "SELECT SUM(o.total_price) AS [total_price]\n"
                            + "FROM [BirdFarmShop].[dbo].[Order] o \n"
                            + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n";
                    if (startDay != null) {
                        query += "       AND (o.order_date >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.order_date < = '" + startDay + " 23:59:59.999')\n";
                    }
                    if (typeMoney != null && typeMoney.equals("online")) {
                        query += "   AND o.payment_type = N'Chuyển khoản'\n";
                    } else if (typeMoney != null && typeMoney.equals("receiver")) {
                        query += "   AND o.payment_type = N'Tiền mặt'\n";
                    }
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    if (rs != null && rs.next()) {
                        money.add(rs.getLong("total_price"));
                        dateMonth.add(startDay.getDayOfMonth());
                    }
                    if (startDay.getDayOfMonth() == dateNow.getDayOfMonth()) {
                        break;
                    }
                    startDay = startDay.plusDays(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        r.add(dateMonth);
        r.add(money);
        return r;
    }

    public ArrayList<Long> getMoneyByMonth(LocalDate startDay, String typeMoney, boolean bird, boolean accessory, boolean birdPair) throws SQLException {
        ArrayList<Long> money = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        LocalDate dateNow = LocalDate.now();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (int i = 0; i < 31; i++) {
                    String query = "SELECT SUM(oi.[unit_price]*oi.[order_quantity]) AS [total_price]\n"
                            + "       FROM [BirdFarmShop].[dbo].[Order] o\n"
                            + "	   LEFT JOIN [BirdFarmShop].[dbo].[OrderItem] oi\n"
                            + "	   ON o.[order_id] = oi.[order_id]\n"
                            + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n";
                    if (startDay != null) {
                        query += "       AND (o.[order_date] >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.[order_date] < = '" + startDay + " 23:59:59.999')\n";
                    }
                    if (typeMoney != null && typeMoney.equals("online")) {
                        query += "   AND o.payment_type = N'Chuyển khoản'\n";
                    } else if (typeMoney != null && typeMoney.equals("receiver")) {
                        query += "   AND o.payment_type = N'Tiền mặt'\n";
                    }
                    if (bird) {
                        query += " AND oi.bird_id IS NOT NULL\n";
                    } else if (accessory) {
                        query += "AND oi.accessory_id IS NOT NULL\n";
                    } else {
                        query = "SELECT SUM(oi.[unit_price]*oi.[order_quantity]+CASE WHEN bp.number_young_bird IS NOT NULL THEN bp.young_bird_price * bp.number_young_bird ELSE 0 END) AS [total_price]\n"
                                + "  FROM [BirdFarmShop].[dbo].[Order] o\n"
                                + "   LEFT JOIN [BirdFarmShop].[dbo].[OrderItem] oi\n"
                                + "   ON o.[order_id] = oi.[order_id]\n"
                                + "   LEFT JOIN BirdPair bp \n"
                                + "   ON oi.pair_id = bp.pair_id\n"
                                + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n"
                                + "       AND (o.[order_date] >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.[order_date] < = '" + startDay + " 23:59:59.999')\n"
                                + "   AND oi.pair_id IS NOT NULL";
                    }
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    if (rs != null && rs.next()) {
                        money.add(rs.getLong("total_price"));
                    }
                    if (startDay.getDayOfMonth() == dateNow.getDayOfMonth()) {
                        break;
                    }
                    startDay = startDay.plusDays(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return money;
    }

    public ArrayList<Object> getMoneyByYear(LocalDate startDay, String typeMoney) throws SQLException {
        ArrayList<Object> r = new ArrayList<>();
        ArrayList<String> dateYear = new ArrayList<>();
        ArrayList<Long> money = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        LocalDate dateNow = LocalDate.now();
        String[] month = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (int i = 0; i < month.length - 1; i++) {
                    int maxDay = startDay.lengthOfMonth();
                    LocalDate localMaxDate = LocalDate.of(startDay.getYear(), startDay.getMonth(), maxDay);
                    String query = "SELECT SUM(o.total_price) AS [total_price]\n"
                            + "FROM [BirdFarmShop].[dbo].[Order] o \n"
                            + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n";
                    if (startDay != null) {
                        query += "       AND (o.order_date >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.order_date < = '" + localMaxDate + " 23:59:59.999')\n";
                    }
                    if (typeMoney != null && typeMoney.equals("online")) {
                        query += "   AND o.payment_type = N'Chuyển khoản'\n";
                    } else if (typeMoney != null && typeMoney.equals("receiver")) {
                        query += "   AND o.payment_type = N'Tiền mặt'\n";
                    }
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    if (rs != null && rs.next()) {
                        money.add(rs.getLong("total_price"));
                        dateYear.add(month[i]);
                    }
                    if (startDay.getMonth() == dateNow.getMonth()) {
                        break;
                    }
                    startDay = startDay.plusMonths(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        r.add(dateYear);

        r.add(money);
        return r;
    }

    public ArrayList<Long> getMoneyByYear(LocalDate startDay, String typeMoney, boolean bird, boolean accessory, boolean birdPair) throws SQLException {
        ArrayList<Long> money = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        LocalDate dateNow = LocalDate.now();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (int i = 0; i < 11; i++) {
                    int maxDay = startDay.lengthOfMonth();
                    LocalDate localMaxDate = LocalDate.of(startDay.getYear(), startDay.getMonth(), maxDay);
                    String query = "SELECT SUM(oi.[unit_price]*oi.[order_quantity]) AS [total_price]\n"
                            + "       FROM [BirdFarmShop].[dbo].[Order] o\n"
                            + "	   LEFT JOIN [BirdFarmShop].[dbo].[OrderItem] oi\n"
                            + "	   ON o.[order_id] = oi.[order_id]\n"
                            + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n";
                    if (startDay != null) {
                        query += "       AND (o.[order_date] >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.[order_date] < = '" + localMaxDate + " 23:59:59.999')\n";
                    }
                    if (typeMoney != null && typeMoney.equals("online")) {
                        query += "   AND o.payment_type = N'Chuyển khoản'\n";
                    } else if (typeMoney != null && typeMoney.equals("receiver")) {
                        query += "   AND o.payment_type = N'Tiền mặt'\n";
                    }
                    if (bird) {
                        query += " AND oi.bird_id IS NOT NULL\n";
                    } else if (accessory) {
                        query += "AND oi.accessory_id IS NOT NULL\n";
                    } else {
                        query = "SELECT SUM(oi.[unit_price]*oi.[order_quantity]+CASE WHEN bp.number_young_bird IS NOT NULL THEN bp.young_bird_price * bp.number_young_bird ELSE 0 END) AS [total_price]\n"
                                + "  FROM [BirdFarmShop].[dbo].[Order] o\n"
                                + "   LEFT JOIN [BirdFarmShop].[dbo].[OrderItem] oi\n"
                                + "   ON o.[order_id] = oi.[order_id]\n"
                                + "   LEFT JOIN BirdPair bp \n"
                                + "   ON oi.pair_id = bp.pair_id\n"
                                + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n"
                                + "       AND (o.[order_date] >= '" + startDay + " 00:00:00.000' \n"
                                + "	   AND o.[order_date] < = '" + localMaxDate + " 23:59:59.999')\n"
                                + "   AND oi.pair_id IS NOT NULL";
                    }
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    if (rs != null && rs.next()) {
                        money.add(rs.getLong("total_price"));
                    }
                    if (startDay.getMonth() == dateNow.getMonth()) {
                        break;
                    }
                    startDay = startDay.plusMonths(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return money;
    }

    public ArrayList<Object> getMoneyByAll(String typeMoney) throws SQLException {
        OrderDAO order = new OrderDAO();
        ArrayList<Object> r = new ArrayList<>();
        ArrayList<Integer> dateYear = new ArrayList<>();
        ArrayList<Long> money = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        LocalDate dateNow = LocalDate.now();
        LocalDate dateOrder = order.getFirstOrder().getOrder_date().toLocalDate();
        LocalDate startDate = LocalDate.of(dateOrder.getYear(), 1, 1);
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (int i = 0; i < 11; i++) {
                    LocalDate localMaxDate = LocalDate.of(startDate.getYear(), 12, 31);
                    String query = "SELECT SUM(o.total_price) AS [total_price]\n"
                            + "FROM [BirdFarmShop].[dbo].[Order] o \n"
                            + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n"
                            + "       AND (o.order_date >= '" + startDate + " 00:00:00.000' \n"
                            + "	   AND o.order_date < = '" + localMaxDate + " 23:59:59.999')\n";
                    if (typeMoney != null && typeMoney.equals("online")) {
                        query += "   AND o.payment_type = N'Chuyển khoản'\n";
                    } else if (typeMoney != null && typeMoney.equals("receiver")) {
                        query += "   AND o.payment_type = N'Tiền mặt'\n";
                    }
                    st = con.createStatement();
                    rs = st.executeQuery(query);
                    if (rs != null && rs.next()) {
                        money.add(rs.getLong("total_price"));
                        dateYear.add(startDate.getYear());
                    }
                    if (startDate.getYear() == dateNow.getYear()) {
                        break;
                    }
                    startDate = startDate.plusYears(1);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        r.add(dateYear);
        r.add(money);
        return r;
    }

        public ArrayList<Long> getMoneyByAll(String typeMoney, boolean bird, boolean accessory, boolean birdPair) throws SQLException {
        OrderDAO order = new OrderDAO();
        ArrayList<Long> money = new ArrayList<>();
         Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        LocalDate dateNow = LocalDate.now();
        LocalDate dateOrder = order.getFirstOrder().getOrder_date().toLocalDate();
        LocalDate startDate = LocalDate.of(dateOrder.getYear(), 1, 1);
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (int i = 0; i < 11; i++) {
                    LocalDate localMaxDate = LocalDate.of(startDate.getYear(), 12, 31);
                    String query = "SELECT SUM(oi.[unit_price]*oi.[order_quantity]) AS [total_price]\n"
                            + "       FROM [BirdFarmShop].[dbo].[Order] o\n"
                            + "	   LEFT JOIN [BirdFarmShop].[dbo].[OrderItem] oi\n"
                            + "	   ON o.[order_id] = oi.[order_id]\n"
                             + "WHERE (o.payment_status = N'Đã thanh toán' AND (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá'))\n"
                            + "       AND (o.[order_date] >= '" + startDate + " 00:00:00.000' \n"
                            + "	   AND o.[order_date] < = '" + localMaxDate + " 23:59:59.999')\n";
                    if (typeMoney != null && typeMoney.equals("online")) {
                        query += "   AND o.payment_type = N'Chuyển khoản'\n";
                    } else if (typeMoney != null && typeMoney.equals("receiver")) {
                        query += "   AND o.payment_type = N'Tiền mặt'\n";
                    }
                    if (bird) {
                        query += " AND oi.bird_id IS NOT NULL\n";
                    } else if (accessory) {
                        query += "AND oi.accessory_id IS NOT NULL\n";
                    } else {
                        query = "SELECT SUM(oi.[unit_price]*oi.[order_quantity]+ CASE WHEN bp.number_young_bird IS NOT NULL THEN bp.young_bird_price * bp.number_young_bird ELSE 0 END) AS [total_price]\n"
                                + "  FROM [BirdFarmShop].[dbo].[Order] o\n"
                                + "   LEFT JOIN [BirdFarmShop].[dbo].[OrderItem] oi\n"
                                + "   ON o.[order_id] = oi.[order_id]\n"
                                + "   LEFT JOIN BirdPair bp \n"
                                + "   ON oi.pair_id = bp.pair_id\n"
                                + "WHERE  (o.order_status = N'Đã giao hàng' OR o.order_status = N'Đã đánh giá')\n"
                                + "       AND (o.[order_date] >= '" + startDate + " 00:00:00.000' \n"
                                + "	   AND o.[order_date] < = '" + localMaxDate + " 23:59:59.999')\n"
                                + "   AND oi.pair_id IS NOT NULL";
                    }
                     st = con.createStatement();
                    rs = st.executeQuery(query);
                    if (rs != null && rs.next()) {
                        money.add(rs.getLong("total_price"));
                    }
                    if (startDate.getYear() == dateNow.getYear()) {
                        break;
                    }
                    startDate = startDate.plusYears(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return money;
    }

    public Order getFirstOrder() {
        Order o = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 [order_id]\n"
                        + "      ,[customer]\n"
                        + "      ,[order_date]\n"
                        + "      ,[order_status]\n"
                        + "      ,[name_receiver]\n"
                        + "      ,[phone_receiver]\n"
                        + "      ,[address_receiver]\n"
                        + "      ,[payment_status]\n"
                        + "      ,[total_price]\n"
                        + "      ,[cancel_reason]\n"
                        + "  FROM [BirdFarmShop].[dbo].[Order]\n"
                        + "  ORDER BY order_date ASC";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs != null && rs.next()) {
                    String order_id = rs.getString("order_id");
                    String customer = rs.getString("customer");
                    Date order_date = rs.getDate("order_date");
                    String order_status = rs.getString("order_status");
                    String name_receiver = rs.getString("name_receiver");
                    int phone_receiver = rs.getInt("phone_receiver");
                    String address_receiver = rs.getString("address_receiver");
                    String payment_status = rs.getString("payment_status");
                    int total_price = rs.getInt("total_price");
                    String cancel_reason = rs.getString("cancel_reason");
                    o = new Order(order_id, customer, order_date, order_status, name_receiver, phone_receiver, address_receiver, payment_status, total_price, cancel_reason);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }

    public boolean updatePaymentStatus(String order_id, String status) throws SQLException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "UPDATE [dbo].[Order] SET [payment_status] = ? WHERE [order_id] = ?";
                stm = con.prepareStatement(query);
                stm.setString(1, status);
                stm.setString(2, order_id);
                int rs = stm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
