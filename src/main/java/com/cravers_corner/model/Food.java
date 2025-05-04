package com.cravers_corner.model;

import java.sql.Timestamp;



public class Food {
    private int food_id;
    private String name;
    private String description;
    private double price;
    private int category_id;
    private String image_url;
    private String status;
    private String serving_size;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String category_name;
    // Empty constructor
    public Food() {
    }

    // Full constructor
    public Food(int food_id, String name, String description, double price, int category_id,
                String image_url, String status, String serving_size,
                Timestamp created_at, Timestamp updated_at) {
        this.food_id = food_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.image_url = image_url;
        this.status = status;
        this.serving_size = serving_size;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters and Setters
    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServing_size() {
        return serving_size;
    }

    public void setServing_size(String serving_size) {
        this.serving_size = serving_size;
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
    
    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String categoryName) {
        this.category_name = categoryName;
    }
}