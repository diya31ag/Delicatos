package com.example.delicatos.Models;

public class Address {
    private String street;
    private String zipcode;
    private String city;
    private Location location;

    public Address(String street, String zipcode, String city, Location location) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.location = location;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
