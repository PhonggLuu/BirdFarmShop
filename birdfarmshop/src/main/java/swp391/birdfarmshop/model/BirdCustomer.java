/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author Admin
 */
public class BirdCustomer {
    
    private int bird_id;
    private String name;
    private boolean gender;
    private String breed_id;
    private String username;
    private String img_url;
    private String status;
    private String breed_name;
    public BirdCustomer(int bird_id, String name, boolean gender, String breed_id, String username, String img_url, String status) {
        this.bird_id = bird_id;
        this.name = name;
        this.gender = gender;
        this.breed_id = breed_id;
        this.username = username;
        this.img_url = img_url;
        this.status = status;
    }

    public BirdCustomer(int bird_id, String name, boolean gender, String breed_id, String username, String img_url, String status, String breed_name) {
        this.bird_id = bird_id;
        this.name = name;
        this.gender = gender;
        this.breed_id = breed_id;
        this.username = username;
        this.img_url = img_url;
        this.status = status;
        this.breed_name = breed_name;
    }

    
    public int getBird_id() {
        return bird_id;
    }

    public void setBird_id(int bird_id) {
        this.bird_id = bird_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBreed_name() {
        return breed_name;
    }

    public void setBreed_name(String breed_name) {
        this.breed_name = breed_name;
    }
    
    
}
