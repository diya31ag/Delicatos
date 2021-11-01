package com.example.delicatos.Models;

public class Restaurant {
    private int id;
    private String email;
    private String name;
    private Address address;
    private String contact;
    private String city;

    public Restaurant(int id, String email, String name, Address address, String contact, String city) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.city = city;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
        this.city = city;
    }
}
