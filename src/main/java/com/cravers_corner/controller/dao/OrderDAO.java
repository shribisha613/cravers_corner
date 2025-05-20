package com.cravers_corner.controller.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public int createOrderWithItems(Order order) throws SQLException {
        int orderId = -1;
        String insertOrderSql = "INSERT INTO Orders (customer_id, status, total_amount, order_note, order_date) VALUES (?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, order.getCustomerId());
                stmt.setString(2, order.getStatus());
                stmt.setDouble(3, order.getTotalAmount());
                stmt.setString(4, order.getOrderNote());
                stmt.setTimestamp(5, order.getOrderDate());
                
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) orderId = rs.getInt(1);
            }
            for (OrderItem item : order.getItems()) {
                item.setOrderId(orderId);
                orderItemDAO.insertOrderItem(item);
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        return orderId;
    }


    // Get order by ID
    public Order getOrderById(int orderId) throws SQLException {
        String sql = "SELECT order_id, customer_id, status, total_amount, order_note, created_at, updated_at FROM Orders WHERE order_id = ?";
        
        Order order = new Order();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
           
            while(rs.next()) {
                
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setOrderNote(rs.getString("order_note"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdatedAt(rs.getTimestamp("updated_at"));
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
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
