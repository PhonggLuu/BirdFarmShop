/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class BirdNestDTO {

    private String nest_id;
    private String nest_name;
    private String dad_bird_id;
    private String dad_bird_name;
    private String dad_bird_thumbnail;
    private String mom_bird_id;
    private String mom_bird_name;
    private String mom_bird_thumbnail;
    private int baby_quantity;
    private String status;
    private int price;
    private String description;
    private ArrayList<String> image_url;
    private String breed_id;
    private int discount;

    public BirdNestDTO() {
    }

    public BirdNestDTO(String nest_id, String nest_name, String dad_bird_id, String dad_bird_name, String dad_bird_thumbnail, String mom_bird_id, String mom_bird_name, String mom_bird_thumbnail, int baby_quantity, String status, int price, String description, ArrayList<String> image_url) {
        this.nest_id = nest_id;
        this.nest_name = nest_name;
        this.dad_bird_id = dad_bird_id;
        this.dad_bird_name = dad_bird_name;
        this.dad_bird_thumbnail = dad_bird_thumbnail;
        this.mom_bird_id = mom_bird_id;
        this.mom_bird_name = mom_bird_name;
        this.mom_bird_thumbnail = mom_bird_thumbnail;
        this.baby_quantity = baby_quantity;
        this.status = status;
        this.price = price;
        this.description = description;
        this.image_url = image_url;
    }

    public BirdNestDTO(String nest_id, String nest_name, String dad_bird_id, String mom_bird_id, int baby_quantity, String status, int price, String description, String breed_id, int discount) {
        this.nest_id = nest_id;
        this.nest_name = nest_name;
        this.dad_bird_id = dad_bird_id;
        this.mom_bird_id = mom_bird_id;
        this.baby_quantity = baby_quantity;
        this.status = status;
        this.price = price;
        this.description = description;
        this.breed_id = breed_id;
        this.discount = discount;
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

    public String getDad_bird_id() {
        return dad_bird_id;
    }

    public void setDad_bird_id(String dad_bird_id) {
        this.dad_bird_id = dad_bird_id;
    }

    public String getDad_bird_name() {
        return dad_bird_name;
    }

    public void setDad_bird_name(String dad_bird_name) {
        this.dad_bird_name = dad_bird_name;
    }

    public String getDad_bird_thumbnail() {
        return dad_bird_thumbnail;
    }

    public void setDad_bird_thumbnail(String dad_bird_thumbnail) {
        this.dad_bird_thumbnail = dad_bird_thumbnail;
    }

    public String getMom_bird_id() {
        return mom_bird_id;
    }

    public void setMom_bird_id(String mom_bird_id) {
        this.mom_bird_id = mom_bird_id;
    }

    public String getMom_bird_name() {
        return mom_bird_name;
    }

    public void setMom_bird_name(String mom_bird_name) {
        this.mom_bird_name = mom_bird_name;
    }

    public String getMom_bird_thumbnail() {
        return mom_bird_thumbnail;
    }

    public void setMom_bird_thumbnail(String mom_bird_thumbnail) {
        this.mom_bird_thumbnail = mom_bird_thumbnail;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImage_url() {
        return image_url;
    }

    public void setImage_url(ArrayList<String> image_url) {
        this.image_url = image_url;
    }

    public String getBreed_id() {
        return breed_id;
    }

    public void setBreed_id(String breed_id) {
        this.breed_id = breed_id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
