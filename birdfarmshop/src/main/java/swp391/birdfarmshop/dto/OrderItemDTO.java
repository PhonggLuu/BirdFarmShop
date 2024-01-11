/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdNest;

/**
 *
 * @author tlminh
 */
public class OrderItemDTO {
    private int order_item_id;
    private String order_id;
    Bird bird;
    Accessory accessory;
    BirdNest birdNest;
    BirdPairDTO birdPair;
    private int unit_price;
    private int order_quantity;

    public OrderItemDTO(int order_item_id, String order_id, Bird bird, Accessory accessory, BirdNest birdNest, BirdPairDTO birdPair, int unit_price, int order_quantity) {
        this.order_item_id = order_item_id;
        this.order_id = order_id;
        this.bird = bird;
        this.accessory = accessory;
        this.birdNest = birdNest;
        this.birdPair = birdPair;
        this.unit_price = unit_price;
        this.order_quantity = order_quantity;
    }

    public int getOrder_item_id() {
        return order_item_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public Bird getBird() {
        return bird;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public BirdNest getBirdNest() {
        return birdNest;
    }

    public BirdPairDTO getBirdPair() {
        return birdPair;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }
}