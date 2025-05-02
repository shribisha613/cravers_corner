package com.cravers_corner.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.cravers_corner.controller.database.DatabaseConnection;
import com.cravers_corner.model.Category;

public class CategoryDAO {

	private Connection conn;
	private PreparedStatement ps;

	public CategoryDAO() throws ClassNotFoundException, SQLException {
		conn = DatabaseConnection.getConnection();// Establish a database connection when CategoryDAO is created
	}

	// Add a new category to the database
	public boolean addCategory(Category category) {
		boolean isCategoryAdded = false;

	    
	    String sql = "INSERT INTO categories (name, description, created_at, updated_at) " +
	                 "VALUES (?, ?, ?, ?)";

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

	        
	        ps.setString(1, category.getName());
	        ps.setString(2, category.getDescription());
	        ps.setTimestamp(3, currentTime); 
	        ps.setTimestamp(4, currentTime); 

	        
	        if (ps.executeUpdate() > 0) {
	            isCategoryAdded = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return isCategoryAdded;
}
}
