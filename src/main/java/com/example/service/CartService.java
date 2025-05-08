package com.example.service;

import com.example.dto.ProductDetailDto;

import java.util.List;

public class CartService {

    public double getTotalCostOfProducts(List<ProductDetailDto> products) {
        double totalPrice = 0;
        for (ProductDetailDto product : products) {
            // Get the quantity directly from the product
            int quantity = product.getQuantity();
            totalPrice += quantity * product.getPrice();  // Multiply by product price
        }
        return totalPrice;
    }
}
