/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tlminh
 */
public class BirdDTO {

    private String bird_id;
    private String bird_name;
    private String color;
    private Date birthday;
    private int age;
    private int grown_age;
    private String gender;
    private String breed_id;
    private String breed_name;
    private String achievement;
    private int reproduction_history;
    private int price;
    private String description;
    private String dad_id;
    private String dad_name;
    private String mom_id;
    private String mom_name;
    private int discount;
    private String status;
    private ArrayList<String> image_urls;

    public BirdDTO() {
    }

    public BirdDTO(String bird_id, String bird_name, String color, Date birthday, int age, int grown_age, String gender, String breed_id, String breed_name, String achievement, int reproduction_history, int price, String description, String dad_id, String dad_name, String mom_id, String mom_name, int discount, String status, ArrayList<String> image_urls) {
        this.bird_id = bird_id;
        this.bird_name = bird_name;
        this.color = color;
        this.birthday = birthday;
        this.age = age;
        this.grown_age = grown_age;
        this.gender = gender;
        this.breed_id = breed_id;
        this.breed_name = breed_name;
        this.achievement = achievement;
        this.reproduction_history = reproduction_history;
        this.price = price;
        this.description = description;
        this.dad_id = dad_id;
        this.dad_name = dad_name;
        this.mom_id = mom_id;
        this.mom_name = mom_name;
        this.discount = discount;
        this.status = status;
        this.image_urls = image_urls;
    }

    public ArrayList<String> getImage_urls() {
        return image_urls;
    }

    public void setImage_url(ArrayList<String> image_urls) {
        this.image_urls = image_urls;
    }

    public String getBird_id() {
        return bird_id;
    }

    public void setBird_id(String bird_id) {
        this.bird_id = bird_id;
    }

    public String getBird_name() {
        return bird_name;
    }

    public void setBird_name(String bird_name) {
        this.bird_name = bird_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrown_age() {
        return grown_age;
    }

    public void setGrown_age(int grown_age) {
        this.grown_age = grown_age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed_id() {
        return breed_id;
    }

    public void setBreed_id(String breed_id) {
        this.breed_id = breed_id;
    }

    public String getBreed_name() {
        return breed_name;
    }

    public void setBreed_name(String breed_name) {
        this.breed_name = breed_name;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public int getReproduction_history() {
        return reproduction_history;
    }

    public void setReproduction_history(int reproduction_history) {
        this.reproduction_history = reproduction_history;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDad_id() {
        return dad_id;
    }

    public void setDad_id(String dad_id) {
        this.dad_id = dad_id;
    }

    public String getDad_name() {
        return dad_name;
    }

    public void setDad_name(String dad_name) {
        this.dad_name = dad_name;
    }

    public String getMom_id() {
        return mom_id;
    }

    public void setMom_id(String mom_id) {
        this.mom_id = mom_id;
    }

    public String getMom_name() {
        return mom_name;
    }

    public void setMom_name(String mom_name) {
        this.mom_name = mom_name;
    }

}
