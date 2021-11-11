package com.example.delicatos.Models;

import java.awt.*;
import java.util.List;
public class MenuItem {
    private int id;
    private String restaurant;
    private String itemName;
    private double price;
    List<String> categories;
    private String photo;
    public MenuItem(){
    }
    public MenuItem(int id, String restaurant, String itemName, double price, List<String> categories, String photo) {
        this.id = id;
        this.restaurant = restaurant;
        this.itemName = itemName;
        this.price = price;
        this.categories = categories;
        this.photo= photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String    getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getImage() {
//        System.out.println(id);
//        System.out.println("/img/food-item-photos/"+String.valueOf(id)+"/"+photo);
        return photo;
    }
    public String getImageURL(){
        System.out.println("/img/food-item-photos/"+String.valueOf(id)+"/"+photo);
        return "/img/food-item-photos/"+String.valueOf(id)+"/"+photo;
    }
    public void setImage(String photo) {
        this.photo = photo;
    }
}



