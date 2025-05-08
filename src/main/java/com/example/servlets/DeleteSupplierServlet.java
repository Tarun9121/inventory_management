package com.example.servlets;

import com.example.dao.JDBCConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/DeleteSupplierServlet")
public class DeleteSupplierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));

        try (Connection conn = new JDBCConnection().connect()) {
            // Attempt to delete the supplier
            String sql = "DELETE FROM suppliers WHERE supplier_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, supplierId);
            int rowsAffected = ps.executeUpdate();

            // Redirect after deletion
            response.sendRedirect("SupplierListServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
