package com.example.dto;

import java.util.Objects;

public class CartItemsDto {
    private int id;
    private int userId;
    private int variantId;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemsDto that)) return false;
        return id == that.id && userId == that.userId && variantId == that.variantId && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, variantId, quantity);
    }

    @Override
    public String toString() {
        return "CartItemsDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", variantId=" + variantId +
                ", quantity=" + quantity +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public CartItemsDto() {
    }

    public CartItemsDto(int id, int userId, int variantId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.variantId = variantId;
        this.quantity = quantity;
    }

    public CartItemsDto(int userId, int variantId, int quantity) {
        this.userId = userId;
        this.variantId = variantId;
        this.quantity = quantity;
    }
}
