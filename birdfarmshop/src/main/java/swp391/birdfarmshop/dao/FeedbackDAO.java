/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import swp391.birdfarmshop.dto.FeedbackDTO;
import swp391.birdfarmshop.dto.StarDTO;
import swp391.birdfarmshop.model.Feedback;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author tlminh
 */
public class FeedbackDAO {

    public ArrayList<FeedbackDTO> getFeedbackByIdProduct(String id, String page, int recordsPerPage) {
        ArrayList<FeedbackDTO> list = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [full_name]\n"
                        + "      ,[email]\n"
                        + "      ,[rating]\n"
                        + "      ,[comment]\n"
                        + "      ,[feedback_date]\n"
                        + "      ,[bird_id]\n"
                        + "      ,[bird_name]\n"
                        + "      ,[accessory_id]\n"
                        + "      ,[accessory_name]\n"
                        + "      ,[image_url]\n"
                        + "  FROM [BirdFarmShop].[dbo].[View_Feedback]\n"
                        + "WHERE [bird_id] = ? OR [accessory_id] = ? \n";
                if (page != null) {
                    int pageNumber = Integer.parseInt(page);
                    int start = (pageNumber - 1) * recordsPerPage;
                    sql += "ORDER BY feedback_date DESC OFFSET " + start + " ROWS FETCH NEXT " + recordsPerPage + " ROWS ONLY";
                }
                pst = cnn.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("full_name");
                    String email = rs.getString("email");
                    int rating = rs.getInt("rating");
                    String comment = rs.getString("comment");
                    Date date = rs.getDate("feedback_date");
                    String bird_id = rs.getString("bird_id");
                    String bird_name = rs.getString("bird_name");
                    String accessory_id = rs.getString("accessory_id");
                    String accessory_name = rs.getString("accessory_name");
                    String image_url = rs.getString("image_url");
                    FeedbackDTO f = new FeedbackDTO(email, name, bird_id, bird_name, accessory_id,
                            accessory_name, image_url, comment, rating, image_url, date);
                    list.add(f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
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
        return list;
    }

    public int totalFeedbackByIdProduct(String id) {
        int number = 0;
        Connection cnn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT COUNT(feedback_date) AS Amount"
                        + "  FROM [BirdFarmShop].[dbo].[View_Feedback]\n"
                        + "WHERE [bird_id] = ? OR [accessory_id] = ? \n";
                pst = cnn.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, id);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    number = rs.getInt("Amount");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
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
        return number;
    }

    public StarDTO getRatingByIdProduct(String id) {
        StarDTO s = null;
        Connection cnn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "WITH RankedStar AS (	\n"
                        + "SELECT COUNT(CASE WHEN f.rating = 1 THEN 1 END) AS [oneStar],  COUNT(CASE WHEN f.rating = 2 THEN 1 END) AS [twoStar],\n"
                        + "			COUNT(CASE WHEN f.rating = 3 THEN 1 END) AS [threeStar],COUNT(CASE WHEN f.rating = 4 THEN 1 END) AS [fourStar],\n"
                        + "			COUNT(CASE WHEN f.rating = 5 THEN 1 END) AS [fiveStar]\n"
                        + "FROM [Feedback] f\n"
                        + "RIGHT JOIN [OrderItem] o\n"
                        + "ON o.order_item_id = f.order_item_id\n"
                        + "LEFT JOIN [Bird] b\n"
                        + "ON o.bird_id = b.bird_id\n"
                        + "LEFT JOIN [Accessory] a\n"
                        + "ON o.accessory_id = a.accessory_id\n"
                        + "WHERE o.bird_id = ? OR a.accessory_id = ?\n"
                        + "GROUP BY f.rating \n"
                        + ")\n"
                        + "SELECT SUM(rs.oneStar) AS [totalOneStar],SUM(rs.twoStar) AS [totalTwoStar],\n"
                        + "		SUM(rs.threeStar) AS [totalThreeStar],SUM(rs.fourStar) AS [totalFourStar],\n"
                        + "		SUM(rs.fiveStar) AS [totalFiveStar]\n"
                        + "FROM RankedStar rs";
                pst = cnn.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, id);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int oneStar = rs.getInt("totalOneStar");
                    int twoStar = rs.getInt("totalTwoStar");
                    int threeStar = rs.getInt("totalThreeStar");
                    int fourStar = rs.getInt("totalFourStar");
                    int fiveStar = rs.getInt("totalFiveStar");
                    s = new StarDTO(oneStar, twoStar, threeStar, fourStar, fiveStar);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
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
        return s;
    }
      public boolean addFeedback(Feedback fb) throws SQLException, ParseException, ClassNotFoundException {
        Connection con = null;
        
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                LocalDateTime customDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                String sqlDate = customDateTime.format(formatter);
                stm = con.prepareStatement("INSERT INTO [dbo].[Feedback]\n"
                        + "             ([customer],[order_item_id],[rating],[comment],[feedback_date])"
                        + "             VALUES (?,?,?,?,?) ");
                stm.setString(1, fb.getCustomer());
                stm.setInt(2, fb.getOrder_item_id());
                stm.setInt(3, fb.getRating());
                stm.setString(4, fb.getComment());
                stm.setString(5, sqlDate);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
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
        return false;
    }
      
    public ArrayList<Feedback> getFeedbackByCustomer(String customer) {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql =    "SELECT TOP (1000) [feedback_id],[customer],[order_item_id],\n" +
                                "[rating],[comment],[feedback_date]\n" +
                                "FROM [BirdFarmShop].[dbo].[Feedback]\n" +
                                "WHERE [customer] = ?";
                pst = cnn.prepareStatement(sql);
                pst.setString(1, customer);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String feedback_id = rs.getString("feedback_id");
                    int order_item_id = rs.getInt("order_item_id");
                    int rating = rs.getInt("rating");
                    String commment = rs.getString("comment");
                    Date feedback_date = rs.getDate("feedback_date");
                    Feedback feedback = new Feedback(feedback_id, customer, order_item_id, rating, commment, feedback_date);
                    feedbacks.add(feedback);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (cnn != null) {
                try {
                    cnn.close();
                } catch (SQLException e) {
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
        }
        return feedbacks;
    }
}
