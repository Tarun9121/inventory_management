package com.example.servlets;

import com.example.dao.JDBCConnection;
import com.example.models.Order;
import com.example.service.AdminOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/AdminManageOrdersServlet")
public class AdminManageOrdersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = new JDBCConnection().connect();
            AdminOrderService orderService = new AdminOrderService(connection);
            List<Order> orders = orderService.getConfirmedOrders();

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("admin_pages/manage_orders.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to fetch orders. Try again later.");
            request.getRequestDispatcher("admin_pages/admin_dashboard.jsp").forward(request, response);
        }
    }
}
