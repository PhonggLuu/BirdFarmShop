/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author Admin
 */
public class OrderedBirdPairItem {
    private Bird birdMale;
    private Bird birdFemale;
    private BirdCustomer birdCustomer;
    private Bird birdShop;  
    private int servicePrice;

    public OrderedBirdPairItem(Bird birdShop,BirdCustomer birdCustomer,  int servicePrice) {
        this.birdCustomer = birdCustomer;
        this.birdShop = birdShop;
        this.servicePrice = servicePrice;
    }

    public OrderedBirdPairItem(Bird birdMale, Bird birdFemale, int servicePrice) {
        this.birdMale = birdMale;
        this.birdFemale = birdFemale;
        this.servicePrice = servicePrice;
    }

    public Bird getBirdMale() {
        return birdMale;
    }

    public void setBirdMale(Bird birdMale) {
        this.birdMale = birdMale;
    }

    public Bird getFemalBird() {
        return birdFemale;
    }

    public void setFemalBird(Bird femalBird) {
        this.birdFemale = femalBird;
    }

    public BirdCustomer getBirdCustomer() {
        return birdCustomer;
    }

    public void setBirdCustomer(BirdCustomer birdCustomer) {
        this.birdCustomer = birdCustomer;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Bird getBirdFemale() {
        return birdFemale;
    }

    public Bird getBirdShop() {
        return birdShop;
    }
    
}
