package com.example.dao;

import com.example.dto.CartItemsDto;
import com.example.dto.ProductDetailDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartRepository {

    public boolean addProductToCart(int userId, int productId) {
        try (Connection connection = new JDBCConnection().connect()) {
            String selectSql = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectSql);
            selectStmt.setInt(1, userId);
            selectStmt.setInt(2, productId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int currentQuantity = rs.getInt("quantity");
                String updateSql = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                updateStmt.setInt(1, currentQuantity + 1);
                updateStmt.setInt(2, userId);
                updateStmt.setInt(3, productId);
                return updateStmt.executeUpdate() > 0;
            } else {
                String insertSql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, 1)";
                PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, productId);
                return insertStmt.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean increaseQuantity(int userId, int productId) {
        String getStockQuery = "SELECT quantity FROM products WHERE product_id = ?";
        String getCartQuantityQuery = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ?";
        String updateQuery = "UPDATE cart SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?";

        try (Connection connection = new JDBCConnection().connect()) {

            // Step 1: Get available stock
            int availableStock = 0;
            try (PreparedStatement stmt = connection.prepareStatement(getStockQuery)) {
                stmt.setInt(1, productId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        availableStock = rs.getInt("quantity");
                    }
                }
            }

            // Step 2: Get current cart quantity
            int currentCartQty = 0;
            try (PreparedStatement stmt = connection.prepareStatement(getCartQuantityQuery)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, productId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        currentCartQty = rs.getInt("quantity");
                    }
                }
            }

            // Step 3: Check before increasing
            if (currentCartQty < availableStock) {
                try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
                    stmt.setInt(1, userId);
                    stmt.setInt(2, productId);
                    return stmt.executeUpdate() > 0;
                }
            } else {
                // Not enough stock
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean decreaseQuantity(int userId, int productId) {
        String selectQuery = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection connection = new JDBCConnection().connect();
             PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setInt(1, userId);
            selectStmt.setInt(2, productId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                if (quantity > 1) {
                    String updateQuery = "UPDATE cart SET quantity = quantity - 1 WHERE user_id = ? AND product_id = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                    updateStmt.setInt(1, userId);
                    updateStmt.setInt(2, productId);
                    return updateStmt.executeUpdate() > 0;
                } else {
                    return removeProductFromCart(userId, productId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeProductFromCart(int userId, int productId) {
        String query = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection connection = new JDBCConnection().connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeCartItems(int userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (Connection connection = new JDBCConnection().connect();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CartItemsDto> getCartItems(int userId) {
        List<CartItemsDto> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart WHERE user_id = ?";
        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cartItems.add(new CartItemsDto(
                        rs.getInt("cart_id"),
                        userId,
                        rs.getInt("product_id"),
                        rs.getInt("quantity")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    public List<ProductDetailDto> getAllProductsInCartByUserId(int userId) {
        List<ProductDetailDto> products = new ArrayList<>();
        String query = """
                SELECT p.product_id, p.name, p.description, p.price, p.category_id, p.image_url, c.quantity
                FROM cart c
                JOIN products p ON c.product_id = p.product_id
                WHERE c.user_id = ?
                ORDER BY p.product_id
                """;
        try (Connection connection = new JDBCConnection().connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductDetailDto dto = new ProductDetailDto();
                dto.setProductId(rs.getInt("product_id"));
                dto.setName(rs.getString("name"));
                dto.setDescription(rs.getString("description"));
                dto.setPrice(rs.getDouble("price"));
                dto.setCategoryId(rs.getInt("category_id"));
                dto.setImageUrl(rs.getString("image_url"));
                dto.setQuantity(rs.getInt("quantity"));
                products.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
