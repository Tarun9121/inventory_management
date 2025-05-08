package com.example.dao;

import com.example.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {
    private Connection conn;

    public List<Product> getWishlistItems(int userId) {
        List<Product> wishlistItems = new ArrayList<>();

        String sql = "SELECT p.product_id, p.name, p.price, p.description, p.image_url " +
                "FROM wishlist w " +
                "JOIN products p ON w.product_id = p.product_id " +
                "WHERE w.user_id = ?";

        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setImageUrl(rs.getString("image_url"));

                wishlistItems.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return wishlistItems;
    }

    public WishlistDAO(Connection conn) {
        this.conn = conn;
    }

    public void addToWishlist(int userId, int productId) throws SQLException {
        // First, check if the product is already in the wishlist for the user
        String checkSql = "SELECT COUNT(*) FROM wishlist WHERE user_id = ? AND product_id = ?";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, productId);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Product already in wishlist, no need to insert
                return;
            }
        }

        // If not in wishlist, insert the product into the wishlist
        String sql = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }


    public void removeFromWishlist(int userId, int productId) throws SQLException {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND product_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }
}
