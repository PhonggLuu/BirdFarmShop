/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author tlminh
 */
public class AccessoryCategory {

    String category_id;
    String category_name;
    String category_thumbnail;

    public AccessoryCategory() {
    }

    public AccessoryCategory(String category_id, String category_name, String category_thumbnail) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_thumbnail = category_thumbnail;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_thumbnail() {
        return category_thumbnail;
    }

    public void setCategory_thumbnail(String category_thumbnail) {
        this.category_thumbnail = category_thumbnail;
    }

}
