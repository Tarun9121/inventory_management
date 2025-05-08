package com.example.servlets;

import com.example.dao.JDBCConnection;
import com.example.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/view_products")
public class ViewProductsServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ViewProductsServlet.class.getName());
    private Connection connection;

    @Override
    public void init() throws ServletException {
        JDBCConnection jdbc = new JDBCConnection();
        try {
            connection = jdbc.connect();
            LOGGER.info("Database connection established.");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Database connection failed in servlet init()", e);
            throw new RuntimeException("Database connection failed in servlet init()", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Entering doGet method.");

        try {
            Integer supplierId = (Integer) request.getSession().getAttribute("supplierId");

            if (supplierId == null) {
                LOGGER.warning("Supplier ID not found in session. Redirecting to login.");
                response.sendRedirect("login.jsp");
                return;
            }

            LOGGER.info("Supplier ID: " + supplierId);

            // Query to get products for the supplier
            String sql = "SELECT p.product_id, p.name, p.description, p.price, c.category_name AS category_name, p.quantity " +
                    "FROM products p " +
                    "JOIN categories c ON p.category_id = c.category_id " +
                    "WHERE p.supplier_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, supplierId);

            LOGGER.info("Executing query: " + sql);

            ResultSet rs = stmt.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setCategoryName(rs.getString("category_name"));
                product.setQuantity(rs.getInt("quantity"));

                productList.add(product);
            }

            LOGGER.info("Retrieved " + productList.size() + " products from the database.");

            if (productList.isEmpty()) {
                LOGGER.info("No products found for this supplier.");
            }

            request.setAttribute("productList", productList);
            request.getRequestDispatcher("view_products.jsp").forward(request, response);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQLException occurred while fetching products", e);
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error occurred", e);
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
                LOGGER.info("Database connection closed.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error closing database connection", e);
        }
    }
}
