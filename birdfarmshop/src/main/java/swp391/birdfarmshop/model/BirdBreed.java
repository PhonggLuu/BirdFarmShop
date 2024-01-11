/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author tlminh
 */
public class BirdBreed {

    String breed_id;
    String breed_name;
    String breed_thumbnail;

    public BirdBreed(String breed_id, String breed_name, String breed_thumbnail) {
        this.breed_id = breed_id;
        this.breed_name = breed_name;
        this.breed_thumbnail = breed_thumbnail;
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

    public String getBreed_thumbnail() {
        return breed_thumbnail;
    }

    public void setBreed_thumbnail(String breed_thumbnail) {
        this.breed_thumbnail = breed_thumbnail;
    }

}
