package com.example.service;

import com.example.dao.CartRepository;
import com.example.dao.OrderRepository;
import com.example.dto.OrderDto;
import com.example.dto.OrderItemDto;
import com.example.dto.ProductDetailDto;

import java.util.List;

public class OrderService {
    private CartRepository cartRepository = new CartRepository();
    private OrderRepository orderRepository = new OrderRepository();
    private CartService cartService = new CartService();

    public void placeOrder(int userId) {
        List<ProductDetailDto> productDetailDtoList = cartRepository.getAllProductsInCartByUserId(userId);
        double totalCost = cartService.getTotalCostOfProducts(productDetailDtoList);
        int orderId = orderRepository.placeOrder(userId, totalCost);

        for (ProductDetailDto product : productDetailDtoList) {
            int productId = product.getProductId(); // Ensure this is the correct column
            int quantity = product.getQuantity();
            double price = product.getPrice();

            // Ensure this method is called correctly
            boolean isItemInserted = orderRepository.placeOrderItem(orderId, productId, quantity, price);
            if (!isItemInserted) {
                System.out.println("Failed to insert order item for product ID: " + productId);
            }
        }

        cartRepository.removeCartItems(userId);
    }


    public List<OrderDto> getUserOrders(int userId) {
        List<OrderDto> orders = orderRepository.getOrdersByUserId(userId);
        for (OrderDto order : orders) {
            List<OrderItemDto> orderItems = orderRepository.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItems(orderItems);
        }
        return orders;
    }
}
