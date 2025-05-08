package com.example.servlets;

import com.example.dao.JDBCConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SalesAndReportsServlet")
public class SalesAndReportsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        // Initialize variables to store the required data
        int totalSuppliers = 0;
        int totalOrders = 0;
        Map<String, Integer> orderStatusCount = new HashMap<>();
        Map<Integer, SupplierOrderData> supplierOrdersCount = new HashMap<>();

        try {
            // Get the database connection
            connection = new JDBCConnection().connect();
            stmt = connection.createStatement();

            // Query to get the total number of suppliers from the 'supplier' table
            String supplierQuery = "SELECT COUNT(*) FROM supplier";
            rs = stmt.executeQuery(supplierQuery);
            if (rs.next()) {
                totalSuppliers = rs.getInt(1);
            }

            // Query to get the total number of orders from the 'orders' table
            String orderQuery = "SELECT COUNT(*) FROM orders";
            rs = stmt.executeQuery(orderQuery);
            if (rs.next()) {
                totalOrders = rs.getInt(1);
            }

            // Query to count the number of orders by their 'status' from the 'orders' table
            String orderStatusQuery = "SELECT status, COUNT(*) FROM orders GROUP BY status";
            rs = stmt.executeQuery(orderStatusQuery);
            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt(2);
                orderStatusCount.put(status, count);
            }

            // Query to get the number of orders for each supplier and the supplier name
            String supplierOrdersQuery = "SELECT p.supplier_id, s.name AS supplier_name, COUNT(o.order_id) " +
                    "FROM products p " +
                    "INNER JOIN order_items oi ON p.product_id = oi.product_id " +
                    "INNER JOIN orders o ON oi.order_id = o.order_id " +
                    "INNER JOIN supplier s ON p.supplier_id = s.id " +
                    "GROUP BY p.supplier_id";
            rs = stmt.executeQuery(supplierOrdersQuery);
            while (rs.next()) {
                int supplierId = rs.getInt(1);
                String supplierName = rs.getString("supplier_name");
                int orderCount = rs.getInt(3);
                supplierOrdersCount.put(supplierId, new SupplierOrderData(supplierName, orderCount));
            }

            // Set the collected data as request attributes for use in the JSP
            request.setAttribute("totalSuppliers", totalSuppliers);
            request.setAttribute("totalOrders", totalOrders);
            request.setAttribute("orderStatusCount", orderStatusCount);
            request.setAttribute("supplierOrdersCount", supplierOrdersCount);

            // Forward to JSP to display the sales and reports data
            request.getRequestDispatcher("salesAndReports.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");  // Redirect to error page in case of failure
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper class to store supplier data (name and order count)
    public static class SupplierOrderData {
        private String supplierName;
        private int orderCount;

        public SupplierOrderData(String supplierName, int orderCount) {
            this.supplierName = supplierName;
            this.orderCount = orderCount;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public int getOrderCount() {
            return orderCount;
        }
    }
}
