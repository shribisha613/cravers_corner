package com.cravers_corner.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.cravers_corner.controller.database.DatabaseConnection;
import com.cravers_corner.model.User;



public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	// Constructor: Initializes the database connection when an object is created
	public UserDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getConnection();
	}
	
	public boolean register(User user) {
	    boolean isUserRegistered = false;

	    // Follow exact database column order (excluding auto-increment user_id)
	    String query = "INSERT INTO users (full_name, email, role, phone, password, created_at, updated_at, " +
	                   "username, profile_image_url, current_address, shipping_address) " +
	                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

	        ps.setString(1, user.getFull_name());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getRole());
	        ps.setString(4, user.getPhone());
	        ps.setString(5, user.getPassword());
	        ps.setTimestamp(6, currentTime); // created_at
	        ps.setTimestamp(7, currentTime); // updated_at
	        ps.setString(8, user.getUsername());
	        ps.setString(9, user.getProfile_image_url());
	        ps.setString(10, user.getCurrent_address());
	        ps.setString(11, user.getShipping_address());

	        if (ps.executeUpdate() > 0) {
	            isUserRegistered = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return isUserRegistered;
	}

	
	
	
	public User login(String identifier, String password) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM users WHERE (email = ? OR username = ?) AND password = ?";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, identifier);//email
            ps.setString(2, identifier);
            ps.setString(3, password); 

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setFull_name(rs.getString("full_name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                return user;
            } else {
                return null; // No user found
            }
        }
    }
}


