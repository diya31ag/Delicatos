package com.example.delicatos.Models;

public class OrderItem {
    private int id;
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(int id, MenuItem menuItem, int quantity) {
        this.id = id;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
