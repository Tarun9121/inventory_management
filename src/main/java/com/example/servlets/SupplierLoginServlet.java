package com.example.servlets;

import com.example.models.Supplier;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SupplierLoginServlet")
public class SupplierLoginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_management";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Received login request:");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password); // Not recommended in real apps

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC driver loaded.");

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Database connected: " + DB_URL);

            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM supplier WHERE email = ? AND password = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, password);  // Use hashed passwords in production

            System.out.println("Executing query with email=" + email + " and password=" + password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone")
                );

                System.out.println("Supplier found: ");
                System.out.println("ID: " + supplier.getId());
                System.out.println("Name: " + supplier.getName());
                System.out.println("Email: " + supplier.getEmail());
                System.out.println("Phone: " + supplier.getPhone());

                HttpSession session = request.getSession();
                session.setAttribute("supplier", supplier);
                session.setAttribute("supplierId", supplier.getId());

                System.out.println("Session attributes set. Redirecting to supplier-welcome.jsp");
                response.sendRedirect("supplier-welcome.jsp");
            } else {
                System.out.println("No matching supplier found.");
                response.sendRedirect("error.jsp");
            }

            conn.close();
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            System.out.println("Exception occurred during login:");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
