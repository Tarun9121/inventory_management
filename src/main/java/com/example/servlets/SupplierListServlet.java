package com.example.servlets;

import com.example.dao.JDBCConnection;
import com.example.models.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SupplierListServlet")
public class SupplierListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM supplier");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Supplier s = new Supplier();
                s.setId(rs.getInt("id"));  // Make sure this matches your DB column
                s.setName(rs.getString("name"));
                s.setEmail(rs.getString("email"));
                suppliers.add(s);
            }

            request.setAttribute("supplierList", suppliers);
            request.getRequestDispatcher("supplier_list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
