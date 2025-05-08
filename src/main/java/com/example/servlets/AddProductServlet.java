package com.example.servlets;

import com.example.dao.JDBCConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        JDBCConnection jdbc = new JDBCConnection();
        try {
            connection = jdbc.connect();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database connection failed in servlet init()", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String imageUrl = request.getParameter("imageUrl");
            int quantity = Integer.parseInt(request.getParameter("quantity")); // New quantity field

            // Insert into products table including quantity
            String insertProductSQL = "INSERT INTO products (name, description, price, category_id, image_url, supplier_id, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psProduct = connection.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS);
            psProduct.setString(1, name);
            psProduct.setString(2, description);
            psProduct.setDouble(3, price);
            psProduct.setInt(4, categoryId);
            psProduct.setString(5, imageUrl);
            psProduct.setInt(6, supplierId);
            psProduct.setInt(7, quantity);  // Insert quantity into the database

            int rows = psProduct.executeUpdate();

            if (rows > 0) {
                // If product was successfully added, redirect with success message
                response.sendRedirect("admin_pages/manage_products.jsp?success=1");
            } else {
                // If something went wrong, redirect with error message
                response.sendRedirect("admin_pages/manage_products.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin_pages/manage_products.jsp?error=1");
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
