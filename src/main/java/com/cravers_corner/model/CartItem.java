package com.cravers_corner.model;

import java.sql.Timestamp;

public class CartItem {
    private int cart_item_id;
    private int cart_id;
    private int food_id;
    private int quantity;
    private double price;
    private double subtotal;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String food_name;
    private String image_url;

    // Constructors
    public CartItem() {}

    public CartItem(int cart_item_id, int cart_id, int food_id, int quantity, double price, double subtotal, Timestamp created_at, Timestamp updated_at) {
        this.cart_item_id = cart_item_id;
        this.cart_id = cart_id;
        this.food_id = food_id;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
        this.created_at = created_at;
        this.updated_at = updated_at;
        
    }

    // Getters and Setters
    public int getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(int cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    
    public String getFood_name() {
        return food_name;
    }
    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
