package com.example.dao;

import com.example.dto.ProductDetailDto;
import com.example.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> getAllProductsByCategoryId(int categoryId) {
        String sql = "SELECT * FROM products WHERE category_id = ?";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = new JDBCConnection().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("category_id"),
                            resultSet.getString("image_url")
                    );
                    productList.add(product);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    public ProductDetailDto getProductWithDetail(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        ProductDetailDto productDetailDto = new ProductDetailDto();

        try (Connection connection = new JDBCConnection().connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    productDetailDto.setProductId(resultSet.getInt("product_id"));
                    productDetailDto.setName(resultSet.getString("name"));
                    productDetailDto.setCategoryId(resultSet.getInt("category_id"));
                    productDetailDto.setPrice(resultSet.getDouble("price"));
                    productDetailDto.setQuantity(resultSet.getInt("quantity"));
                    productDetailDto.setDescription(resultSet.getString("description"));
                    productDetailDto.setImageUrl(resultSet.getString("image_url"));

                    // Since we're not using variants anymore, no need to query the product_variants table
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productDetailDto;
    }
}
