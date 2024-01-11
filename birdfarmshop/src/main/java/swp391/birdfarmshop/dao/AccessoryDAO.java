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
import java.util.List;
import swp391.birdfarmshop.dto.AccessoryDTO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.AccessoryCategory;
//import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author tlminh
 */
public class AccessoryDAO {

    private static final String GET_ACCESSORY_BY_ID = "SELECT * FROM Accessory WHERE accessory_id = ?";

    public ArrayList<Accessory> getAccessories() throws SQLException {
        ArrayList<Accessory> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ImageDAO ID = new ImageDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM [BirdFarmShop].[dbo].[Accessory]";
                st = con.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    String accessory_id = rs.getString("accessory_id");
                    String accessory_name = rs.getString("accessory_name");
                    String category_id = rs.getString("category_id");
                    int unit_price = rs.getInt("unit_price");
                    int stock_quantity = rs.getInt("stock_quantity");
                    String description = rs.getString("description");
                    int discount = rs.getInt("discount");
                    String status = rs.getString("status");
                    String image_url = ID.getThumbnailUrlByAccessoryId(accessory_id);
                    list.add(new Accessory(accessory_id, accessory_name, category_id, unit_price, stock_quantity, description, discount, status, image_url));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public ArrayList<Accessory> getAccessoriesCustom(String search, String category, String price, String page, int recordsPerPage) throws SQLException {
        ArrayList<Accessory> list = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ImageDAO ID = new ImageDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM [BirdFarmShop].[dbo].[Accessory]\n";
                if (search != null) {
                    sql += " WHERE accessory_name LIKE N'%" + search + "%'\n";
                    if (category != null) {
                        sql += " AND category_id = " + "'" + category + "'" + "\n";
                    }
                    if (price != null) {
                        sql += " AND " + price + "\n";
                    }
                }
                if (search == null && category != null) {
                    sql += " WHERE category_id = " + "'" + category + "'" + "\n";
                    if (price != null) {
                        sql += " AND " + price + "\n";
                    }
                }
                if (search == null && category == null
                        && price != null) {
                    sql += " WHERE " + price + "\n";
                }
                if (page != null) {
                    int pageNumber = Integer.parseInt(page);
                    int start = (pageNumber - 1) * recordsPerPage;
                    sql += "ORDER BY (SELECT NULL) OFFSET " + start + " ROWS FETCH NEXT " + recordsPerPage + " ROWS ONLY";
                }
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    String accessory_id = rs.getString("accessory_id");
                    String accessory_name = rs.getString("accessory_name");
                    String category_id = rs.getString("category_id");
                    int unit_price = rs.getInt("unit_price");
                    int stock_quantity = rs.getInt("stock_quantity");
                    String description = rs.getString("description");
                    int discount = rs.getInt("discount");
                    String status = rs.getString("status");
                    String image_url = ID.getThumbnailUrlByAccessoryId(accessory_id);
                    list.add(new Accessory(accessory_id, accessory_name, category_id, unit_price, stock_quantity, description, discount, status, image_url));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public int totalAccessories(String search, String category, String price) throws SQLException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int number = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(accessory_id)AS Amount FROM [BirdFarmShop].[dbo].[Accessory]\n";
                if (search != null) {
                    sql += " WHERE accessory_name LIKE N'%" + search + "%'\n";
                    if (category != null) {
                        sql += " AND category_id = " + "'" + category + "'" + "\n";
                    }
                    if (price != null) {
                        sql += " AND " + price + "\n";
                    }
                }
                if (search == null && category != null) {
                    sql += " WHERE category_id = " + "'" + category + "'" + "\n";
                    if (price != null) {
                        sql += " AND " + price + "\n";
                    }
                }
                if (search == null && category == null
                        && price != null) {
                    sql += " WHERE " + price + "\n";
                }
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs != null && rs.next()) {
                    number = rs.getInt("Amount");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return number;
    }

    public AccessoryDTO getAccessoryDetailsByID(String accessory_id) throws SQLException {
        AccessoryDTO a = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ImageDAO ID = new ImageDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM [BirdFarmShop].[dbo].[Accessory] WHERE [accessory_id] = ?";
                st = con.prepareStatement(sql);
                st.setString(1, accessory_id);
                rs = st.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("accessory_id");
                    String accessory_name = rs.getString("accessory_name");
                    String category_id = rs.getString("category_id");
                    int unit_price = rs.getInt("unit_price");
                    int stock_quantity = rs.getInt("stock_quantity");
                    String description = rs.getString("description");
                    int discount = rs.getInt("discount");
                    String status = rs.getString("status");
                    ArrayList<String> image_url = ID.getImagesByAccessoryId(accessory_id);
                    a = new AccessoryDTO(id, accessory_name, category_id, unit_price, stock_quantity, description, discount, status, image_url);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return a;
    }

    public Accessory getAccessoryByID(String accessoryId) throws SQLException {
        Accessory accessory = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ImageDAO imgDao = new ImageDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ACCESSORY_BY_ID);
                stm.setString(1, accessoryId);
                rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    String accessory_id = rs.getString("accessory_id");
                    String accessory_name = rs.getString("accessory_name");
                    String category_id = rs.getString("category_id");
                    int unit_price = rs.getInt("unit_price");
                    int stock_quantity = rs.getInt("stock_quantity");
                    String description = rs.getString("description");
                    int discount = rs.getInt("discount");
                    String status = rs.getString("status");
                    String image_url = imgDao.getThumbnailUrlByAccessoryId(accessory_id);
                    accessory = new Accessory(accessory_id, accessory_name, category_id, unit_price, stock_quantity, description, discount, status, image_url);
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
        return accessory;
    }

    public List<Accessory> getCageList() throws SQLException {
        List<Accessory> cages = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ImageDAO imgDao = new ImageDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("SELECT *\n"
                        + "FROM [BirdFarmShop].[dbo].[Accessory]\n"
                        + "WHERE [category_id] = 'cage' ORDER BY [unit_price]");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String accessory_id = rs.getString("accessory_id");
                    String accessory_name = rs.getString("accessory_name");
                    String category_id = rs.getString("category_id");
                    int unit_price = rs.getInt("unit_price");
                    int stock_quantity = rs.getInt("stock_quantity");
                    String description = rs.getString("description");
                    int discount = rs.getInt("discount");
                    String status = rs.getString("status");
                    String image_url = imgDao.getThumbnailUrlByAccessoryId(accessory_id);
                    cages.add(new Accessory(accessory_id, accessory_name, category_id, unit_price, stock_quantity, description, discount, status, image_url));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions here, e.g., log or throw
        } finally {
            // Close resources in the finally block
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return cages;
    }

    public boolean insertAccessory(String txtAccessoryID, String txtAccessoryName, String txtCategoryID, String txtPrice, String txtStockQuantity, String txtDescribe, String txtDiscount, String imageURL_1, String imageURL_2, String imageURL_3) throws SQLException {
        String status;
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        ImageDAO im = new ImageDAO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("INSERT INTO [BirdFarmShop].[dbo].[Accessory]\n"
                        + "           ([accessory_id]\n"
                        + "           ,[accessory_name]\n"
                        + "           ,[category_id]\n"
                        + "           ,[unit_price]\n"
                        + "           ,[stock_quantity]\n"
                        + "           ,[description]\n"
                        + "           ,[discount]\n"
                        + "           ,[status])\n"
                        + "     VALUES\n"
                        + "           (?, ?, ?, ?, ?, ?, ?, ?)");
                stm.setString(1, txtAccessoryID);
                stm.setString(2, txtAccessoryName);
                stm.setString(3, txtCategoryID);
                if (txtCategoryID.equals("other")) {
                    AccessoryCategoryDAO dao = new AccessoryCategoryDAO();
                    List<AccessoryCategory> list = dao.getAccessoryCategories();
                    boolean check = false;

                    for (AccessoryCategory a : list) {
                        if (a.getCategory_id().equalsIgnoreCase(txtCategoryID)) {
                            check = true;
                        }
                    }

                    if (!check) {
                        boolean createCategory = dao.createCategory(txtCategoryID);
                    }
                }
                stm.setInt(4, Integer.parseInt(txtPrice));
                stm.setInt(5, Integer.parseInt(txtStockQuantity));
                stm.setString(6, txtDescribe);
                stm.setInt(7, Integer.parseInt(txtDiscount));
                if (Integer.parseInt(txtStockQuantity) > 0) {
                    status = "còn hàng";
                } else {
                    status = "hết hàng";
                }
                stm.setString(8, status);

                rs = stm.executeUpdate();

                boolean checkImage_1 = im.addNewAccessoryImage(imageURL_1, true, txtAccessoryID);
                boolean checkImage_2 = im.addNewAccessoryImage(imageURL_2, false, txtAccessoryID);
                boolean checkImage_3 = im.addNewAccessoryImage(imageURL_3, false, txtAccessoryID);
                if (rs > 0) {
                    return true;
                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions here, e.g., log or throw
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

    public boolean updateAccessory(String txtAccessoryID, String txtAccessoryName, String txtCategoryID, String txtPrice, String txtStockQuantity, String txtDescribe, String txtDiscount) throws SQLException {
        String status;
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("  UPDATE [BirdFarmShop].[dbo].[Accessory]\n"
                        + "    SET  "
                        + "    [accessory_name] = ?,\n"
                        + "    [category_id] = ?,\n"
                        + "    [unit_price] = ?,\n"
                        + "    [stock_quantity] = ?,\n"
                        + "    [description] = ?,\n"
                        + "    [discount] = ?,\n"
                        + "    [status] = ?"
                        + "    WHERE [accessory_id] = ?; ");

                stm.setString(1, txtAccessoryName);
                stm.setString(2, txtCategoryID);
                stm.setInt(3, Integer.parseInt(txtPrice));
                stm.setInt(4, Integer.parseInt(txtStockQuantity));
                stm.setString(5, txtDescribe);
                stm.setInt(6, Integer.parseInt(txtDiscount));
                if (Integer.parseInt(txtStockQuantity) > 0) {
                    stm.setString(7, "còn hàng");
                } else {
                    stm.setString(7, "hết hàng");
                }

                stm.setString(8, txtAccessoryID);
                rs = stm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions here, e.g., log or throw
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

    public boolean updateAccessoryQuantity(String txtAccessoryID, String txtStockQuantity) throws SQLException {
        String status;
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("UPDATE [BirdFarmShop].[dbo].[Accessory] SET [stock_quantity] = ?, [status] = ? WHERE [accessory_id] = ?;");

                stm.setInt(1, Integer.parseInt(txtStockQuantity));
                if (Integer.parseInt(txtStockQuantity) > 0) {
                    stm.setString(2, "còn hàng");
                } else {
                    stm.setString(2, "hết hàng");
                }

                stm.setString(3, txtAccessoryID);
                rs = stm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle exceptions here, e.g., log or throw
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

    public ArrayList<Integer> getQuantityByStatus() throws SQLException {
        ArrayList<Integer> accessory = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("SELECT COUNT([accessory_id]) AS totalAccessory,\n"
                        + "	   SUM(CASE WHEN [stock_quantity] > 0 THEN 1 END) AS Available,\n"
                        + "	   COUNT(CASE WHEN [stock_quantity] = 0 THEN 1 END) AS OutOfStock\n"
                        + "FROM [BirdFarmShop].[dbo].[Accessory]");
            }
            rs = stm.executeQuery();
            if(rs!= null && rs.next()){
                accessory.add(rs.getInt("totalAccessory"));
                accessory.add(rs.getInt("Available"));
                accessory.add(rs.getInt("OutOfStock"));
            }
        } catch (ClassNotFoundException e ) {

        }finally {
            if(rs != null){
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return accessory;
    }
}
