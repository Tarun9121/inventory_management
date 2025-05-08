package com.example.models;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int variantId;
    private int quantity;
    private double price;

    // Getters, Setters, Constructors

    public OrderItem() {}

    public OrderItem(int orderItemId, int orderId, int variantId, int quantity, double price) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", variantId=" + variantId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
