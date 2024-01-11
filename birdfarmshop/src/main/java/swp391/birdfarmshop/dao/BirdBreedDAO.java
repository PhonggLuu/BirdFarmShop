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
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdBreed;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author tlminh
 */
public class BirdBreedDAO {

    private static final String GET_BIRD_BREED_LIST = "SELECT * FROM BirdBreed";
    private static final String GET_BREED_NAME_BY_ID = "SELECT [breed_name] FROM [BirdBreed] WHERE [breed_id] = ?";

    public List<BirdBreed> getBirdBreeds() throws SQLException {
        List<BirdBreed> birdBreedList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BIRD_BREED_LIST);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String breed_id = rs.getString("breed_id");
                    String breed_name = rs.getString("breed_name");
                    String breed_thumbnail = rs.getString("breed_thumbnail");
                    BirdBreed birdBreed = new BirdBreed(breed_id, breed_name, breed_thumbnail);
                    birdBreedList.add(birdBreed);
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
        return birdBreedList;
    }

    public String getBreedNameById(String breed_id) throws SQLException {
        String breed_name = "";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_BREED_NAME_BY_ID);
                stm.setString(1, breed_id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    breed_name = rs.getString("breed_name");
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
        return breed_name;
    }
    
}
