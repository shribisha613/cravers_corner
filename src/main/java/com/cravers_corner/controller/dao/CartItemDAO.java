package com.cravers_corner.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cravers_corner.controller.database.DatabaseConnection;
import com.cravers_corner.model.CartItem;

public class CartItemDAO {
	
	private Connection conn;

    public CartItemDAO() throws ClassNotFoundException, SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    public void addOrUpdateCartItem(CartItem item) throws SQLException {
        // Check if item exists for cart_id and food_id
        String checkSql = "SELECT quantity FROM Cart_items WHERE cart_id = ? AND food_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, item.getCart_id());
            checkStmt.setInt(2, item.getFood_id());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                // If exists, update quantity and subtotal
                int newQuantity = rs.getInt("quantity") + item.getQuantity();
                double newSubtotal = newQuantity * item.getPrice();
                String updateSql = "UPDATE Cart_items SET quantity = ?, subtotal = ?, updated_at = CURRENT_TIMESTAMP WHERE cart_id = ? AND food_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, newQuantity);
                    updateStmt.setDouble(2, newSubtotal);
                    updateStmt.setInt(3, item.getCart_id());
                    updateStmt.setInt(4, item.getFood_id());
                    updateStmt.executeUpdate();
                }
            } else {
                // Insert new item
                String insertSql = "INSERT INTO Cart_items (cart_id, food_id, quantity, price, subtotal) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, item.getCart_id());
                    insertStmt.setInt(2, item.getFood_id());
                    insertStmt.setInt(3, item.getQuantity());
                    insertStmt.setDouble(4, item.getPrice());
                    insertStmt.setDouble(5, item.getSubtotal());
                    insertStmt.executeUpdate();
                }
            }
        }
    }

}
