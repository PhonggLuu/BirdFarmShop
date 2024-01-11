/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author tlminh
 */
public class Image {
    private int image_id;
    private String image_url;
    private boolean is_thumbnail;
    private String bird_id;
    private String nest_id;
    private String accessory_id;
    
    public Image() {
    }

    public Image(int image_id, String image_url, boolean is_thumbnail, String bird_id, String nest_id, String accessory_id) {
        this.image_id = image_id;
        this.image_url = image_url;
        this.is_thumbnail = is_thumbnail;
        this.bird_id = bird_id;
        this.nest_id = nest_id;
        this.accessory_id = accessory_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isIs_thumbnail() {
        return is_thumbnail;
    }

    public void setIs_thumbnail(boolean is_thumbnail) {
        this.is_thumbnail = is_thumbnail;
    }

    public String getBird_id() {
        return bird_id;
    }

    public void setBird_id(String bird_id) {
        this.bird_id = bird_id;
    }

    public String getNest_id() {
        return nest_id;
    }

    public void setNest_id(String nest_id) {
        this.nest_id = nest_id;
    }

    public String getAccessory_id() {
        return accessory_id;
    }

    public void setAccessory_id(String accessory_id) {
        this.accessory_id = accessory_id;
    }


    }
