package com.cravers_corner.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
		            
		        	
		            String encrypted_password_from_db = rs.getString("password");

		           
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
		                user.setProfile_image_url(rs.getString("profile_image_url"));
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
	

	
	public User getUserByID(int user_id) throws SQLException, ClassNotFoundException {
		
		String sql = "SELECT * FROM users WHERE id = ?";
		
		try (Connection conn = DatabaseConnection.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
	    
	    stmt.setInt(1, user_id);
	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
	        User user = new User();
	        user.setUser_id(rs.getInt("id"));
	        user.setFirst_name(rs.getString("first_name"));
	        user.setLast_name(rs.getString("last_name"));
	        user.setEmail(rs.getString("email"));
	        user.setPhone(rs.getString("phone"));
	        user.setPassword(rs.getString("password"));
	        user.setCurrent_address(rs.getString("address"));
	        user.setUsername(rs.getString("username"));
	        return user;
	    }
		}
	    return null;
		
	}
	
	public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
	    String sql = "SELECT * FROM users WHERE username = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, username); // Set the username parameter
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            User user = new User();
	            user.setUser_id(rs.getInt("user_id"));
	            user.setFirst_name(rs.getString("first_name"));
	            user.setLast_name(rs.getString("last_name"));
	            user.setEmail(rs.getString("email"));
	            user.setPhone(rs.getString("phone"));
	            user.setPassword(rs.getString("password"));
	            user.setCurrent_address(rs.getString("current_address"));
	            user.setUsername(rs.getString("username"));
	            return user;
	        }
	    }
	    return null; // Return null if no user is found
	}
	
	 public List<User> getAllUsersExceptAdmin(int adminId, String sortOrder) throws SQLException {
	        List<User> userList = new ArrayList<>();
	        String query = "SELECT user_id, first_name, last_name, username, email, phone, role, created_at, profile_image_url FROM users WHERE user_id != ? ORDER BY " + sortOrder;
	        
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, adminId);
	            ResultSet rs = ps.executeQuery();
	            
	            while (rs.next()) {
	                User user = new User();
	                user.setUser_id(rs.getInt("user_id"));
	                user.setFirst_name(rs.getString("first_name"));
	                user.setLast_name(rs.getString("last_name"));
	                user.setUsername(rs.getString("username"));
	                user.setEmail(rs.getString("email"));
	                user.setPhone(rs.getString("phone"));
	                user.setRole(rs.getString("role"));
	                user.setCreated_at(rs.getTimestamp("created_at"));
	                user.setProfile_image_url(rs.getString("profile_image_url"));
	                userList.add(user);
	            }
	        }
	        return userList;
	    }
	 
	 
	 public List<User> getAllUsers(String sortOrder) throws SQLException {
		    List<User> userList = new ArrayList<>();
		    String query = "SELECT u.user_id, u.first_name, u.last_name, u.username, u.email, u.phone,u.role, u.created_at, u.profile_image_url, COUNT(o.order_id) as total_orders FROM users u LEFT JOIN orders o ON u.user_id = o.customer_id WHERE u.role != \"admin\" GROUP BY u.user_id ORDER BY " + sortOrder;
		    
		    try (PreparedStatement ps = conn.prepareStatement(query)) {
		        // No need to set admin ID parameter anymore
		        ResultSet rs = ps.executeQuery();
		        
		        while (rs.next()) {
		            User user = new User();
		            user.setUser_id(rs.getInt("user_id"));
		            user.setFirst_name(rs.getString("first_name"));
		            user.setLast_name(rs.getString("last_name"));
		            user.setUsername(rs.getString("username"));
		            user.setEmail(rs.getString("email"));
		            user.setPhone(rs.getString("phone"));
		            user.setRole(rs.getString("role"));
		            user.setCreated_at(rs.getTimestamp("created_at"));
		            user.setProfile_image_url(rs.getString("profile_image_url"));
		            user.setTotal_orders(rs.getInt("total_orders"));
		            userList.add(user);
		        }
		    }
		    return userList;
		}
	
	
	public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
	    String sql;
	    boolean hasNewProfileImage = user.getProfile_image_url() != null && !user.getProfile_image_url().isEmpty();

	    if (hasNewProfileImage) {
	        sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, username = ?, phone = ?, current_address = ?, password = ?, profile_image_url = ?, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";
	    } else {
	        sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, username = ?, phone = ?, current_address = ?, password = ?, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";
	    }

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, user.getFirst_name());
	        stmt.setString(2, user.getLast_name());
	        stmt.setString(3, user.getEmail());
	        stmt.setString(4, user.getUsername());
	        stmt.setString(5, user.getPhone());
	        stmt.setString(6, user.getCurrent_address());
	        stmt.setString(7, user.getPassword());

	        if (hasNewProfileImage) {
	            stmt.setString(8, user.getProfile_image_url());
	            stmt.setInt(9, user.getUser_id());
	        } else {
	            stmt.setInt(8, user.getUser_id());
	        }

	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	
	public List<User> searchUsers(String keyword) throws SQLException {
	    List<User> userList = new ArrayList<>();
	    String query = "SELECT u.* FROM users u WHERE u.username LIKE ? OR u.first_name LIKE ? OR u.last_name LIKE ? ORDER BY u.user_id;";  // Changed to sort by user_id
	    
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        String searchPattern = "%" + keyword + "%";
	        ps.setString(1, searchPattern);
	        ps.setString(2, searchPattern);
	        ps.setString(3, searchPattern);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                User user = new User();
	                user.setUser_id(rs.getInt("user_id"));
	                user.setFirst_name(rs.getString("first_name"));
	                user.setLast_name(rs.getString("last_name"));
	                user.setUsername(rs.getString("username"));
	                user.setEmail(rs.getString("email"));
	                user.setPhone(rs.getString("phone"));
	                user.setRole(rs.getString("role"));
	                user.setProfile_image_url(rs.getString("profile_image_url"));
	                user.setCreated_at(rs.getTimestamp("created_at"));
	                user.setUpdated_at(rs.getTimestamp("updated_at"));
	                
	                userList.add(user);
	            }
	        }
	    }
	    return userList;
	}


}


