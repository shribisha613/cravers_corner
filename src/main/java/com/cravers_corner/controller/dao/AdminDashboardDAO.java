package com.cravers_corner.controller.dao;
// AdminDashboardDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.cravers_corner.controller.database.DatabaseConnection;

public class AdminDashboardDAO {

    public int getTotalCategories() {
        int count = 0;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM Categories;");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalFoodItems() {
        int count = 0;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM Foods;");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    
    public int getTotalCustomers() {
        int count = 0;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE role != 'admin';");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public int getTodaysOrderCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Orders WHERE DATE(order_date) = CURDATE()";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    
    public String getMostOrderedCategory() {
        String category = "N/A";
        String sql = "SELECT c.name, SUM(oi.quantity) AS total_sold " +
                     "FROM Order_items oi " +
                     "JOIN Foods f ON oi.food_id = f.food_id " +
                     "JOIN Categories c ON f.category_id = c.category_id " +
                     "GROUP BY c.category_id " +
                     "ORDER BY total_sold DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                category = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public String getMostOrderedItem() {
        String item = "N/A";
        String sql = "SELECT f.name, SUM(oi.quantity) AS total_sold " +
                     "FROM Order_items oi " +
                     "JOIN Foods f ON oi.food_id = f.food_id " +
                     "GROUP BY oi.food_id " +
                     "ORDER BY total_sold DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                item = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    
    public int getUsersRegisteredLastMonth() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Users " +
                     "WHERE role = 'customer' AND created_at >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
