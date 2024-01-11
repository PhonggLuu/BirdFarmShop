/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import swp391.birdfarmshop.model.Bird;
import swp391.birdfarmshop.model.BirdCustomer;

/**
 *
 * @author tlminh
 */
public class BirdPairDTO {
    private int pair_id;
    private String order_id;
    private int young_bird_price;
    private BirdCustomer birdCustomer;
    private Bird male_bird;
    private Bird female_bird;
    private int number_egg;
    private int number_young_bird;
    private String status;
    private String username;
    private Timestamp create_date;
    ArrayList<String> pList;

    public BirdPairDTO(int pair_id, String order_id, int young_bird_price, BirdCustomer birdCustomer, Bird male_bird, Bird female_bird, int number_egg, int number_young_bird, String status, ArrayList<String> pList) {
        this.pair_id = pair_id;
        this.order_id = order_id;
        this.young_bird_price = young_bird_price;
        this.birdCustomer = birdCustomer;
        this.male_bird = male_bird;
        this.female_bird = female_bird;
        this.number_egg = number_egg;
        this.number_young_bird = number_young_bird;
        this.status = status;
        this.pList = pList;
    }
    
    public BirdPairDTO(int pair_id, String order_id, int young_bird_price, BirdCustomer birdCustomer, Bird male_bird, Bird female_bird, int number_egg, int number_young_bird, String status, ArrayList<String> pList, String username) {
        this.pair_id = pair_id;
        this.order_id = order_id;
        this.young_bird_price = young_bird_price;
        this.birdCustomer = birdCustomer;
        this.male_bird = male_bird;
        this.female_bird = female_bird;
        this.number_egg = number_egg;
        this.number_young_bird = number_young_bird;
        this.status = status;
        this.pList = pList;
        this.username = username;
    }

    public BirdPairDTO(int pair_id, String order_id, int young_bird_price, BirdCustomer birdCustomer, Bird male_bird, Bird female_bird, int number_egg, int number_young_bird, String status, String username, Timestamp create_date, ArrayList<String> pList) {
        this.pair_id = pair_id;
        this.order_id = order_id;
        this.young_bird_price = young_bird_price;
        this.birdCustomer = birdCustomer;
        this.male_bird = male_bird;
        this.female_bird = female_bird;
        this.number_egg = number_egg;
        this.number_young_bird = number_young_bird;
        this.status = status;
        this.username = username;
        this.create_date = create_date;
        this.pList = pList;
    }


    
    public int getPair_id() {
        return pair_id;
    }

    public void setPair_id(int pair_id) {
        this.pair_id = pair_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getYoung_bird_price() {
        return young_bird_price;
    }

    public void setYoung_bird_price(int young_bird_price) {
        this.young_bird_price = young_bird_price;
    }

    public BirdCustomer getBirdCustomer() {
        return birdCustomer;
    }

    public void setBirdCustomer(BirdCustomer birdCustomer) {
        this.birdCustomer = birdCustomer;
    }

    public Bird getMale_bird() {
        return male_bird;
    }

    public void setMale_bird(Bird male_bird) {
        this.male_bird = male_bird;
    }

    public Bird getFemale_bird() {
        return female_bird;
    }

    public void setFemale_bird(Bird female_bird) {
        this.female_bird = female_bird;
    }

    public int getNumber_egg() {
        return number_egg;
    }

    public void setNumber_egg(int number_egg) {
        this.number_egg = number_egg;
    }

    public int getNumber_young_bird() {
        return number_young_bird;
    }

    public void setNumber_young_bird(int number_young_bird) {
        this.number_young_bird = number_young_bird;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getpList() {
        return pList;
    }

    public void setpList(ArrayList<String> pList) {
        this.pList = pList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }
}
