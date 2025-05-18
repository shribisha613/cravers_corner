package com.cravers_corner.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT category_id, name, description FROM categories";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setCategory_id(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                categories.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
	
	// Added getCategoryById to edit a specific category
	public Category getCategoryById(int id) {
	    Category category = null;
	    String sql = "SELECT * FROM categories WHERE category_id = ?";

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            category = new Category();
	            category.setCategory_id(rs.getInt("category_id"));
	            category.setName(rs.getString("name"));
	            category.setDescription(rs.getString("description"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return category;
	}

	
	// have added method updated Category
	public boolean updateCategory(Category category) {
	    boolean isUpdated = false;
	    String sql = "UPDATE categories SET name = ?, description = ?, updated_at = ? WHERE category_id = ?";

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

	        ps.setString(1, category.getName());
	        ps.setString(2, category.getDescription());
	        ps.setTimestamp(3, currentTime);
	        ps.setInt(4, category.getCategory_id());

	        if (ps.executeUpdate() > 0) {
	            isUpdated = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return isUpdated;
	}

}
