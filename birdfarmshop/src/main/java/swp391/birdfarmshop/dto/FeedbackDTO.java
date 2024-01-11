/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

import java.sql.Date;

/**
 *
 * @author tlminh
 */
public class FeedbackDTO {

    private String email;
    private String fullName;
    private String bird_id;
    private String bird_name;
    private String accessory_id;
    private String accessory_name;
    private String image_url;
    private String comment;
    private int rating;
    private String idProduct;
    private Date feedbackDate;

    public FeedbackDTO(String email, String fullName, String bird_id, String bird_name, String accessory_id, String accessory_name, String image_url, String comment, int rating, String idProduct, Date feedbackDate) {
        this.email = email;
        this.fullName = fullName;
        this.bird_id = bird_id;
        this.bird_name = bird_name;
        this.accessory_id = accessory_id;
        this.accessory_name = accessory_name;
        this.image_url = image_url;
        this.comment = comment;
        this.rating = rating;
        this.idProduct = idProduct;
        this.feedbackDate = feedbackDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getAccessory_id() {
        return accessory_id;
    }

    public void setAccessory_id(String accessory_id) {
        this.accessory_id = accessory_id;
    }

    public String getAccessory_name() {
        return accessory_name;
    }

    public void setAccessory_name(String accessory_name) {
        this.accessory_name = accessory_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

}
