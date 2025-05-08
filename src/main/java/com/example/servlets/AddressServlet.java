package com.example.servlets;

import com.example.dao.UserRepository;
import com.example.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/address")
public class AddressServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Get address from form
        String address = request.getParameter("address");

        // 2. Get user from session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");

        // 3. Update address in database
        boolean updated = userRepository.updateAddress(user.getId(), address);

        if (updated) {
            // 4. Update address in session user
            user.setAddress(address);
            session.setAttribute("user", user);

            // 5. Redirect back to profile page
            response.sendRedirect(request.getContextPath() + "/profile.jsp");
        } else {
            // 6. Handle error (optional)
            response.getWriter().println("Failed to update address. Please try again.");
        }
    }
}
