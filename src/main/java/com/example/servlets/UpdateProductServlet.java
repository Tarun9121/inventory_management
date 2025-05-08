package com.example.servlets;

import com.example.dao.JDBCConnection;
import com.example.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
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
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            String imageUrl = request.getParameter("imageUrl");

            // Update product in the database
            String updateSQL = "UPDATE products SET name = ?, description = ?, price = ?, quantity = ?, category_id = ?, image_url = ? WHERE product_id = ?";
            PreparedStatement ps = connection.prepareStatement(updateSQL);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setInt(4, quantity);
            ps.setInt(5, categoryId);
            ps.setString(6, imageUrl);
            ps.setInt(7, productId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("view_products.jsp?success=1");
            } else {
                response.sendRedirect("view_products.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("view_products.jsp?error=1");
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
