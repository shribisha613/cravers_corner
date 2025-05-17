package com.cravers_corner.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cravers_corner.controller.database.DatabaseConnection;
import com.cravers_corner.model.Cart;
import com.cravers_corner.model.CartItem;

public class CartDAO {
	private Connection conn;
	private PreparedStatement ps;
	// Constructor: Initializes the database connection when an object is created
	public CartDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getConnection();
	}
	
	 public Cart getCartByCustomerId(int customer_id) throws SQLException {
    String sql = "SELECT * FROM Carts WHERE customer_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, customer_id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Cart cart = new Cart();
            cart.setCart_id(rs.getInt("cart_id"));
            cart.setCustomer_id(rs.getInt("customer_id"));
            cart.setTotal_amount(rs.getDouble("total_amount"));
            cart.setCreated_at(rs.getTimestamp("created_at"));
            cart.setUpdated_at(rs.getTimestamp("updated_at"));
            return cart;
        }
    }
    return null;
}
	 
	 public int createCart(Cart cart) throws SQLException {
	        String sql = "INSERT INTO Carts (customer_id, total_amount) VALUES (?, ?)";
	        try (PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
	            stmt.setInt(1, cart.getCustomer_id());
	            stmt.setDouble(2, cart.getTotal_amount());
	            stmt.executeUpdate();

	            ResultSet keys = stmt.getGeneratedKeys();
	            if (keys.next()) {
	                return keys.getInt(1); // Return newly generated cart_id
	            }
	        }
	        return -1;
	    }
	 
	 public void updateCartTotal(int cart_id, double total_amount) throws SQLException {
	        String sql = "UPDATE Carts SET total_amount = ?, updated_at = CURRENT_TIMESTAMP WHERE cart_id = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setDouble(1, total_amount);
	            stmt.setInt(2, cart_id);
	            stmt.executeUpdate();
	        }
	    }
	 
	 public List<CartItem> getCartItems(int cart_id) throws SQLException {
	        List<CartItem> items = new ArrayList<>();
	        String sql = "SELECT * FROM Cart_items WHERE cart_id = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, cart_id);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                CartItem item = new CartItem();
	                item.setCart_item_id(rs.getInt("cart_item_id"));
	                item.setCart_id(rs.getInt("cart_id"));
	                item.setFood_id(rs.getInt("food_id"));
	                item.setQuantity(rs.getInt("quantity"));
	                item.setPrice(rs.getDouble("price"));
	                item.setSubtotal(rs.getDouble("subtotal"));
	                item.setCreated_at(rs.getTimestamp("created_at"));
	                item.setUpdated_at(rs.getTimestamp("updated_at"));
	                items.add(item);
	            }
	        }
	        return items;
	    }
	 
	 public void clearCartItems(int cart_id) throws SQLException {
	        String sql = "DELETE FROM Cart_items WHERE cart_id = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, cart_id);
	            stmt.executeUpdate();
	        }
	    }

}
