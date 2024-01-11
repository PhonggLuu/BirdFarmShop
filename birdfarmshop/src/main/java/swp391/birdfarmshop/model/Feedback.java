/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

import java.sql.Date;

/**
 *
 * @author tlminh
 */
public class Feedback {
    private String feedback_id;
    private String customer;
    private int order_item_id;
    private int rating;
    private String comment;
    private Date feedback_date;

    public Feedback(String feedback_id, String customer, int order_item_id, int rating, String comment, Date feedback_date) {
        this.feedback_id = feedback_id;
        this.customer = customer;
        this.order_item_id = order_item_id;
        this.rating = rating;
        this.comment = comment;
        this.feedback_date = feedback_date;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(int order_item_id) {
        this.order_item_id = order_item_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getFeedback_date() {
        return feedback_date;
    }

    public void setFeedback_date(Date feedback_date) {
        this.feedback_date = feedback_date;
    }
    
}
