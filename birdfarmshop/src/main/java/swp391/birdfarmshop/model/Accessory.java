/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author tlminh
 */
public class Accessory {

    private String accessory_id;
    private String accessory_name;
    private String category_id;
    private int unit_price;
    private int stock_quantity;
    private String description;
    private int discount;
    private String status;
    private String image_url;

    public Accessory() {
    }

    public Accessory(String accessory_id, String accessory_name, String category_id, int unit_price, int stock_quantity, String description, int discount, String status, String image_url) {
        this.accessory_id = accessory_id;
        this.accessory_name = accessory_name;
        this.category_id = category_id;
        this.unit_price = unit_price;
        this.stock_quantity = stock_quantity;
        this.description = description;
        this.discount = discount;
        this.status = status;
        this.image_url = image_url;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getAccessory_id() {
        return accessory_id;
    }

    public void setAccessory_id(String accessory_id) {
        this.accessory_id = accessory_id;
    }

    public String getAccessory_name() {
        return accessory_name;
    }

    public void setAccessory_name(String accessory_name) {
        this.accessory_name = accessory_name;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
