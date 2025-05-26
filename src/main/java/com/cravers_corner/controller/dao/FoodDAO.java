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
	// Constructor initializes the database connection when an object is created
	public FoodDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getConnection();
	}
	
	// Method to add a new food record into 'foods' table
	public boolean addFood(Food food) {
	    boolean isFoodAdded = false;

	    // SQL insert query excluding auto-increment food_id and timestamps (set automatically)
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
	
	
	public Food getFoodById(int food_id) {
	    Food food = null;
	    String sql = "SELECT f.*, c.name as category_name FROM foods f JOIN categories c ON f.category_id = c.category_id WHERE f.food_id = ? AND f.status = 'available'";

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, food_id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            food = new Food();
	            food.setFood_id(rs.getInt("food_id"));
	            food.setName(rs.getString("name"));               // Food name
	            food.setDescription(rs.getString("description"));
	            food.setPrice(rs.getDouble("price"));
	            food.setCategory_id(rs.getInt("category_id"));
	            food.setImage_url(rs.getString("image_url"));
	            food.setStatus(rs.getString("status"));
	            food.setServing_size(rs.getString("serving_size"));
	            food.setCreated_at(rs.getTimestamp("created_at"));
	            food.setUpdated_at(rs.getTimestamp("updated_at"));
	            food.setCategory_name(rs.getString("category_name"));  // Category name
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Food id not found");
	    }

	    return food;
	}

	
	
	public List<Food> searchFoodByName(String keyword) {
	    List<Food> foodList = new ArrayList<>();
	    String query = "SELECT f.*, c.name AS category_name FROM foods f JOIN categories c ON f.category_id = c.category_id WHERE f.name LIKE ? AND f.status = 'available'";

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
	                food.setCreated_at(rs.getTimestamp("created_at"));
	                food.setUpdated_at(rs.getTimestamp("updated_at"));
	                
	                // Set the category name from the joined table
	                food.setCategory_name(rs.getString("category_name"));
	                foodList.add(food);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return foodList;
	}
	
	public List<Food> getAllFood() throws SQLException {
	    List<Food> foodList = new ArrayList<>();
	    String query = "SELECT f.food_id, f.name, f.description, f.serving_size, f.price, f.image_url, f.category_id, " +
	             "c.name AS category_name " +
	             "FROM foods f " +
	             "JOIN categories c ON f.category_id = c.category_id " +
	             "WHERE f.status = 'available'";   // Join the category table to get the name

	    try (
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Food food = new Food();
	            food.setFood_id(rs.getInt("food_id"));
	            food.setName(rs.getString("name"));
	            food.setDescription(rs.getString("description"));
	            food.setServing_size(rs.getString("serving_size"));
	            food.setPrice(rs.getDouble("price"));
	            food.setImage_url(rs.getString("image_url"));
	            food.setCategory_id(rs.getInt("category_id"));
	            
	            food.setCategory_name(rs.getString("category_name")); // Set category name

	            foodList.add(food);
	        }
	    }
	    System.out.println("Food list size: " + foodList.size()); 
	    return foodList;
	}
	
	public List<Food> getAllFood(String sortOrder) throws SQLException {
	    List<Food> foodList = new ArrayList<>();
	    String query = "SELECT f.food_id, f.name, f.description, f.serving_size, f.price, f.image_url, f.category_id, c.name AS category_name FROM foods f JOIN categories c ON f.category_id = c.category_id WHERE f.status = 'available' ORDER BY  "  + sortOrder; // Join the category table to get the name

	    try (
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Food food = new Food();
	            food.setFood_id(rs.getInt("food_id"));
	            food.setName(rs.getString("name"));
	            food.setDescription(rs.getString("description"));
	            food.setServing_size(rs.getString("serving_size"));
	            food.setPrice(rs.getDouble("price"));
	            food.setImage_url(rs.getString("image_url"));
	            food.setCategory_id(rs.getInt("category_id"));
	            food.setCategory_name(rs.getString("category_name")); // Set category name

	            foodList.add(food);
	        }
	    }
	    System.out.println("Food list size: " + foodList.size()); 
	    return foodList;
	}

	// Helper method to get category_id from category name
	public int getCategoryIdByName(String name) throws SQLException {
        String query = "SELECT category_id FROM categories WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("category_id");
                }
            }
        }
        return -1; //  throw exception if not found
    }


	// Get food items by category name (e.g. "Nepali", "Italian")
    public List<Food> getFoodByCategory(String categoryName) throws SQLException {
        List<Food> foodList = new ArrayList<>();
        
        // First get category_id for given categoryName
        int categoryId = getCategoryIdByName(categoryName);
        if (categoryId == -1) return foodList; // no matching category
        
        // Query to get foods with the category_id and status 'available'
        String query = "SELECT f.*, c.name AS category_name " +
                "FROM foods f JOIN categories c ON f.category_id = c.category_id " +
                "WHERE f.category_id = ? AND f.status = 'available'";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, categoryId); // now using proper ID
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Food food = new Food();
                    food.setFood_id(rs.getInt("food_id"));
                    food.setName(rs.getString("name"));
                    food.setDescription(rs.getString("description"));
                    food.setServing_size(rs.getString("serving_size"));
                    food.setPrice(rs.getDouble("price"));
                    food.setImage_url(rs.getString("image_url"));
                    food.setCategory_id(rs.getInt("category_id"));
                    food.setCategory_name(rs.getString("category_name"));
                    foodList.add(food);
                }
            }
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
	
	public boolean deleteFoodById(int food_id) {
	    boolean isDeleted = false;
	    String sql = "UPDATE foods SET status = 'discontinued', updated_at = CURRENT_TIMESTAMP WHERE food_id = ?";

	    try (
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, food_id);
	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            isDeleted = true;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return isDeleted;
	}
	
	

	 public List<Food> getTop3MostOrderedFoods() throws SQLException {
	        List<Food> topFoods = new ArrayList<>();

	        String sql = "SELECT f.food_id, f.name, f.image_url, f.price, SUM(oi.quantity) AS total_ordered " +
	                 "FROM foods f " +
	                 "JOIN order_items oi ON f.food_id = oi.food_id " +
	                 "WHERE f.status = 'available' " +
	                 "GROUP BY f.food_id, f.name, f.image_url, f.price " +
	                 "ORDER BY total_ordered DESC " +
	                 "LIMIT 3";

	        try (PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Food food = new Food();
	                food.setFood_id(rs.getInt("food_id"));
	                food.setName(rs.getString("name"));
	                food.setImage_url(rs.getString("image_url"));
	                food.setPrice(rs.getDouble("price"));
	                // You can also store total_ordered if needed
	                topFoods.add(food);
	            }
	        }

	        return topFoods;
	    }
	
	
}
