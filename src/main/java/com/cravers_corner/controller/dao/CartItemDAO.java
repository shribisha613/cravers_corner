package com.cravers_corner.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cravers_corner.controller.database.DatabaseConnection;
import com.cravers_corner.model.CartItem;

public class CartItemDAO {
	
	private Connection conn;

    public CartItemDAO() throws ClassNotFoundException, SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

 // 1. Called when pressing 'Add to Cart' from food detail page
    public void addOrIncrementCartItem(CartItem item) throws SQLException {
        String selectSql = "SELECT quantity FROM cart_items WHERE cart_id = ? AND food_id = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setInt(1, item.getCart_id());
            selectStmt.setInt(2, item.getFood_id());

            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                int existingQty = rs.getInt("quantity");
                int newQty = existingQty + item.getQuantity(); // usually item.getQuantity() = 1
                if (newQty > 10) newQty = 10;
                double newSubtotal = newQty * item.getPrice();

                String updateSql = "UPDATE cart_items SET quantity = ?, subtotal = ? WHERE cart_id = ? AND food_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, newQty);
                    updateStmt.setDouble(2, newSubtotal);
                    updateStmt.setInt(3, item.getCart_id());
                    updateStmt.setInt(4, item.getFood_id());
                    updateStmt.executeUpdate();
                }

            } else {
                // New insert
                String insertSql = "INSERT INTO cart_items (cart_id, food_id, quantity, price, subtotal) VALUES (?, ?, ?, ?, ?)";
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
    
   
    public List<CartItem> getCartItems(int cart_id) {
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT ci.*, f.name AS food_name, f.image_url AS image_url " +
                     "FROM cart_items ci " +
                     "JOIN foods f ON ci.food_id = f.food_id " +
                     "WHERE ci.cart_id = ? AND f.status = 'available'";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cart_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCart_item_id(rs.getInt("cart_item_id"));
                item.setFood_id(rs.getInt("food_id"));
                item.setCart_id(cart_id);
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal"));
                item.setFood_name(rs.getString("food_name"));
                item.setImage_url(rs.getString("image_url"));
                System.out.println(">> Extracted food_name: " + rs.getString("food_name"));
                System.out.println(">> Extracted image_url: " + rs.getString("image_url"));

                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    
    public CartItem getCartItemById(int cart_item_id) throws SQLException {
        String sql = "SELECT * FROM Cart_items WHERE cart_item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cart_item_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CartItem item = new CartItem();
                item.setCart_item_id(rs.getInt("cart_item_id"));
                item.setCart_id(rs.getInt("cart_id"));
                item.setFood_id(rs.getInt("food_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal"));
                return item;
            }
        }
        return null;
    }
    
    public boolean updateCartItemQuantity(int cart_item_id, int newQty, double price) throws SQLException {
        if (newQty < 1) newQty = 1;
        if (newQty > 10) newQty = 10;

        double newSubtotal = newQty * price;

        String sql = "UPDATE cart_items SET quantity = ?, subtotal = ? WHERE cart_item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newQty);
            stmt.setDouble(2, newSubtotal);
            stmt.setInt(3, cart_item_id);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCartItem(CartItem item) throws SQLException {
        String sql = "UPDATE Cart_items SET quantity = ?, subtotal = ? WHERE cart_item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getQuantity());
            stmt.setDouble(2, item.getSubtotal());
            stmt.setInt(3, item.getCart_item_id());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // ✅ true if at least one row was updated
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 
        }
    }

    public void removeCartItem(int cart_item_id) throws SQLException {
        String sql = "DELETE FROM Cart_items WHERE cart_item_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cart_item_id);
            stmt.executeUpdate();
        }
    }

    
    public void deleteCartItemsByUserId(int user_id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE ci FROM cart_items ci JOIN carts c ON ci.cart_id = c.cart_id WHERE c.customer_id = ?";
        
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user_id);
            ps.executeUpdate();
        }
    }
}
