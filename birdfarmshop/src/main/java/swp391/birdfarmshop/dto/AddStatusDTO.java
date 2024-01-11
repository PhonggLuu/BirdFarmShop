/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

/**
 *
 * @author Admin
 */
public class AddStatusDTO {

    String status;
    String content;
    int quantity;
    String type;
    int totalPriceAccessory;
    int totalPrice;
    int totalAccessory;
    public AddStatusDTO() {
        quantity = 0;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalPriceAccessory() {
        return totalPriceAccessory;
    }

    public void setTotalPriceAccessory(int totalPriceAccessory) {
        this.totalPriceAccessory = totalPriceAccessory;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalAccessory() {
        return totalAccessory;
    }

    public void setTotalAccessory(int totalAccessory) {
        this.totalAccessory = totalAccessory;
    }
    
}
