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
//import java.util.List;
//import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.Image;
import swp391.birdfarmshop.util.DBUtils;

/**
 *
 * @author tlminh
 */
public class ImageDAO {

    private static final String GET_THUMBNAIL_BY_BIRD_ID = "SELECT [image_url] FROM [BirdFarmShop].[dbo].[Image] WHERE [bird_id] = ? AND [is_thumbnail] = 1";
    private static final String GET_IMAGES_BY_BIRD_ID = "SELECT [image_url] FROM [BirdFarmShop].[dbo].[Image] WHERE [bird_id] = ?";
    private static final String GET_IMAGES_BY_PAIR_ID = "SELECT [image_id],[image_url] FROM [BirdFarmShop].[dbo].[Image] WHERE [pair_id] = ? ORDER BY image_id DESC";
    private static final String GET_THUMBNAIL_BY_ACCESSORY_ID = "SELECT [image_url] FROM [BirdFarmShop].[dbo].[Image] WHERE [accessory_id] = ? AND [is_thumbnail] = 1";
    private static final String GET_IMAGES_BY_ACCESSORY_ID = "SELECT [image_url] FROM [BirdFarmShop].[dbo].[Image] WHERE [accessory_id] = ?";
    private static final String GET_THUMBNAIL_BY_BIRDNEST_ID = "SELECT [image_url] FROM [BirdFarmShop].[dbo].[Image] WHERE [nest_id] = ? AND [is_thumbnail] = 1";
    private static final String GET_IMAGES_BY_BIRDNEST_ID = "SELECT [image_url] FROM [BirdFarmShop].[dbo].[Image] WHERE [nest_id] = ?";

    public String getThumbnailUrlByBirdId(String birdId) throws SQLException {
        String url = "";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_THUMBNAIL_BY_BIRD_ID);
                stm.setString(1, birdId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    url = rs.getString("image_url");
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
        return url;
    }

    public ArrayList<String> getImagesByBirdId(String birdId) throws SQLException {
        ArrayList<String> urls = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_IMAGES_BY_BIRD_ID);
                stm.setString(1, birdId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String url = rs.getString("image_url");
                    urls.add(url);
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
        return urls;
    }

    public ArrayList<String> getImagesByAccessoryId(String accessoryId) throws SQLException {
        ArrayList<String> urls = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_IMAGES_BY_ACCESSORY_ID);
                stm.setString(1, accessoryId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String url = rs.getString("image_url");
                    urls.add(url);
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
        return urls;
    }

    public ArrayList<String> getImagesByBirdNestId(String birdnestId) throws SQLException {
        ArrayList<String> urls = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_IMAGES_BY_BIRDNEST_ID);
                stm.setString(1, birdnestId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String url = rs.getString("image_url");
                    urls.add(url);
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
        return urls;
    }

    public String getThumbnailUrlByAccessoryId(String accessoryId) throws SQLException {
        String url = "";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_THUMBNAIL_BY_ACCESSORY_ID);
                stm.setString(1, accessoryId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    url = rs.getString("image_url");
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
        return url;
    }

    public ArrayList<String> getUrlByAccessoryId(String accessoryId) throws SQLException {
        ArrayList<String> url = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String urls = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(" select [image_url]\n"
                        + "  FROM [BirdFarmShop].[dbo].[Image]\n"
                        + "  where [accessory_id] = ? and [is_thumbnail] = 0");
                stm.setString(1, accessoryId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    urls = rs.getString("image_url");
                    url.add(urls);
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
        return url;
    }

    public String getThumbnailUrlByBirdNestId(String birdNestID) throws SQLException {
        String url = "";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_THUMBNAIL_BY_BIRDNEST_ID);
                stm.setString(1, birdNestID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    url = rs.getString("image_url");
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
        return url;
    }

    public boolean addNewImageBird(String url, String is_thumbnail, String bird_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean is_thumbnaill;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("INSERT INTO [dbo].[Image]\n"
                        + "           ([image_url],[is_thumbnail],[bird_id],[nest_id],[accessory_id])\n"
                        + "           VALUES (?,?,?,?,?)");
                stm.setString(1, url);
                is_thumbnaill = is_thumbnail.equals("1");
                stm.setBoolean(2, is_thumbnaill);
                stm.setString(3, bird_id);
                stm.setString(4, null);
                stm.setString(5, null);

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

    public boolean addNewImageBirdNest(String url, String is_thumbnail, String nest_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean is_thumbnaill;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("INSERT INTO [dbo].[Image]\n"
                        + "           ([image_url],[is_thumbnail],[bird_id],[nest_id],[accessory_id])\n"
                        + "           VALUES (?,?,?,?,?)");
                stm.setString(1, url);
                if (is_thumbnail.equals("1")) {
                    is_thumbnaill = true;
                } else {
                    is_thumbnaill = false;
                }
                stm.setBoolean(2, is_thumbnaill);
                stm.setString(3, null);
                stm.setString(4, nest_id);
                stm.setString(5, null);
                rs = stm.executeQuery();
                if (rs.next()) {
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
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public boolean addNewAccessoryImage(String url, boolean type, String accessoryID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("INSERT INTO [BirdFarmShop].[dbo].[Image] ([image_url], [is_thumbnail], [bird_id], [nest_id], [accessory_id])\n"
                        + " VALUES(?, ?, ?, ?, ?)");
                stm.setString(1, url);
                stm.setBoolean(2, type);
                stm.setString(3, null);
                stm.setString(4, null);
                stm.setString(5, accessoryID);

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

    public boolean updateImageAccessory(String txtAccessoryID, boolean type, String urlImage, String ImageID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                if (ImageID != null) {
                    stm = con.prepareStatement(" UPDATE [BirdFarmShop].[dbo].[Image]\n"
                            + "  SET [image_url] = ? \n"
                            + "  WHERE [accessory_id] = ? AND is_thumbnail = ? AND [image_id] = ?");
                } else {
                    stm = con.prepareStatement(" UPDATE [BirdFarmShop].[dbo].[Image]\n"
                            + "  SET [image_url] = ? \n"
                            + "  WHERE [accessory_id] = ? AND is_thumbnail = ?");
                }
                stm.setString(1, urlImage);
                stm.setString(2, txtAccessoryID);
                stm.setBoolean(3, type);
                if(ImageID != null){
                    stm.setInt(4, Integer.parseInt(ImageID));
                }
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

    public boolean updateThumbnailImageAccessory(String txtAccessoryID, boolean type, String urlImage) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(" UPDATE [BirdFarmShop].[dbo].[Image]\n"
                        + "  SET [image_url] = ? \n"
                        + "  WHERE [accessory_id] = ? AND is_thumbnail = ?");
                stm.setString(1, urlImage);
                stm.setString(2, txtAccessoryID);
                stm.setBoolean(3, type);
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

    public ArrayList<Image> getImageByAccessoryId(String accessoryId) throws SQLException {
        ArrayList<Image> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("  SELECT [image_id] ,[image_url] ,[is_thumbnail], [bird_id], [nest_id], [accessory_id]\n"
                        + "  FROM [BirdFarmShop].[dbo].[Image]\n"
                        + "  WHERE [accessory_id] = ? AND [is_thumbnail] = 0");
                stm.setString(1, accessoryId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int image_id = rs.getInt("image_id");
                    String image_url = rs.getString("image_url");
                    boolean is_thumbnail = rs.getBoolean("is_thumbnail");
                    String bird_id = rs.getString("bird_id");
                    String nest_id = rs.getString("nest_id");
                    String accessory_id = rs.getString("accessory_id");
                    Image image = new Image(image_id, image_url, is_thumbnail, bird_id, nest_id, accessory_id);
                    list.add(image);
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
        return list;
    }

    public boolean updateImageBird(String url, String bird_id, int image_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("UPDATE [dbo].[Image]\n"
                        + "SET [image_url] = ?\n"
                        + "WHERE [bird_id] = ? AND [image_id] = ?");
                stm.setString(1, url);
                stm.setString(2, bird_id);
                stm.setInt(3, image_id);
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

    public boolean updateImageBirdNest(String url, String nest_id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("UPDATE [dbo].[Image]\n"
                        + "                 SET [image_url] = ?\n"
                        + "                 WHERE [nest_id] = ?");
                stm.setString(1, url);
                stm.setString(2, nest_id);

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

    public ArrayList<Image> getImagesFollowBirdId(String birdId) throws SQLException {
        ArrayList<Image> images = new ArrayList<>();
        Image img;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement("SELECT [image_id],[image_url],[is_thumbnail],[bird_id],[nest_id],[accessory_id]\n"
                        + "FROM [BirdFarmShop].[dbo].[Image]\n"
                        + "WHERE [bird_id] = ? ORDER BY [image_id] ASC");
                stm.setString(1, birdId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int image_id = rs.getInt("image_id");
                    String image_url = rs.getString("image_url");
                    boolean is_thumbnail = rs.getBoolean("is_thumbnail");
                    String nest_id = rs.getString("nest_id");
                    String accessory_id = rs.getString("accessory_id");
                    img = new Image(image_id, image_url, is_thumbnail, birdId, nest_id, accessory_id);
                    images.add(img);
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
        return images;
    }

    public ArrayList<String> getImagesByPairId(int pairId) throws SQLException {
        ArrayList<String> urls = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                stm = con.prepareStatement(GET_IMAGES_BY_PAIR_ID);
                stm.setInt(1, pairId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String url = rs.getString("image_url");
                    urls.add(url);
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
        return urls;
    }
    public static void main(String[] args) throws SQLException {
        ImageDAO dao = new ImageDAO();
        boolean check = dao.updateImageBird("data:image/jpeg;base64,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", "CP111", 114);
        System.out.println(check);
    }
}
