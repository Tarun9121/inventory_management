package com.example.servlets;

import com.example.models.Category;
import com.example.dao.JDBCConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CategoryListServlet")
public class CategoryListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = new ArrayList<>();

        try (Connection conn = new JDBCConnection().connect()) {
            String sql = "SELECT category_id, category_name FROM categories";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("category_id"));
                    category.setName(rs.getString("category_name"));
                    categories.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/admin_pages/manage_products.jsp").forward(request, response);
    }
}
