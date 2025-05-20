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
        String sql = "SELECT o.order_id, o.customer_id, o.status, o.total_amount, o.order_note, o.order_date, " +
                     "o.created_at, o.updated_at, " +
                     "u.phone, u.current_address, " +
                     "oi.order_item_id, oi.food_id, oi.quantity, oi.price, oi.subtotal, " +
                     "f.name, f.image_url " +
                     "FROM Orders o " +
                     "JOIN Users u ON o.customer_id = u.user_id " +
                     "LEFT JOIN Order_items oi ON o.order_id = oi.order_id " +
                     "LEFT JOIN Foods f ON oi.food_id = f.food_id " +
                     "WHERE o.order_id = ?";
        
        Order order = null;
        List<OrderItem> items = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (order == null) {
                    order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setCustomerId(rs.getInt("customer_id"));
                    order.setStatus(rs.getString("status"));
                    order.setTotalAmount(rs.getDouble("total_amount"));
                    order.setOrderNote(rs.getString("order_note"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setCreatedAt(rs.getTimestamp("created_at"));
                    order.setUpdatedAt(rs.getTimestamp("updated_at"));

                    // Customer details
                    order.setOrderContact(rs.getString("phone"));
                    order.setShippingAddress(rs.getString("current_address"));
                }

                int orderItemId = rs.getInt("order_item_id");
                if (orderItemId > 0) { // valid order item row
                    OrderItem item = new OrderItem();
                    item.setOrderItemId(orderItemId);
                    item.setOrderId(rs.getInt("order_id"));
                    item.setFoodId(rs.getInt("food_id"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    item.setSubtotal(rs.getDouble("subtotal"));
                    item.setFood_name(rs.getString("name"));
                    item.setImage_url(rs.getString("image_url"));

                    items.add(item);
                }
            }
        }

        if (order != null) {
            order.setItems(items);
        }

        return order;
    }

    // Get all orders
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE status = 'pending' ORDER BY order_date ASC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        }
        return orders;
    }

    
    public boolean updateOrderStatus(int orderId, String status) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            int rows = ps.executeUpdate();
            return rows > 0;
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
    
    
    
    public List<Order> getCompletedOrdersByCustomerId(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT o.order_id, o.customer_id, o.status, o.total_amount, o.order_note, o.order_date, o.created_at, o.updated_at, oi.order_item_id, oi.food_id, oi.quantity, oi.price, oi.subtotal, f.name FROM Orders o JOIN Order_items oi ON o.order_id = oi.order_id JOIN Foods f ON oi.food_id = f.food_id WHERE o.customer_id = ? AND (o.status = 'complete') ORDER BY o.order_date DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            Order currentOrder = null;
            int currentOrderId = -1;

            while (rs.next()) {
                int orderId = rs.getInt("order_id");

                if (orderId != currentOrderId) {
                    currentOrder = new Order();
                    currentOrder.setOrderId(orderId);
                    currentOrder.setCustomerId(rs.getInt("customer_id"));
                    currentOrder.setStatus(rs.getString("status"));
                    currentOrder.setTotalAmount(rs.getDouble("total_amount"));
                    currentOrder.setOrderNote(rs.getString("order_note"));
                    currentOrder.setOrderDate(rs.getTimestamp("order_date"));
                    currentOrder.setCreatedAt(rs.getTimestamp("created_at"));
                    currentOrder.setUpdatedAt(rs.getTimestamp("updated_at"));
                    currentOrder.setItems(new ArrayList<>());
                    orders.add(currentOrder);
                    currentOrderId = orderId;
                }

                OrderItem item = new OrderItem();
                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(orderId);
                item.setFoodId(rs.getInt("food_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal"));
                item.setFood_name(rs.getString("name"));
                item.setImage_url(rs.getString("image_url"));

                currentOrder.getItems().add(item);
            }
        }

        return orders;
    }

	public OrderItemDAO getOrderItemDAO() {
		return orderItemDAO;
	}

	public void setOrderItemDAO(OrderItemDAO orderItemDAO) {
		this.orderItemDAO = orderItemDAO;
	}



    
    
    


}