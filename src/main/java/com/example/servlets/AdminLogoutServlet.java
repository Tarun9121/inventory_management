package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // false = don't create new session if doesn't exist
        if (session != null) {
            session.invalidate(); // kill the session
        }
        response.sendRedirect("admin_pages/login.jsp"); // redirect to login page
    }
}
