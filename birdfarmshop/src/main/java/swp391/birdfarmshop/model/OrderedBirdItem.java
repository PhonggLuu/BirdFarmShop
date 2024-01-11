/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.model;

/**
 *
 * @author tlminh
 */
public class OrderedBirdItem {
    private Bird bird;
    private Accessory cage;

    public OrderedBirdItem() {
    }

    public OrderedBirdItem(Bird bird, Accessory cage) {
        this.bird = bird;
        this.cage = cage;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public Accessory getCage() {
        return cage;
    }

    public void setCage(Accessory cage) {
        this.cage = cage;
    }
}
