/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ReportDTO {

    private ArrayList<Object> revenue;
    private int totalProduct;
    private ArrayList<Integer> product_sale;
    private int totalBird;
    private ArrayList<Integer> bird;
    private int totalAccessory;
    private ArrayList<Integer> accessory;
    private int totalBirdPair;
    private int stockBirdPair;
    private ArrayList<Integer> birdPair;
    private ArrayList<Long> totalMoneyBird;
    private ArrayList<Long> totalMoneyAccessory;
    private ArrayList<Long> totalMoneyBirdPair;
    private ArrayList<Object> totalMoney;
    public ArrayList<Object> getRevenue() {
        return revenue;
    }

    public void setRevenue(ArrayList<Object> revenue) {
        this.revenue = revenue;
    }

    public ArrayList<Integer> getProduct_sale() {
        return product_sale;
    }

    public void setProduct_sale(ArrayList<Integer> product_sale) {
        this.totalProduct = product_sale.get(0);
        product_sale.remove(0);
        this.product_sale = product_sale;
    }

    public ArrayList<Integer> getBird() {
        return bird;
    }

    public void setBird(ArrayList<Integer> bird) {
        this.totalBird = bird.get(0);
        bird.remove(0);
        this.bird = bird;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public int getTotalBird() {
        return totalBird;
    }

    public void setTotalBird(int totalBird) {
        this.totalBird = totalBird;
    }

    public int getTotalAccessory() {
        return totalAccessory;
    }

    public void setTotalAccessory(int totalAccessory) {
        this.totalAccessory = totalAccessory;
    }

    public ArrayList<Integer> getAccessory() {
        return accessory;
    }

    public void setAccessory(ArrayList<Integer> accessory) {
        this.totalAccessory = accessory.get(0);
        accessory.remove(0);
        this.accessory = accessory;
    }

    public int getTotalBirdPair() {
        return totalBirdPair;
    }

    public void setTotalBirdPair(int totalBirdPair) {
        this.totalBirdPair = totalBirdPair;
    }

    public ArrayList<Integer> getBirdPair() {
        return birdPair;
    }

    public void setBirdPair(ArrayList<Integer> birdPair) {
        this.totalBirdPair = birdPair.get(0)-birdPair.get(1);
        birdPair.remove(0);
        this.stockBirdPair = this.totalBirdPair;
        birdPair.remove(0);
        this.birdPair = birdPair;
    }

    public int getStockBirdPair() {
        return stockBirdPair;
    }

    public void setStockBirdPair(int stockBirdPair) {
        this.stockBirdPair = stockBirdPair;
    }

    public ArrayList<Object> getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(ArrayList<Object> totalMoney) {
        this.totalMoney = totalMoney;
    }

    public ArrayList<Long> getTotalMoneyBird() {
        return totalMoneyBird;
    }

    public void setTotalMoneyBird(ArrayList<Long> totalMoneyBird) {
        this.totalMoneyBird = totalMoneyBird;
    }

    public ArrayList<Long> getTotalMoneyAccessory() {
        return totalMoneyAccessory;
    }

    public void setTotalMoneyAccessory(ArrayList<Long> totalMoneyAccessory) {
        this.totalMoneyAccessory = totalMoneyAccessory;
    }

    public ArrayList<Long> getTotalMoneyBirdPair() {
        return totalMoneyBirdPair;
    }

    public void setTotalMoneyBirdPair(ArrayList<Long> totalMoneyBirdPair) {
        this.totalMoneyBirdPair = totalMoneyBirdPair;
    }
    
}
