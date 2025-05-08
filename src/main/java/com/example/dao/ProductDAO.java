package com.example.dao;

import com.example.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> searchProducts(String keyword) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.product_id, p.name, p.description, p.price, p.image_url, c.category_name " +
                "FROM products p JOIN categories c ON p.category_id = c.category_id " +
                "WHERE p.name LIKE ? OR c.category_name LIKE ?";

        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String likeKeyword = "%" + keyword + "%";
            stmt.setString(1, likeKeyword);
            stmt.setString(2, likeKeyword);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId((rs.getInt("product_id")));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("image_url"));
                product.setCategoryName(rs.getString("category_name"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return products;
    }
}
