package com.example.delicatos.Models;
import java.util.*;
public class Order {
    private int id;
    private String customer;
    private String restaurant;
    private List<OrderItem> itemList;
    private String dateTime;
    private String status;
    private double total;
    private String restaurantName;
    private Customer customerInfo;
    public Order(){
    }

    public Order(int id, String customer, String restaurant, List<OrderItem> itemList, String dateTime, String status, double total, String restaurantName, Customer customerInfo) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.itemList = itemList;
        this.dateTime = dateTime;
        this.status = status;
        this.total=total;
        this.restaurantName=restaurantName;
//        this.customerName=customerName;
        this.customerInfo=customerInfo;
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

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Customer getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(Customer customerInfo) {
        this.customerInfo = customerInfo;
    }
}