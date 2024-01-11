/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import swp391.birdfarmshop.dao.AccessoryDAO;
import swp391.birdfarmshop.model.Accessory;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdCustomer;
import swp391.birdfarmshop.model.BirdNest;
import swp391.birdfarmshop.model.OrderedAccessoryItem;
import swp391.birdfarmshop.model.OrderedBirdItem;
import swp391.birdfarmshop.model.OrderedBirdPairItem;

/**
 *
 * @author tlminh
 */
public class CartDTO {

    private Map<String, OrderedBirdItem> birdList;
    private Map<String, BirdNest> birdNestList;
    private Map<String, OrderedAccessoryItem> accessoryList;
    private Map<String, OrderedBirdPairItem> birdPairList;
    private int birdPairTotalPrice;
    private int birdTotalPrice;
    private int birdNestTotalPrice;
    private int accessoryTotalPrice;
    private int cartTotalPrice;
    private int totalItem;

    public CartDTO() {
        birdList = new HashMap<>();
        birdNestList = new HashMap<>();
        accessoryList = new HashMap<>();
        birdPairList = new HashMap<>();
        cartTotalPrice = 0;
        totalItem = 0;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public Map<String, OrderedBirdItem> getBirdList() {
        return birdList;
    }

    public void setBirdList(Map<String, OrderedBirdItem> birdList) {
        this.birdList = birdList;
    }

    public Map<String, BirdNest> getBirdNestList() {
        return birdNestList;
    }

    public void setBirdNestList(Map<String, BirdNest> birdNestList) {
        this.birdNestList = birdNestList;
    }

    public Map<String, OrderedAccessoryItem> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(Map<String, OrderedAccessoryItem> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public int getBirdTotalPrice() {
        return birdTotalPrice;
    }

    public void setBirdTotalPrice(int birdTotalPrice) {
        this.birdTotalPrice = birdTotalPrice;
    }

    public int getBirdNestTotalPrice() {
        return birdNestTotalPrice;
    }

    public void setBirdNestTotalPrice(int birdNestTotalPrice) {
        this.birdNestTotalPrice = birdNestTotalPrice;
    }

    public int getAccessoryTotalPrice() {
        return accessoryTotalPrice;
    }

    public void setAccessoryTotalPrice(int accessoryTotalPrice) {
        this.accessoryTotalPrice = accessoryTotalPrice;
    }

    public int getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(int cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Map<String, OrderedBirdPairItem> getBirdPairList() {
        return birdPairList;
    }

    public void setBirdPairList(Map<String, OrderedBirdPairItem> birdPairList) {
        this.birdPairList = birdPairList;
    }

    public int getBirdPairTotalPrice() {
        return birdPairTotalPrice;
    }

    public void setBirdPairTotalPrice(int birdPairTotalPrice) {
        this.birdPairTotalPrice = birdPairTotalPrice;
    }

    public boolean addBirdPairCustomerToCart(Bird birdShop, BirdCustomer birdCustomer) {
        boolean check = false;
        if (!this.birdPairList.containsKey(birdShop.getBird_id() + birdCustomer.getBird_id())) {
            this.birdPairList.put(birdShop.getBird_id() + birdCustomer.getBird_id(), new OrderedBirdPairItem(birdShop, birdCustomer, 2000000));
            this.totalItem += 1;
            cartTotalPrice += 2000000;
            check = true;
        }
        return check;

    }

    public boolean addBirdPairShopToCart(Bird birdMale, Bird birdFemale) {
        boolean check = false;
        if (!this.birdPairList.containsKey(birdMale.getBird_id() + birdFemale.getBird_id())) {
            this.birdPairList.put(birdMale.getBird_id() + birdFemale.getBird_id(), new OrderedBirdPairItem(birdMale, birdFemale, 2000000));
            this.totalItem += 1;
            cartTotalPrice += 2000000;
            check = true;
        }
        return check;

    }

    public boolean addBirdToCart(Bird bird, Accessory cage) {
        boolean check = false;
        if (!this.birdList.containsKey(bird.getBird_id())) {
            this.birdList.put(bird.getBird_id(), new OrderedBirdItem(bird, cage));
            if(cage != null) {
                this.totalItem += 2;
            } else {
                this.totalItem += 1;
            }
            cartTotalPrice += (bird.getPrice() - bird.getPrice() * bird.getDiscount() / 100);
            check = true;
        }
        return check;
    }

    public boolean addBirdNestToCart(BirdNest birdnest) {
        boolean check = false;
        if (!this.birdNestList.containsKey(birdnest.getNest_id())) {
            this.birdNestList.put(birdnest.getNest_id(), birdnest);
            this.totalItem += 1;
            cartTotalPrice += (birdnest.getPrice() - (birdnest.getPrice() * birdnest.getDiscount() / 100));
            check = true;
        }
        return check;
    }

    public boolean addAccessoryToCart(Accessory accessory, int quantity) throws SQLException {
        boolean check = false;
        AccessoryDAO ad = new AccessoryDAO();
        int numberAccessory = ad.getAccessoryByID(accessory.getAccessory_id()).getStock_quantity();
        if (numberAccessory != 0 && numberAccessory >= quantity) {
            if (this.accessoryList.containsKey(accessory.getAccessory_id())) {
                int currentQuant = this.accessoryList.get(accessory.getAccessory_id()).getOrder_quantity();
                if (currentQuant + 1 <= numberAccessory) {
                    this.accessoryList.get(accessory.getAccessory_id()).setOrder_quantity(currentQuant + quantity);
                    this.totalItem += quantity;
                    this.cartTotalPrice += ((accessory.getUnit_price() - (accessory.getUnit_price() * accessory.getDiscount() / 100)) * quantity);
                    check = true;
                }
            } else {
                this.accessoryList.put(accessory.getAccessory_id(), new OrderedAccessoryItem(accessory, quantity));
                this.totalItem += quantity;
                this.cartTotalPrice += ((accessory.getUnit_price() - (accessory.getUnit_price() * accessory.getDiscount() / 100)) * quantity);
                check = true;
            }
        }
        return check;
    }

    public boolean addAccessoryToCart(Accessory accessory, int quantity, int free) throws SQLException {
        boolean check = false;
        AccessoryDAO ad = new AccessoryDAO();
        int numberAccessory = ad.getAccessoryByID(accessory.getAccessory_id()).getStock_quantity();
        if (numberAccessory != 0 && numberAccessory >= quantity) {
            if (this.accessoryList.containsKey(accessory.getAccessory_id())) {
                OrderedAccessoryItem item = this.accessoryList.get(accessory.getAccessory_id());
                item.setFree_order(item.getFree_order() + 1);
                check = true;
            } else {
                this.accessoryList.put(accessory.getAccessory_id(), new OrderedAccessoryItem(accessory, 0, free));
                check = true;
            }
        }
        return check;
    }

    public boolean removeAccessoryItem(Accessory accessory) throws SQLException {
        boolean check = false;
        if (accessory != null) {
            if (this.accessoryList.containsKey(accessory.getAccessory_id())) {
                OrderedAccessoryItem item = this.accessoryList.get(accessory.getAccessory_id());
                if (item.getOrder_quantity() - 1 >= 0) {
                    item.setOrder_quantity(item.getOrder_quantity() - 1);
                    this.totalItem -= 1;
                    this.cartTotalPrice -= ((accessory.getUnit_price() - (accessory.getUnit_price() * accessory.getDiscount() / 100)));
                    check = true;
                }
            }
        }
        return check;
    }

    public boolean addCageToAccessory(Accessory accessory, int quantity) throws SQLException {
        boolean check = false;
        if (this.accessoryList.containsKey(accessory.getAccessory_id() + accessory.getDiscount())) {
            int currentQuant = this.accessoryList.get(accessory.getAccessory_id() + accessory.getDiscount()).getOrder_quantity();
            this.accessoryList.get(accessory.getAccessory_id() + accessory.getDiscount()).setOrder_quantity(currentQuant + quantity);
            check = true;
        } else {
            this.accessoryList.put(accessory.getAccessory_id() + accessory.getDiscount(), new OrderedAccessoryItem(accessory, quantity));
            check = true;
        }
        return check;
    }

    public boolean updateQuatityAccessory(Accessory accessory, int quantity) throws SQLException {
        boolean check = false;
        if (this.accessoryList.containsKey(accessory.getAccessory_id())) {
            int currentQuant = this.accessoryList.get(accessory.getAccessory_id()).getOrder_quantity();
            this.accessoryList.get(accessory.getAccessory_id()).setOrder_quantity(currentQuant - quantity);
            this.totalItem -= quantity;
            this.cartTotalPrice -= ((accessory.getUnit_price() - (accessory.getUnit_price() * accessory.getDiscount() / 100)) * quantity);
            check = true;
        }
        return check;
    }

    public void removeBirdFromCart(Bird bird) {
        OrderedBirdItem item = this.birdList.remove(bird.getBird_id());
        this.totalItem -= 1;
        this.cartTotalPrice -= (bird.getPrice() - bird.getPrice() * bird.getDiscount() / 100);
    }

    public void removeAccessoryFromCart(Accessory accessory, int order_quantity) {
        this.accessoryList.remove(accessory.getAccessory_id());
        this.totalItem -= order_quantity;
        this.cartTotalPrice -= ((accessory.getUnit_price() - (accessory.getUnit_price() * accessory.getDiscount() / 100)) * order_quantity);
    }

    public void removeCage(Accessory accessory) {
        OrderedAccessoryItem item = this.accessoryList.get(accessory.getAccessory_id());
        this.totalItem -= 1;
        item.setFree_order(item.getFree_order() - 1);
    }

    public void removeBirdPairFromCart(String key) {
        this.birdPairList.remove(key);
        this.totalItem -= 1;
        this.cartTotalPrice -= 2000000;
    }
}
