/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

import java.util.ArrayList;

public class BirdNest {

    private String nest_id;
    private String nest_name;
    private String breed_id;
    private String dad_bird_id;
    private String mom_bird_id;
    private int baby_quantity;
    private String status;
    private int price;
    private int discount;
    private String image_url;
    private String description;
    private ArrayList<String> image_url_arr;

    public BirdNest() {
    }

    public BirdNest(String nest_id, String nest_name, String breed_id, String dad_bird_id, String mom_bird_id, int baby_quantity, String status, int price, int discount, String image_url, String description) {
        this.nest_id = nest_id;
        this.nest_name = nest_name;
        this.breed_id = breed_id;
        this.dad_bird_id = dad_bird_id;
        this.mom_bird_id = mom_bird_id;
        this.baby_quantity = baby_quantity;
        this.status = status;
        this.price = price;
        this.discount = discount;
        this.image_url = image_url;
        this.description = description;
    }

    public ArrayList<String> getImage_url_arr() {
        return image_url_arr;
    }

    public void setImage_url_arr(ArrayList<String> image_url_arr) {
        this.image_url_arr = image_url_arr;
    }
    
    public String getNest_id() {
        return nest_id;
    }

    public void setNest_id(String nest_id) {
        this.nest_id = nest_id;
    }

    public String getNest_name() {
        return nest_name;
    }

    public void setNest_name(String nest_name) {
        this.nest_name = nest_name;
    }

    public String getBreed_id() {
        return breed_id;
    }

    public void setBreed_id(String breed_id) {
        this.breed_id = breed_id;
    }

    public String getDad_bird_id() {
        return dad_bird_id;
    }

    public void setDad_bird_id(String dad_bird_id) {
        this.dad_bird_id = dad_bird_id;
    }

    public String getMom_bird_id() {
        return mom_bird_id;
    }

    public void setMom_bird_id(String mom_bird_id) {
        this.mom_bird_id = mom_bird_id;
    }

    public int getBaby_quantity() {
        return baby_quantity;
    }

    public void setBaby_quantity(int baby_quantity) {
        this.baby_quantity = baby_quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    

}
