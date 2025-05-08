package com.example.servlets;

import com.example.dao.JDBCConnection;
import com.example.models.Admin;
import com.example.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection connection = new JDBCConnection().connect();
            AdminService adminService = new AdminService(connection);
            Admin admin = adminService.loginAdmin(email, password);

            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("adminObj", admin);
                response.sendRedirect("admin_pages/admin_dashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid email or password");
                request.getRequestDispatcher("admin_pages/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Something went wrong, please try again later.");
            request.getRequestDispatcher("admin_pages/login.jsp").forward(request, response);
        }
    }
}

