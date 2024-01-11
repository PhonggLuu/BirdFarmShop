/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import swp391.birdfarmshop.dto.BirdPairDTO;
import swp391.birdfarmshop.dto.OrderItemDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdNest;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author tlminh
 */
public class OrderItemDAO {

    public String error = null;

    public ArrayList<OrderItemDTO> getItemOrder(String order_id) throws SQLException {
        ArrayList<OrderItemDTO> orderItemList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderItemDTO orderItem;
        BirdDAO birdDao = new BirdDAO();
        BirdNestDAO bnDao = new BirdNestDAO();
        AccessoryDAO accessoryDao = new AccessoryDAO();
        BirdPairDAO bpDao = new BirdPairDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("SELECT [order_item_id],[bird_id],[nest_id],"
                        + "                 [accessory_id],[pair_id],[unit_price],[order_quantity]\n"
                        + "                 FROM [dbo].[OrderItem]\n"
                        + "                 WHERE [order_id] = ?");
                stm.setString(1, order_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int order_item_id = rs.getInt("order_item_id");
                    String bird_id = rs.getString("bird_id");
                    String nest_id = rs.getString("nest_id");
                    String accessory_id = rs.getString("accessory_id");
                    Bird bird = birdDao.getBirdById(bird_id);
                    BirdNest birdNest = bnDao.getBirdsNestById(nest_id);
                    Accessory accessory = accessoryDao.getAccessoryByID(accessory_id);
                    BirdPairDTO birdPair = bpDao.getBirdPairByOrderId(order_id);
                    int unit_price = rs.getInt("unit_price");
                    int order_quantity = rs.getInt("order_quantity");
                    orderItem = new OrderItemDTO(order_item_id, order_id, bird, accessory, birdNest, birdPair, unit_price, order_quantity);
                    orderItemList.add(orderItem);
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
        return orderItemList;
    }

    public int addNewPairOrderItem(String order_id, String male_bird_id, String female_bird_id, String accessory_id, int totalPrice, int pair_id, boolean typePayment) {
        int result = 0;
        Connection con = null;
        OrderItemDAO oid = new OrderItemDAO();
        BirdDAO bd = new BirdDAO();
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean checkBird = true;
        boolean checkAccessory = true;
        boolean checkMoney = true;
        boolean checkPair = true;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                if (male_bird_id != null) {
                    String maleBird = "SELECT [price]\n"
                            + "      ,[discount]\n"
                            + "FROM [BirdFarmShop].[dbo].[Bird]\n"
                            + "WHERE [bird_id]= ? AND [status]= ?";
                    pst = con.prepareStatement(maleBird);
                    pst.setString(1, male_bird_id);
                    pst.setString(2, "Đang sinh sản");
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        int realPrice = rs.getInt("price");
                        int discount = rs.getInt("discount");
                        if (discount > 0) {
                            realPrice = realPrice - realPrice * discount / 100;
                        }
                        String updateMaleBird = "UPDATE [Bird]\n"
                                + "SET [status] = N'Đã bán'\n"
                                + "WHERE [bird_id] = ?";
                        pst = con.prepareStatement(updateMaleBird);
                        pst.setString(1, male_bird_id);
                        result = pst.executeUpdate();
                        if (result > 0) {
                            String insertBird = "INSERT INTO [OrderItem]([order_id],[bird_id],\n"
                                    + "		  [unit_price],[order_quantity])\n"
                                    + "   VALUES(?,?,?,?)";
                            pst = con.prepareStatement(insertBird);
                            pst.setString(1, order_id);
                            pst.setString(2, male_bird_id);
                            pst.setInt(3, realPrice);
                            pst.setInt(4, 1);
                            result = pst.executeUpdate();
                            if (result == 0) {
                                checkBird = false;
                            }
                        }
                    } else {
                        error = "Không tìm thấy sản phẩm";
                        checkBird = false;
                    }
                } else {
                    String SelectBirdPair = "SELECT [male_bird_id]\n"
                            + "FROM [BirdPair]\n"
                            + "WHERE [pair_id] = ?";
                    pst = con.prepareStatement(SelectBirdPair);
                    pst.setInt(1, pair_id);
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        String id = rs.getString("male_bird_id");
                        if (id != null) {
                            String updateMaleBird = "UPDATE [Bird]\n"
                                    + "SET [status] = N'Còn hàng'\n"
                                    + "WHERE [bird_id] = ?";
                            pst = con.prepareStatement(updateMaleBird);
                            pst.setString(1, id);
                            result = pst.executeUpdate();
                            if (result == 0) {
                                checkBird = false;
                            }
                        }
                    } else {
                        checkBird = false;
                    }
                }
                if (female_bird_id != null) {
                    String checkFemaleBird = "SELECT [price]\n"
                            + "      ,[discount]\n"
                            + "FROM [BirdFarmShop].[dbo].[Bird]\n"
                            + "WHERE [bird_id]= ? AND [status] = ?";
                    pst = con.prepareStatement(checkFemaleBird);
                    pst.setString(1, female_bird_id);
                    pst.setString(2, "Đang sinh sản");
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        int realPrice = rs.getInt("price");
                        int discount = rs.getInt("discount");
                        if (discount > 0) {
                            realPrice = realPrice - realPrice * discount / 100;
                        }
                        String updateBird = "UPDATE [Bird]\n"
                                + "SET [status] = N'Đã bán'\n"
                                + "WHERE [bird_id] = ?";
                        pst = con.prepareStatement(updateBird);
                        pst.setString(1, female_bird_id);
                        result = pst.executeUpdate();
                        if (result == 0) {
                            checkBird = false;

                        } else {
                            String insertBird = "INSERT INTO [OrderItem]([order_id],[bird_id],\n"
                                    + "		  [unit_price],[order_quantity])\n"
                                    + "   VALUES(?,?,?,?)";
                            pst = con.prepareStatement(insertBird);
                            pst.setString(1, order_id);
                            pst.setString(2, female_bird_id);
                            pst.setInt(3, realPrice);
                            pst.setInt(4, 1);
                            result = pst.executeUpdate();
                            if (result == 0) {
                                checkBird = false;
                            }
                        }
                    } else {
                        error = "Không tìm thấy sản phẩm";
                        checkBird = false;
                    }
                } else {
                    String SelectBirdPair = "SELECT [female_bird_id]\n"
                            + "FROM [BirdPair]\n"
                            + "WHERE [pair_id] = ?";
                    pst = con.prepareStatement(SelectBirdPair);
                    pst.setInt(1, pair_id);
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        String id = rs.getString("female_bird_id");
                        if (id != null) {
                            String updateMaleBird = "UPDATE [Bird]\n"
                                    + "SET [status] = N'Còn hàng'\n"
                                    + "WHERE [bird_id] = ?";
                            pst = con.prepareStatement(updateMaleBird);
                            pst.setString(1, id);
                            result = pst.executeUpdate();
                            if (result == 0) {
                                checkBird = false;
                            }
                        }
                    } else {
                        checkBird = false;
                    }
                }
                if (accessory_id != null) {
                    String getAccessoryOrder = "SELECT [order_quantity]\n"
                            + "FROM [BirdFarmShop].[dbo].[OrderItem]\n"
                            + "WHERE [accessory_id]= ? AND [unit_price] = ? AND order_id = ?";

                    pst = con.prepareStatement(getAccessoryOrder);
                    pst.setString(1, accessory_id);
                    pst.setInt(2, 0);
                    pst.setString(3, order_id);
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        int quantity = rs.getInt("order_quantity");
                        String getStockAccessory = "SELECT [stock_quantity]\n"
                                + "FROM [BirdFarmShop].[dbo].[Accessory]\n"
                                + "WHERE [accessory_id] = ?";
                        pst = con.prepareStatement(getStockAccessory);
                        pst.setString(1, accessory_id);
                        rs = pst.executeQuery();
                        if (rs != null && rs.next()) {
                            int numberAccessory = rs.getInt("stock_quantity");
                            if (numberAccessory == 0) {
                                error = "Sản phẩm này đã hết hàng";
                                checkAccessory = false;
                            } else if (numberAccessory < 1) {
                                checkAccessory = false;
                                error = "Sản phẩm này không đủ số lượng trong kho";
                            } else {
                                int newStock = numberAccessory - 1;
                                if (female_bird_id != null && male_bird_id != null) {
                                    newStock -= 1;
                                    quantity += 1;
                                    if (newStock == -1) {
                                        error = "Sản phẩm này không đủ số lượng trong kho";
                                        return 0;
                                    }
                                }
                                String updateQuantity = "UPDATE [Accessory]\n"
                                        + "SET [stock_quantity] = ?\n"
                                        + "WHERE [accessory_id] = ?";
                                pst = con.prepareStatement(updateQuantity);
                                pst.setInt(1, newStock);
                                pst.setString(2, accessory_id);
                                result = pst.executeUpdate();
                                if (result == 0) {
                                    checkAccessory = false;
                                } else {
                                    String updateOrder = "UPDATE [OrderItem]\n"
                                            + "SET [order_quantity] = ?\n"
                                            + "WHERE [order_id] = ? \n"
                                            + "AND [accessory_id] = ?;";
                                    pst = con.prepareStatement(updateOrder);
                                    pst.setInt(1, quantity + 1);
                                    pst.setString(2, order_id);
                                    pst.setString(3, accessory_id);

                                    result = pst.executeUpdate();
                                    if (result == 0) {
                                        checkAccessory = false;
                                    }
                                }
                            }
                        } else {
                            error = "Không tìm thấy sản phẩm";
                            checkAccessory = false;
                        }
                    } else {
                        String getStockAccessory = "SELECT [stock_quantity]\n"
                                + "FROM [BirdFarmShop].[dbo].[Accessory]\n"
                                + "WHERE [accessory_id] = ?";
                        pst = con.prepareStatement(getStockAccessory);
                        pst.setString(1, accessory_id);
                        rs = pst.executeQuery();
                        if (rs != null && rs.next()) {
                            int numberAccessory = rs.getInt("stock_quantity");
                            if (numberAccessory == 0) {
                                error = "Sản phẩm này đã hết hàng";
                                checkAccessory = false;
                            } else if (numberAccessory < 1) {
                                checkAccessory = false;
                                error = "Sản phẩm này không đủ số lượng trong kho";
                            } else {
                                int quantity = 1;
                                int newStock = numberAccessory - 1;
                                if (female_bird_id != null && male_bird_id != null) {
                                    newStock -= 1;
                                    quantity += 1;
                                    if (newStock == -1) {
                                        error = "Sản phẩm này không đủ số lượng trong kho";
                                        return 0;
                                    }
                                }
                                String updateQuantity = "UPDATE [Accessory]\n"
                                        + "SET [stock_quantity] = ?\n"
                                        + "WHERE [accessory_id] = ?";
                                pst = con.prepareStatement(updateQuantity);
                                pst.setInt(1, newStock);
                                pst.setString(2, accessory_id);
                                result = pst.executeUpdate();
                                if (result == 0) {
                                    checkAccessory = false;
                                } else {
                                    String insertAccessory = "INSERT INTO [OrderItem]([order_id],\n"
                                            + "		  [accessory_id],[unit_price],[order_quantity])\n"
                                            + "               VALUES(?,?,?,?)";
                                    pst = con.prepareStatement(insertAccessory);
                                    pst.setString(1, order_id);
                                    pst.setString(2, accessory_id);
                                    pst.setInt(3, 0);
                                    pst.setInt(4, quantity);
                                    result = pst.executeUpdate();
                                    if (result == 0) {
                                        checkAccessory = false;
                                    }
                                }
                            }
                        } else {
                            error = "Không tìm thấy sản phẩm";
                            checkAccessory = false;
                        }
                    }
                }
                if (totalPrice > 0) {
                    String query = "SELECT [total_price]\n"
                            + "FROM [BirdFarmShop].[dbo].[Order] WHERE [order_id] = ?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, order_id);
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        int oldPrice = rs.getInt("total_price");
                        totalPrice += oldPrice;
                        if (typePayment == false) {
                            String update = "UPDATE [ORDER]\n"
                                    + "SET [total_price] = ?\n"
                                    + "WHERE [order_id] = ?";
                            pst = con.prepareStatement(update);
                            pst.setInt(1, totalPrice);
                            pst.setString(2, order_id);
                        } else {
                            String update = "UPDATE [ORDER]\n"
                                    + "SET [total_price] = ?, \n"
                                    + "[payment_type] = N'Chuyển khoản',\n"
                                    + "[payment_status] = N'Đã thanh toán'\n"
                                    + "WHERE [order_id] = ?";
                            pst = con.prepareStatement(update);
                            pst.setInt(1, totalPrice);
                            pst.setString(2, order_id);
                        }
                        result = pst.executeUpdate();
                        if (result == 0) {
                            checkMoney = false;
                        }
                    } else {
                        checkMoney = false;
                    }
                }
                if (pair_id > 0) {
                    String updateStatusBirdPair = "UPDATE [BirdPair]\n"
                            + "SET [status] = ?\n"
                            + "WHERE [pair_id] = ?";
                    pst = con.prepareStatement(updateStatusBirdPair);
                    pst.setString(1, "Đã thanh toán");
                    pst.setInt(2, pair_id);
                    result = pst.executeUpdate();
                    if (result == 0) {
                        checkPair = false;
                    }
                }
                String SelectBirdPair = "SELECT [bird_customer]\n"
                        + "FROM [BirdPair]\n"
                        + "WHERE [pair_id] = ?";
                pst = con.prepareStatement(SelectBirdPair);
                pst.setInt(1, pair_id);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    String id = rs.getString("bird_customer");
                    if (id != null) {
                        String updateMaleBird = "UPDATE [CustomerBird]\n"
                                + "SET [status] = N'Chưa ghép cặp'\n"
                                + "WHERE [bird_id] = ?";
                        pst = con.prepareStatement(updateMaleBird);
                        pst.setString(1, id);
                        result = pst.executeUpdate();
                        if (result == 0) {
                            checkBird = false;
                        }
                    }
                } else {
                    checkBird = false;
                }
                if (checkAccessory && checkBird && checkMoney && checkPair) {
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

    public ArrayList<OrderItemDTO> getItemByOrderId(String order_id) throws SQLException {
        ArrayList<OrderItemDTO> orderItemList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderItemDTO orderItem;
        BirdDAO birdDao = new BirdDAO();
        BirdNestDAO bnDao = new BirdNestDAO();
        AccessoryDAO accessoryDao = new AccessoryDAO();
        BirdPairDAO bpDao = new BirdPairDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("SELECT Item.[order_item_id],Item.[order_id],[Order].[order_status]\n"
                        + "                            ,Item.[bird_id],Item.[nest_id],Item.[accessory_id],Item.[pair_id],Item.[unit_price],Item.[order_quantity]\n"
                        + "                            FROM [BirdFarmShop].[dbo].[OrderItem] AS Item\n"
                        + "                            LEFT JOIN [BirdFarmShop].[dbo].[Order] ON Item.[order_id] = [Order].[order_id]\n"
                        + "                            WHERE Item.[order_id] = ?");
                stm.setString(1, order_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int order_item_id = rs.getInt("order_item_id");
                    String bird_id = rs.getString("bird_id");
                    String nest_id = rs.getString("nest_id");
                    String accessory_id = rs.getString("accessory_id");
                    int unit_price = rs.getInt("unit_price");
                    int order_quantity = rs.getInt("order_quantity");
                    Bird bird = birdDao.getBirdById(bird_id);
                    BirdNest birdNest = bnDao.getBirdNestById(nest_id);
                    Accessory accessory = accessoryDao.getAccessoryByID(accessory_id);
                    BirdPairDTO birdPair = bpDao.getBirdPairByOrderId(order_id);
                    orderItem = new OrderItemDTO(order_item_id, order_id, bird, accessory, birdNest, birdPair, unit_price, order_quantity);
                    orderItemList.add(orderItem);
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
        return orderItemList;
    }
    
    public OrderItemDTO getOrderItem(int order_item_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderItemDTO orderItem = null;
        BirdDAO birdDao = new BirdDAO();
        BirdNestDAO bnDao = new BirdNestDAO();
        AccessoryDAO accessoryDao = new AccessoryDAO();
        BirdPairDAO bpDao = new BirdPairDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("SELECT [order_id],[bird_id],[nest_id],"
                        + "                 [accessory_id],[unit_price],[order_quantity]\n"
                        + "                 FROM [dbo].[OrderItem]\n"
                        + "                 WHERE [order_item_id] = ?");
                stm.setInt(1, order_item_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String order_id = rs.getString("order_id");
                    String bird_id = rs.getString("bird_id");
                    String nest_id = rs.getString("nest_id");
                    String accessory_id = rs.getString("accessory_id");
                    Bird bird = birdDao.getBirdById(bird_id);
                    BirdNest birdNest = bnDao.getBirdsNestById(nest_id);
                    Accessory accessory = accessoryDao.getAccessoryByID(accessory_id);
                    BirdPairDTO birdPair = bpDao.getBirdPairByOrderId(order_id);
                    int unit_price = rs.getInt("unit_price");
                    int order_quantity = rs.getInt("order_quantity");
                    orderItem = new OrderItemDTO(order_item_id, order_id, bird, accessory, birdNest, birdPair, unit_price, order_quantity);
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
        return orderItem;
    }
}
