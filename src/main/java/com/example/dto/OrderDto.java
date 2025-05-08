package com.example.dto;

import java.util.List;

public class OrderDto {
    private int orderId;
    private int userId;
    private String orderDate;
    private double totalCost;
    private List<OrderItemDto> orderItems; // List of items in the order
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderDate='" + orderDate + '\'' +
                ", totalCost=" + totalCost +
                ", orderItems=" + orderItems +
                ", status=" + status +
                '}';
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
