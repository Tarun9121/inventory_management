package com.example.servlets;

import com.example.dao.OrderRepository;
import com.example.service.OrderService;
import com.example.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/confirmPayment")
public class ConfirmPaymentServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private OrderRepository orderRepository = new OrderRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if(session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");

            // Update the user's latest order status to 'CONFIRMED'
            orderRepository.confirmPendingOrders(user.getId());

            // After confirming, forward to loading.jsp
            req.getRequestDispatcher("order_pages/loading.jsp").forward(req, resp);
        }
    }
}
