package com.cravers_corner.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.cravers_corner.controller.database.DatabaseConnection;
import com.cravers_corner.controller.util.PasswordUtil;
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
	    String query = "INSERT INTO users (first_name, last_name, email, role, phone, password, created_at, updated_at, " +
	               "username, profile_image_url, current_address, shipping_address) " +
	               "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

	        ps.setString(1, user.getFirst_name());
	        ps.setString(2, user.getLast_name());
	        ps.setString(3, user.getEmail());
	        ps.setString(4, user.getRole());
	        ps.setString(5, user.getPhone());
	        ps.setString(6, user.getPassword());
	        ps.setTimestamp(7, currentTime); // created_at
	        ps.setTimestamp(8, currentTime); // updated_at
	        ps.setString(9, user.getUsername());
	        ps.setString(10, user.getProfile_image_url());
	        ps.setString(11, user.getCurrent_address());
	        ps.setString(12, user.getShipping_address());

	        if (ps.executeUpdate() > 0) {
	            isUserRegistered = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return isUserRegistered;
	}

	
	
	
	public User login(String identifier, String password) throws ClassNotFoundException, SQLException {
		 String sql = "SELECT * FROM users WHERE (email = ? OR username = ?)";

		    try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setString(1, identifier); // email or username
		        ps.setString(2, identifier);
		        
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            
		        	//getting the encrypted pw from db
		            String encrypted_password_from_db = rs.getString("password");

		            //encrypting the user entered password
		            String encrypted_entered_password = PasswordUtil.encrypt(password);

		            //if the both are equal then only:
		            if (encrypted_password_from_db.equals(encrypted_entered_password )) {
		                // Passwords match, login successful
		                User user = new User();
		                user.setUser_id(rs.getInt("user_id"));
		                user.setFirst_name(rs.getString("first_name"));
		                user.setLast_name(rs.getString("last_name"));
		                user.setUsername(rs.getString("username"));
		                user.setEmail(rs.getString("email"));
		                user.setPhone(rs.getString("phone"));
		                user.setRole(rs.getString("role"));
		                return user;
		            } else {
		                // Password does not match
		                return null; // No user found or wrong password
		            }
		        } else {
		            return null; // No user found
		        }
		    }
	}
	
	public boolean isUsernameTaken(String username) throws SQLException, ClassNotFoundException {
	    String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, username);
	        
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            // If the count is greater than 0, the username is taken
	            return rs.getInt(1) > 0;
	        }
	    }
	    return false;
	}
}


