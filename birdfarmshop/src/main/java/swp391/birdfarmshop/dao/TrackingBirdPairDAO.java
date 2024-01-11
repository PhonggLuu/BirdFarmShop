/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import swp391.birdfarmshop.dto.TrackingDTO;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author Admin
 */
public class TrackingBirdPairDAO {

    public ArrayList<TrackingDTO> getTrackingBirdPair(int pair_id) {
        ArrayList<TrackingDTO> trackingList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT [content],[create_user]\n"
                        + "      ,[date_content]\n"
                        + "FROM [BirdFarmShop].[dbo].[PairTracking]\n"
                        + "WHERE [pair_id] = ?\n"
                        + "ORDER BY tracking_id DESC";
                pst = con.prepareStatement(sql);
                pst.setInt(1, pair_id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String content = rs.getString("content");
                    String username = rs.getString("create_user");
                    Timestamp dateTime = rs.getTimestamp("date_content");
                    TrackingDTO t = new TrackingDTO(content, dateTime, username);
                    trackingList.add(t);
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

        return trackingList;
    }

    public int updateTrackingBirdPair(String url, int pair_id, String content, String username, String status,
            String quantity_young_bird, String quantity_egg) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql;
                if (url != null) {
                    sql = "INSERT INTO [Image]([image_url],[pair_id])\n"
                            + "VALUES(?,?)";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, url);
                    pst.setInt(2, pair_id);
                    result = pst.executeUpdate();
                } else {
                    result = 1;
                }
                if (result > 0) {
                    sql = "INSERT INTO [PairTracking]([pair_id],[create_user],[content],[date_content])\n"
                            + "VALUES(?,?,?,?)";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, pair_id);
                    pst.setString(2, username);
                    pst.setString(3, content);
                    Timestamp date = new Timestamp(System.currentTimeMillis());
                    pst.setTimestamp(4, date);
                    result = pst.executeUpdate();
                    if (result > 0) {
                        sql = "UPDATE [BirdPair]\n"
                                + "SET [number_egg] = ?,\n"
                                + "[number_young_bird] = ?,\n"
                                + "[status] = ?\n"
                                + "WHERE [pair_id] = ?";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, quantity_egg);
                        pst.setString(2, quantity_young_bird);
                        pst.setString(3, status);
                        pst.setInt(4, pair_id);
                        result = pst.executeUpdate();
                        if (result > 0) {
                            check = true;
                        }
                    }
                }
                if (check) {
                    result = 1;
                    con.commit();
                } else {
                    result = 0;
                    con.rollback();
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
        }

        return result;
    }

    public int updateTrackingBirdPair(String pair_id, String content, String status) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = "INSERT INTO [PairTracking]([pair_id],[content],[date_content])\n"
                        + "VALUES(?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, pair_id);
                pst.setString(2, content);
                Timestamp date = new Timestamp(System.currentTimeMillis());
                pst.setTimestamp(3, date);
                result = pst.executeUpdate();
                if (result > 0) {
                    sql = "UPDATE [BirdPair]\n"
                            + "SET [status] = ?\n"
                            + "WHERE [pair_id] = ?";
                    pst = con.prepareStatement(sql);
                    pst.setString(1, status);
                    pst.setString(2, pair_id);
                    result = pst.executeUpdate();
                    if (result > 0) {
                        check = true;
                    }
                }
                if ("Đã hủy".equals(status)) {
                    String selectPair = "SELECT [pair_id]\n"
                            + "      ,[order_id]\n"
                            + "      ,[young_bird_price]\n"
                            + "      ,[bird_customer]\n"
                            + "      ,[male_bird_id]\n"
                            + "      ,[female_bird_id]\n"
                            + "      ,[number_egg]\n"
                            + "      ,[number_young_bird]\n"
                            + "      ,[status]\n"
                            + "  FROM [BirdFarmShop].[dbo].[BirdPair]\n"
                            + "  WHERE [pair_id] = ?";
                    pst = con.prepareStatement(selectPair);
                    pst.setString(1, pair_id);
                    rs = pst.executeQuery();
                    if (rs != null && rs.next()) {
                        String idMale = rs.getString("male_bird_id");
                        String idFemale = rs.getString("female_bird_id");
                        String idCustomer = rs.getString("bird_customer");
                        if (idMale != null) {
                            String updateMaleBird = "UPDATE [Bird]\n"
                                    + "SET [status] = N'Còn hàng'\n"
                                    + "WHERE [bird_id] = ?";
                            pst = con.prepareStatement(updateMaleBird);
                            pst.setString(1, idMale);
                            result = pst.executeUpdate();
                            if (result == 0) {
                                check = false;
                            }
                        }
                        if (idFemale != null) {
                            String updateFemaleBird = "UPDATE [Bird]\n"
                                    + "SET [status] = N'Còn hàng'\n"
                                    + "WHERE [bird_id] = ?";
                            pst = con.prepareStatement(updateFemaleBird);
                            pst.setString(1, idFemale);
                            result = pst.executeUpdate();
                            if (result == 0) {
                                check = false;
                            }
                        }
                        if (idCustomer != null) {
                            String updateCustomerBird = "UPDATE [CustomerBird]\n"
                                    + "SET [status] = N'Chưa ghép cặp'\n"
                                    + "WHERE [bird_id] = ?";
                            pst = con.prepareStatement(updateCustomerBird);
                            pst.setString(1, idCustomer);
                            result = pst.executeUpdate();
                            if (result == 0) {
                                check = false;
                            }
                        }
                    } else {
                        check = false;
                    }
                }
                if (check) {
                    result = 1;
                    con.commit();
                } else {
                    result = 0;
                    con.rollback();
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

        return result;
    }

    public boolean insertContent(int pair_id, String username, String content) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                sql = "INSERT INTO [PairTracking]([pair_id],[create_user],[content],[date_content])\n"
                        + "VALUES(?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, pair_id);
                pst.setString(2, username);
                pst.setString(3, content);
                Timestamp date = new Timestamp(System.currentTimeMillis());
                pst.setTimestamp(4, date);
                result = pst.executeUpdate();
                if(result > 0){
                    return true;
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

        return false;
    }
}
