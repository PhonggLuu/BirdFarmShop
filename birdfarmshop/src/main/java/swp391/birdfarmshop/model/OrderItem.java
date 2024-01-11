/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author tlminh
 */
public class OrderItem {
    private int order_item_id;
    private String order_id;
    private String bird_id;
    private String nest_id;
    private String accessory_id;
    private int unit_price;
    private int order_quantity;
    
    public OrderItem() {
    }

    public OrderItem(int order_item_id, String order_id, String bird_id, String nest_id, String accessory_id, int unit_price, int order_quantity) {
        this.order_item_id = order_item_id;
        this.order_id = order_id;
        this.bird_id = bird_id;
        this.nest_id = nest_id;
        this.accessory_id = accessory_id;
        this.unit_price = unit_price;
        this.order_quantity = order_quantity;
    }

    public int getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(int order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity = order_quantity;
    }
    
    
}
