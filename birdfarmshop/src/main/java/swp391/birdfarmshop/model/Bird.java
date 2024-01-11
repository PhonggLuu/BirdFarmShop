/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author tlminh
 */
public class Bird {

    private String bird_id;
    private String bird_name;
    private String color;
    private int age;
    private int grown_age;
    private boolean gender;
    private String breed_id;
    private String achievement;
    private int reproduction_history;
    private int price;
    private String description;
    private int discount;
    private String status;
    private String image_url;
    private String mom_id;
    private String dad_id;

    public Bird(String bird_id, String bird_name, String color, int age, int grown_age, boolean gender, String breed_id, String achievement, int reproduction_history, int price, String description, int discount, String status, String image_url) {
        this.bird_id = bird_id;
        this.bird_name = bird_name;
        this.color = color;
        this.age = age;
        this.grown_age = grown_age;
        this.gender = gender;
        this.breed_id = breed_id;
        this.achievement = achievement;
        this.reproduction_history = reproduction_history;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.status = status;
        this.image_url = image_url;
    }

    public Bird(String bird_id, String bird_name, String color, int age, int grown_age, boolean gender, String breed_id, String achievement, int reproduction_history, int price, String description, int discount, String status, String image_url, String mom_id, String dad_id) {
        this.bird_id = bird_id;
        this.bird_name = bird_name;
        this.color = color;
        this.age = age;
        this.grown_age = grown_age;
        this.gender = gender;
        this.breed_id = breed_id;
        this.achievement = achievement;
        this.reproduction_history = reproduction_history;
        this.price = price;
        this.description = description;
        this.discount = discount;
        this.status = status;
        this.image_url = image_url;
        this.mom_id = mom_id;
        this.dad_id = dad_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public void setAge(short age) {
        this.age = age;
    }

    public int getGrown_age() {
        return grown_age;
    }

    public void setGrown_age(short grown_age) {
        this.grown_age = grown_age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBreed_id() {
        return breed_id;
    }

    public void setBreed_id(String breed_id) {
        this.breed_id = breed_id;
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
    
    public String getMom_id() {
        return mom_id;
    }

    public void setMom_id(String mom_id) {
        this.mom_id = mom_id;
    }

    public String getDad_id() {
        return dad_id;
    }

    public void setDad_id(String dad_id) {
        this.dad_id = dad_id;
    }
    
    
}
