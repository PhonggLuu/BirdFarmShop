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
import java.util.List;
import swp391.birdfarmshop.model.AccessoryCategory;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author tlminh
 */
public class AccessoryCategoryDAO {

    private static final String GET_ACCESSORY_CATEGORY_LIST = "SELECT * FROM [AccessoryCategory]";
    private static final String GET_ACCESSORY_CATEGORY_NAME_BY_ID = "SELECT [category_name] FROM [AccessoryCategory] WHERE [category_id] = ?";

    public List<AccessoryCategory> getAccessoryCategories() throws SQLException {
        List<AccessoryCategory> categoryList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ACCESSORY_CATEGORY_LIST);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String category_id = rs.getString("category_id");
                    String category_name = rs.getString("category_name");
                    String category_thumbnail = rs.getString("category_thumbnail");
                    AccessoryCategory category = new AccessoryCategory(category_id, category_name, category_thumbnail);
                    categoryList.add(category);
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
        }
        return categoryList;
    }

    public String getAccessoryCategoryNameById(String category_id) throws SQLException {
        String category_name = "";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_ACCESSORY_CATEGORY_NAME_BY_ID);
                stm.setString(1, category_id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    category_name = rs.getString("category_name");
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
        }
        return category_name;
    }

    public boolean createCategory(String txtCategoryid) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("INSERT INTO [BirdFarmShop].[dbo].[AccessoryCategory]\n"
                        + "                             ([category_id]\n"
                        + "                             ,[category_name]\n"
                        + "                             ,[category_thumbnail])\n"
                        + "                 VALUES (?, ?, ?)");
                stm.setString(1, txtCategoryid);
                stm.setString(2, "Khác");
                stm.setString(3, "https://lzd-img-global.slatic.net/g/p/2860326d7ccced8d9803f392b9929ad2.jpg");
                rs = stm.executeUpdate();
                if (rs > 0) {
                    return true;
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
        }
        return false;
    }
    
    public static void main(String[] args) throws SQLException {
        AccessoryCategoryDAO d = new AccessoryCategoryDAO();
        System.out.println(d.createCategory("other"));
    }
}
