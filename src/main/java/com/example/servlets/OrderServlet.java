package com.example.servlets;

import com.example.dto.OrderDto;
import com.example.models.User;
import com.example.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            orderService.placeOrder(user.getId());
            double totalPrice = Double.valueOf(req.getParameter("totalPrice"));
            req.setAttribute("totalPrice", totalPrice);
            req.getRequestDispatcher("order_pages/payment.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            // Get the list of orders for the user
            List<OrderDto> orders = orderService.getUserOrders(user.getId());
            System.out.println(orders);
            req.setAttribute("orders", orders);
            // Forward to the JSP page to display orders
            req.getRequestDispatcher("order_pages/order_list.jsp").forward(req, resp);
        }
    }

}
