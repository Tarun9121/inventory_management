package com.example.models;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private String userName;
    private String userEmail;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;

    // No-arg constructor
    public Order() {}

    // All-arg constructor
    public Order(int orderId, int userId, String userName, String userEmail, Timestamp orderDate, double totalAmount, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
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

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
