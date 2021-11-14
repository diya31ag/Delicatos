package com.example.delicatos.Models;
import com.example.delicatos.Models.MenuItem;
public class CartItem {
    int id;
    String customer;
    String restaurant;
    MenuItem item;
    int quantity;

    public CartItem(int id, String customer, String restaurant, MenuItem item, int quantity) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.item=item;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
