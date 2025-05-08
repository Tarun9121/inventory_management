package com.example.dto;

public class OrderItemDto {
    private int productId;
    private String productName;
    private String productImageUrl;
    private String size;
    private double price;
    private int quantity;

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
