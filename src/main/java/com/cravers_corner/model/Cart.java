package com.cravers_corner.model;

import java.sql.Timestamp;

public class Cart {
	
	private int cart_id;
    private int customer_id;
    private double total_amount;
    private Timestamp created_at;
    private Timestamp updated_at;

    // Constructors
    public Cart() {}

    public Cart(int cart_id, int customer_id, double total_amount, Timestamp created_at, Timestamp updated_at) {
        this.cart_id = cart_id;
        this.customer_id = customer_id;
        this.total_amount = total_amount;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters and Setters
    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
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

}
