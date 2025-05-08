package com.cravers_corner.controller.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cravers_corner.model.OrderItem;

public class OrderItemDAO {
    private Connection conn;

    public OrderItemDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert a new order item
    public void insertOrderItem(OrderItem item) throws SQLException {
        String sql = "INSERT INTO Order_items (order_id, food_id, quantity, price, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getFoodId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.setDouble(5, item.getSubtotal());
            stmt.executeUpdate();
        }
    }

    // Get all order items for a given order ID
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM Order_items WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setFoodId(rs.getInt("food_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal"));
                item.setCreatedAt(rs.getTimestamp("created_at"));
                item.setUpdatedAt(rs.getTimestamp("updated_at"));
                items.add(item);
            }
        }
        return items;
    }

    // Optional: delete all items for an order (e.g., if cancelling)
    public void deleteOrderItemsByOrderId(int orderId) throws SQLException {
        String sql = "DELETE FROM Order_items WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }
}
