/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import swp391.birdfarmshop.dto.BirdPairDTO;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdCustomer;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author tlminh
 */
public class BirdPairDAO {

    public ArrayList<BirdPairDTO> getBirdPairByUser(String username, String status) {
        ArrayList<BirdPairDTO> bpList = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        BirdCustomerDAO bsd = new BirdCustomerDAO();
        BirdDAO bd = new BirdDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT bp.pair_id, bp.order_id, bp.young_bird_price,\n"
                        + "	   bp.bird_customer, bp.male_bird_id, bp.female_bird_id,\n"
                        + "	   bp.number_egg, bp.number_young_bird, bp.[status]\n"
                        + "FROM [BirdPair] bp\n"
                        + "LEFT JOIN [Order] o\n"
                        + "ON bp.order_id = o.order_id\n";
                if (username != null) {
                    sql += "WHERE o.customer = '" + username + "'\n";
                    if (status != null) {
                        sql += " AND bp.[status] = N'" + status + "'\n";
                    }
                }
                if (username == null && status != null) {
                    sql += "WHERE bp.[status] = N'" + status + "'\n";
                }
                sql += "ORDER BY bp.pair_id DESC";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    int pair_id = rs.getInt("pair_id");
                    String order_id = rs.getString("order_id");
                    int young_bird_price = rs.getInt("young_bird_price");
                    BirdCustomer birdCustomer = bsd.findBirdCustomer(rs.getInt("bird_customer") + "");
                    Bird male_bird = bd.getBirdById(rs.getString("male_bird_id"));
                    Bird female_bird = bd.getBirdById(rs.getString("female_bird_id"));
                    int number_egg = rs.getInt("number_egg");
                    int number_young_bird = rs.getInt("number_young_bird");
                    status = rs.getString("status");
                    BirdPairDTO bp = new BirdPairDTO(pair_id, order_id, young_bird_price, birdCustomer, male_bird,
                            female_bird, number_egg, number_young_bird, status, null);
                    bpList.add(bp);
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
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return bpList;
    }

    public BirdPairDTO getBirdPairByOrderId(String order_id) {
        BirdPairDTO bp = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BirdCustomerDAO bsd = new BirdCustomerDAO();
        BirdDAO bd = new BirdDAO();
        ImageDAO imgDao = new ImageDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT bp.pair_id, bp.order_id, bp.young_bird_price,\n"
                        + "	   bp.bird_customer, bp.male_bird_id, bp.female_bird_id,\n"
                        + "	   bp.number_egg, bp.number_young_bird, bp.[status]\n"
                        + "FROM [BirdPair] bp\n"
                        + "LEFT JOIN [Order] o\n"
                        + "ON bp.order_id = o.order_id\n"
                        + "WHERE o.order_id = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, order_id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int pair_id = rs.getInt("pair_id");
                    int young_bird_price = rs.getInt("young_bird_price");
                    BirdCustomer birdCustomer = bsd.findBirdCustomer(rs.getInt("bird_customer") + "");
                    Bird male_bird = bd.getBirdById(rs.getString("male_bird_id"));
                    Bird female_bird = bd.getBirdById(rs.getString("female_bird_id"));
                    int number_egg = rs.getInt("number_egg");
                    int number_young_bird = rs.getInt("number_young_bird");
                    String status = rs.getString("status");
                    ArrayList<String> listPair = imgDao.getImagesByPairId(pair_id);
                    bp = new BirdPairDTO(pair_id, order_id, young_bird_price, birdCustomer, male_bird, female_bird, number_egg, number_young_bird, status, listPair);
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
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return bp;
    }
    public BirdPairDTO getBirdPairByBirdPairId(String pairId) {
        BirdPairDTO bp = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        BirdCustomerDAO bsd = new BirdCustomerDAO();
        BirdDAO bd = new BirdDAO();
        ImageDAO imgDao = new ImageDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT bp.pair_id, bp.order_id, bp.young_bird_price,\n"
                        + "	   bp.bird_customer, bp.male_bird_id, bp.female_bird_id,\n"
                        + "	   bp.number_egg, bp.number_young_bird, bp.[status], o.[customer]\n"
                        + "FROM [BirdPair] bp\n"
                        + "RIGHT JOIN [Order] o\n"
                        + "ON bp.order_id = o.order_id\n"
                        + "WHERE bp.pair_id = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, pairId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int pair_id = rs.getInt("pair_id");
                    String order_id = rs.getString("order_id");
                    int young_bird_price = rs.getInt("young_bird_price");
                    BirdCustomer birdCustomer = bsd.findBirdCustomer(rs.getInt("bird_customer") + "");
                    Bird male_bird = bd.getBirdById(rs.getString("male_bird_id"));
                    Bird female_bird = bd.getBirdById(rs.getString("female_bird_id"));
                    int number_egg = rs.getInt("number_egg");
                    int number_young_bird = rs.getInt("number_young_bird");
                    String status = rs.getString("status");
                    String customer = rs.getString("customer");
                    ArrayList<String> listPair = imgDao.getImagesByPairId(pair_id);
                    bp = new BirdPairDTO(pair_id, order_id, young_bird_price, birdCustomer, male_bird, female_bird, number_egg, number_young_bird, status, listPair, customer);
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
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return bp;
    }

    public ArrayList<BirdPairDTO> getBirdPair(String search, String page, int recordsPerPage) {
        ArrayList<BirdPairDTO> bpList = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        BirdCustomerDAO bsd = new BirdCustomerDAO();
        BirdDAO bd = new BirdDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT bp.pair_id, bp.order_id, bp.young_bird_price,\n"
                        + "	   bp.bird_customer, bp.male_bird_id, bp.female_bird_id,\n"
                        + "	   bp.number_egg, bp.number_young_bird, bp.[status],o.[customer],o.[order_date]\n"
                        + "FROM [BirdPair] bp\n"
                        + "LEFT JOIN [Order] o\n"
                        + "ON bp.order_id = o.order_id\n";
                if (search != null) {
                    sql += "WHERE o.[customer] = '" + search + "'\n";
                }
                if (page != null) {
                    int pageNumber = Integer.parseInt(page);
                    int start = (pageNumber - 1) * recordsPerPage;
                    sql += "ORDER BY bp.pair_id DESC OFFSET " + start + " ROWS FETCH NEXT " + recordsPerPage + " ROWS ONLY";
                }
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    int pair_id = rs.getInt("pair_id");
                    String order_id = rs.getString("order_id");
                    int young_bird_price = rs.getInt("young_bird_price");
                    BirdCustomer birdCustomer = bsd.findBirdCustomer(rs.getInt("bird_customer") + "");
                    Bird male_bird = bd.getBirdById(rs.getString("male_bird_id"));
                    Bird female_bird = bd.getBirdById(rs.getString("female_bird_id"));
                    int number_egg = rs.getInt("number_egg");
                    int number_young_bird = rs.getInt("number_young_bird");
                    String status = rs.getString("status");
                    String username = rs.getString("customer");
                    Timestamp dateTime = rs.getTimestamp("order_date");
                    BirdPairDTO bp = new BirdPairDTO(pair_id, order_id, young_bird_price, birdCustomer, male_bird, female_bird, number_egg, number_young_bird, status, username, dateTime, null);
                    bpList.add(bp);
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
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return bpList;
    }

    public int totalBirdPair(String search) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        BirdCustomerDAO bsd = new BirdCustomerDAO();
        BirdDAO bd = new BirdDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(bp.[pair_id]) AS Amount\n"
                        + "FROM [BirdPair] bp\n"
                        + "LEFT JOIN [Order] o\n"
                        + "ON bp.order_id = o.order_id\n";
                if (search != null) {
                    sql += "WHERE o.[customer] = '" + search + "'\n";
                }
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs != null && rs.next()) {
                    result = rs.getInt("Amount");
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
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
 
    public ArrayList<Integer> getBirdPairByStatus() {
        ArrayList<Integer> birdPair = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        BirdCustomerDAO bsd = new BirdCustomerDAO();
        BirdDAO bd = new BirdDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(pair_id) AS [total_birdPair],\n"
                        + "	   COUNT(CASE WHEN [status]= N'Đã thanh toán' THEN 1 END) AS [payment],\n"
                        + "	   COUNT(CASE WHEN [status]= N'Chờ lấy chim' THEN 1 END) AS [waiting_bird],\n"
                        + "	   COUNT(CASE WHEN [status]= N'Đang ghép cặp' THEN 1 END) AS [pairing],\n"
                        + "	   COUNT(CASE WHEN [status]= N'Đã sinh sản' THEN 1 END) AS [spawned],\n"
                        + "	   COUNT(CASE WHEN [status]= N'Đã ấp nở' THEN 1 END) AS [hatched]\n"
                        + "FROM [BirdFarmShop].[dbo].[BirdPair]";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs != null && rs.next()) {
                    birdPair.add(rs.getInt("total_birdPair"));
                    birdPair.add(rs.getInt("payment"));
                    birdPair.add(rs.getInt("waiting_bird"));
                    birdPair.add(rs.getInt("pairing"));
                    birdPair.add(rs.getInt("spawned"));
                    birdPair.add(rs.getInt("hatched"));
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
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return birdPair;
    }
}
