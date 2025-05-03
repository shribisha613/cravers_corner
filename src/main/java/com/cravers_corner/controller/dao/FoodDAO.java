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
import com.cravers_corner.model.Food;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	// Constructor: Initializes the database connection when an object is created
	public FoodDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getConnection();
	}
	

	public boolean addFood(Food food) {
	    boolean isFoodAdded = false;

	    // Follow exact database column order (excluding auto-increment food_id and created_at, updated_at)
	    String query = "INSERT INTO foods (name, description, price, serving_size, category_id, image_url, status, created_at, updated_at) " +
	                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, food.getName());
	        ps.setString(2, food.getDescription());
	        ps.setDouble(3, food.getPrice());
	        ps.setString(4, food.getServing_size());
	        ps.setInt(5, food.getCategory_id());
	        ps.setString(6, food.getImage_url());
	        
	        
	        ps.setString(7, "available");
	        
	        
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        ps.setTimestamp(8, currentTime);
	        ps.setTimestamp(9, currentTime);

	        if (ps.executeUpdate() > 0) {
	            isFoodAdded = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return isFoodAdded;
	}
	
	public List<Food> searchFoodByName(String keyword) {
	    List<Food> foodList = new ArrayList<>();
	    String query = "SELECT * FROM foods WHERE name LIKE ?";

	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, "%" + keyword + "%"); // partial match search
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Food food = new Food();
	                food.setFood_id(rs.getInt("food_id"));
	                food.setName(rs.getString("name"));
	                food.setDescription(rs.getString("description"));
	                food.setPrice(rs.getDouble("price"));
	                food.setServing_size(rs.getString("serving_size"));
	                food.setCategory_id(rs.getInt("category_id"));
	                food.setImage_url(rs.getString("image_url"));
	                foodList.add(food);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return foodList;
	}
	
	public List<Food> getAllFood() {
	    List<Food> foodList = new ArrayList<>();
	    String query = "SELECT * FROM foods";

	    try (PreparedStatement ps = conn.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Food food = new Food();
	            food.setFood_id(rs.getInt("food_id"));
	            food.setName(rs.getString("name"));
	            food.setDescription(rs.getString("description"));
	            food.setPrice(rs.getDouble("price"));
	            food.setServing_size(rs.getString("serving_size"));
	            food.setCategory_id(rs.getInt("category_id"));
	            food.setImage_url(rs.getString("image_url"));
	            foodList.add(food);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return foodList;
	}
	
	public boolean updateFood(Food food) {
	    boolean isUpdated = false;

	    // Update the query to include updated_at field
	    String query = "UPDATE foods SET name = ?, description = ?, price = ?, " +
	                   "serving_size = ?, category_id = ?, image_url = ?, updated_at = ? WHERE food_id = ?";

	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, food.getName());
	        ps.setString(2, food.getDescription());
	        ps.setDouble(3, food.getPrice());
	        ps.setString(4, food.getServing_size());
	        ps.setInt(5, food.getCategory_id());
	        ps.setString(6, food.getImage_url());

	        // Set the updated_at field to the current timestamp
	        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	        ps.setTimestamp(7, currentTime);

	        ps.setInt(8, food.getFood_id());

	        if (ps.executeUpdate() > 0) {
	            isUpdated = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return isUpdated;
	}
	public List<Category> getAllCategories() throws SQLException, ClassNotFoundException {
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT category_id, name FROM categories";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setCategory_id(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        }

        return categories;
    }

	
}
