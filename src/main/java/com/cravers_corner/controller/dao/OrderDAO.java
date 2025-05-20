package com.cravers_corner.controller.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cravers_corner.controller.database.DatabaseConnection;
import com.cravers_corner.model.Order;
import com.cravers_corner.model.OrderItem;

public class OrderDAO {
    public Connection conn;
    private OrderItemDAO orderItemDAO;

    public OrderDAO() throws ClassNotFoundException, SQLException {
    
        this.conn = DatabaseConnection.getConnection();
        this.orderItemDAO = new OrderItemDAO();
    }
    
    public int createOrderWithItems(Order order) throws SQLException, ClassNotFoundException {
        int generatedOrderId = 0;
        String sql = "INSERT INTO orders (customer_id, total_amount, status, order_date, created_at, updated_at, order_note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, order.getCustomerId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setString(3, order.getStatus());
            ps.setTimestamp(4, order.getOrderDate());
            ps.setTimestamp(5, order.getCreatedAt());
            ps.setTimestamp(6, order.getUpdatedAt());
            ps.setString(7, order.getOrderNote());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedOrderId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        }
        return generatedOrderId;
    }



    // Get order by ID
    public Order getOrderById(int orderId) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setOrderNote(rs.getString("order_note"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdatedAt(rs.getTimestamp("updated_at"));
                return order;
            }
        }
        return null;
    }
    // Get all orders
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        }
        return orders;
    }

    // Update order status
    public boolean updateOrderStatus(int orderId, String status) throws SQLException {
        String sql = "UPDATE Orders SET status = ? WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Delete order
    public boolean deleteOrder(int orderId) throws SQLException {
        String sql = "DELETE FROM Orders WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Extract order from ResultSet
    private Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setCustomerId(rs.getInt("customer_id"));
        order.setOrderDate(rs.getTimestamp("order_date"));
        order.setStatus(rs.getString("status"));
        order.setTotalAmount(rs.getDouble("total_amount"));
        order.setOrderNote(rs.getString("order_note"));
        order.setCreatedAt(rs.getTimestamp("created_at"));
        order.setUpdatedAt(rs.getTimestamp("updated_at"));
        return order;
    }


}
