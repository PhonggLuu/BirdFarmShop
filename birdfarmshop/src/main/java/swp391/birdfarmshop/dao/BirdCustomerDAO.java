/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import swp391.birdfarmshop.model.BirdCustomer;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author Admin
 */
public class BirdCustomerDAO {

    public int createNewBirdCustomer(String bird_id, String name, String gender, String breed_id,
            String username, String image_url, String status) {
        int result = 0;
        Connection cnn = null;
        PreparedStatement pst = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "INSERT INTO [CustomerBird]([customer_id],[bird_name],"
                        + "               [breed_id],[gender],[status],[image_url]) \n"
                        + "   VALUES (?,?,?,?,?,?)";
                pst = cnn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, name);
                pst.setString(3, breed_id);
                pst.setString(4, gender);
                pst.setString(5, status);
                pst.setString(6, image_url);
                result = pst.executeUpdate();
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
        }
        return result;
    }

    public ArrayList<BirdCustomer> getBirdCustomer(String search, String page, int recordsPerPage) {
        ArrayList<BirdCustomer> birdCustomerList = new ArrayList<>();
        Connection cnn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [bird_id],[bird_name],[gender],[breed_id],\n"
                        + "	     [customer_id],[image_url],[status]\n"
                        + "FROM CustomerBird\n";
                if (search != null) {
                    sql += " WHERE bird_name LIKE N'%" + search + "%'\n";
                }
                if (page != null) {
                    if (page != null) {
                        int pageNumber = Integer.parseInt(page);
                        int start = (pageNumber - 1) * recordsPerPage;
                        sql += "ORDER BY (SELECT NULL) OFFSET " + start + " ROWS FETCH NEXT " + recordsPerPage + " ROWS ONLY";
                    }
                }
                st = cnn.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    int bird_id = rs.getInt("bird_id");
                    String bird_name = rs.getString("bird_name");
                    boolean gender = rs.getBoolean("gender");
                    String breed_id = rs.getString("breed_id");
                    String customer = rs.getString("customer_id");
                    String img_url = rs.getString("image_url");
                    String status = rs.getString("status");
                    BirdCustomer b = new BirdCustomer(bird_id, bird_name, gender, breed_id, customer, img_url, status);
                    birdCustomerList.add(b);
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
        return birdCustomerList;
    }

    public ArrayList<BirdCustomer> getBirdCustomerByCustomer(String customer, String breedId, String page, int recordsPerPage) {
        ArrayList<BirdCustomer> birdCustomerList = new ArrayList<>();
        Connection cnn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [bird_id],[bird_name],[gender],[breed_id],\n"
                        + "	     [customer_id],[image_url],[status]\n"
                        + "FROM CustomerBird\n";
                if (customer != null) {
                    sql += " WHERE customer_id = '" + customer + "'\n";
                    if (breedId != null) {
                        sql += " AND breed_id = '" + breedId + "'\n";
                    }
                }
                if (customer == null && breedId != null) {
                    sql += " WHERE breed_id = '" + breedId + "'\n";
                }
                if (page != null) {
                    if (page != null) {
                        int pageNumber = Integer.parseInt(page);
                        int start = (pageNumber - 1) * recordsPerPage;
                        sql += "ORDER BY (SELECT NULL) OFFSET " + start + " ROWS FETCH NEXT " + recordsPerPage + " ROWS ONLY";
                    }
                }
                st = cnn.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    int bird_id = rs.getInt("bird_id");
                    String bird_name = rs.getString("bird_name");
                    boolean gender = rs.getBoolean("gender");
                    String breed_id = rs.getString("breed_id");
                    String img_url = rs.getString("image_url");
                    String status = rs.getString("status");
                    BirdCustomer b = new BirdCustomer(bird_id, bird_name, gender, breed_id, customer, img_url, status);
                    birdCustomerList.add(b);
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
        return birdCustomerList;
    }

    public BirdCustomer findBirdCustomer(String birdId) {
        Connection cnn = null;
        BirdCustomer b = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [bird_id],[bird_name],[gender],[breed_id],\n"
                        + "	     [customer_id],[image_url],[status]\n"
                        + "FROM CustomerBird\n"
                        + "WHERE bird_id = ?";
                pst = cnn.prepareStatement(sql);
                pst.setString(1, birdId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int bird_id = rs.getInt("bird_id");
                    String bird_name = rs.getString("bird_name");
                    boolean gender = rs.getBoolean("gender");
                    String breed_id = rs.getString("breed_id");
                    String username = rs.getString("customer_id");
                    String img_url = rs.getString("image_url");
                    String status = rs.getString("status");
                    b = new BirdCustomer(bird_id, bird_name, gender, breed_id, username, img_url, status);
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
        return b;
    }

    public boolean updateBirdCustomerStatus(String customer_id, String status) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "UPDATE [BirdFarmShop].[dbo].[CustomerBird] SET [status] = ? WHERE [customer_id] = ?";
                stm = con.prepareStatement(query);
                stm.setString(1, status);
                stm.setString(2, customer_id);
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
        public ArrayList<BirdCustomer> getBirdCustomerByCustomer(String customer) {
        ArrayList<BirdCustomer> birdCustomerList = new ArrayList<>();
        Connection cnn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cnn = DBUtils.getConnection();
            if (cnn != null) {
                String sql = "SELECT [bird_id],[bird_name],[gender],cb.[breed_id],\n"
                        + "	     [customer_id],[image_url],[status],bb.[breed_name]\n"
                        + "FROM CustomerBird cb\n"
                        + "LEFT JOIN BirdBreed bb\n"
                        + "ON cb.[breed_id] = bb.[breed_id]";
                if (customer != null) {
                    sql += " WHERE customer_id = '" + customer + "'\n";
                }
                st = cnn.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    int bird_id = rs.getInt("bird_id");
                    String bird_name = rs.getString("bird_name");
                    boolean gender = rs.getBoolean("gender");
                    String breed_id = rs.getString("breed_id");
                    String img_url = rs.getString("image_url");
                    String status = rs.getString("status");
                    String breed_name = rs.getString("breed_name");
                    BirdCustomer b = new BirdCustomer(bird_id, bird_name, gender, breed_id, customer, img_url, status,breed_name);
                    birdCustomerList.add(b);
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
        return birdCustomerList;
    }
}
