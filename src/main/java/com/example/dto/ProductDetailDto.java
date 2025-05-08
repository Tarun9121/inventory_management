package com.example.dto;

public class ProductDetailDto {

    private int productId;
    private String name;
    private String description;
    private double price;
    private int categoryId;
    private String imageUrl;
    private int quantity; // Directly store the quantity

    // Getters and Setters for main class fields

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() { // Getter for quantity
        return quantity;
    }

    public void setQuantity(int quantity) { // Setter for quantity
        this.quantity = quantity;
    }
}
