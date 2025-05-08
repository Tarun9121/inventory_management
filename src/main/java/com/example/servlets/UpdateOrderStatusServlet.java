package com.example.servlets;

import com.example.dao.OrderDAO;
import com.example.models.Order;
import com.example.dao.JDBCConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the parameters
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String status = request.getParameter("status");

        // Update the order status in the database
        JDBCConnection jdbc = new JDBCConnection();
        OrderDAO orderDAO = null;
        try {
            orderDAO = new OrderDAO(jdbc.connect());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean updateSuccess = orderDAO.updateOrderStatus(orderId, status);

        // Redirect back to the orders page
        if (updateSuccess) {
            response.sendRedirect("admin_pages/manage_orders.jsp");
        } else {
            response.sendRedirect("admin_pages/manage_orders.jsp?error=1");
        }
    }

}
