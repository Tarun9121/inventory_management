package com.example.dao;

import com.example.models.SupplierOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderManagementDAO {

    private Connection connection;

    public OrderManagementDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to update the order status
    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
