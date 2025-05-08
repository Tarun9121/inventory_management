package com.example.servlets;

import com.example.dao.UserRepository;
import com.example.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session and current user
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User currentUser = (User) session.getAttribute("user");

        // Get updated form values
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Update current user object
        currentUser.setName(name);
        currentUser.setEmail(email);
        currentUser.setPhone(phone);
        currentUser.setAddress(address);

        // Update user in DB
        UserRepository userDAO = new UserRepository();
        boolean success = userDAO.updateUserProfile(currentUser);

        if (success) {
            // Update session user
            session.setAttribute("user", currentUser);
            // Redirect back to profile page
            response.sendRedirect("profile.jsp");
        } else {
            // Update failed — show error (you can improve this later)
            response.getWriter().println("Failed to update profile.");
        }
    }
}
