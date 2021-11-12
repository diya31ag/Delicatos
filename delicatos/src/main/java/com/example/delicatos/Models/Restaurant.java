package com.example.delicatos.Models;

import java.util.Locale;

public class Restaurant {
    private int id;
    private String email;
    private String name;
    private String address;
    private String contact;
    private String city;
    private String description;
    private String image;
    public Restaurant(){
    }
    public Restaurant(int id, String email, String name, String address, String contact, String city, String description, String image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.city = city;
        this.description=description;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city.toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImageURL(){
        System.out.println("img/restaurant/"+id+"/"+image);
        return "/img/restaurant/"+id+"/"+image;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
