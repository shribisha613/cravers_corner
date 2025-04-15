package com.cravers_corner.model;

import java.sql.Timestamp;

public class User {
    private int user_id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String role;
    private String gender;
    private String phone;
    private String password;
    private String profile_image_url;
    private String current_address;
    private String shipping_address;
    private Timestamp created_at;
    private Timestamp updated_at;

    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(int user_id, String firstname, String lastname, String username, String email, String role,
                String gender, String phone, String password, String profile_image_url,
                String current_address, String shipping_address, Timestamp created_at, Timestamp updated_at) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.role = role;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
        this.profile_image_url = profile_image_url;
        this.current_address = current_address;
        this.shipping_address = shipping_address;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters and Setters
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }
    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getCurrent_address() {
        return current_address;
    }
    public void setCurrent_address(String current_address) {
        this.current_address = current_address;
    }

    public String getShipping_address() {
        return shipping_address;
    }
    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
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