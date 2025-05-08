package com.example.dao;

import com.example.dto.OrderDto;
import com.example.dto.OrderItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    // Method to retrieve orders by userId
    public List<OrderDto> getOrdersByUserId(int userId) {
        List<OrderDto> orders = new ArrayList<>();
        String sql = "SELECT order_id, user_id, order_date, total_amount, status FROM orders WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderDto order = new OrderDto();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setOrderDate(rs.getString("order_date"));
                    order.setTotalCost(rs.getDouble("total_amount"));
                    order.setStatus(rs.getString("status"));
                    order.setOrderItems(getOrderItemsByOrderId(order.getOrderId()));
                    orders.add(order);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }


    // Method to retrieve order items by orderId
    public List<OrderItemDto> getOrderItemsByOrderId(int orderId) {
        List<OrderItemDto> orderItems = new ArrayList<>();
        String sql = "SELECT oi.product_id, oi.quantity, oi.price, p.name AS product_name, p.image_url AS product_image_url " +
                "FROM order_items oi " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id = ?";

        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItemDto item = new OrderItemDto();
                    item.setProductId(rs.getInt("product_id"));
                    item.setProductName(rs.getString("product_name"));  // Use 'product_name' here as alias
                    item.setProductImageUrl(rs.getString("product_image_url"));  // Correct column name here
                    item.setPrice(rs.getDouble("price"));
                    item.setQuantity(rs.getInt("quantity"));
                    orderItems.add(item);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItems;
    }




    public int placeOrder(int userId, double totalPrice) {
        String sql = "insert into orders (user_id, total_amount) values (?, ?)";
        int orderId = -1;
        try(Connection connection = new JDBCConnection().connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setDouble(2, totalPrice);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    public void confirmPendingOrders(int userId) {
        String sql = "UPDATE orders SET status = 'CONFIRMED' WHERE user_id = ? AND status = 'PENDING'";

        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Successfully confirmed " + rowsUpdated + " orders for user ID: " + userId);
            } else {
                System.out.println("No pending orders found for user ID: " + userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean placeOrderItem(int orderId, int productId, int quantity, double price) {
        String insertOrderItemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        String updateProductStockSql = "UPDATE products SET quantity = quantity - ? WHERE product_id = ?";
        boolean result = false;

        try (Connection connection = new JDBCConnection().connect()) {

            // Step 1: Insert into order_items table
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderItemSql)) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, productId);
                preparedStatement.setInt(3, quantity);
                preparedStatement.setDouble(4, price);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    result = true; // Order item placed successfully

                    // Step 2: Update the stock in products table
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateProductStockSql)) {
                        updateStmt.setInt(1, quantity);  // Decrease the stock by the ordered quantity
                        updateStmt.setInt(2, productId);

                        // Execute the update
                        updateStmt.executeUpdate();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
