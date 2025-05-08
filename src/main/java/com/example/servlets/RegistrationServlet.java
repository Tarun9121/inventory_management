package com.example.servlets;

import com.example.dao.UserRepository;
import com.example.models.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegistrationServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() {
        userRepository = new UserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        User user = new User(name, email, phone, password);

        int status = new UserRepository().registerUser(user);

        if (status == 201) {
            resp.sendRedirect("login.jsp");
        } else {
            String errorMessage = "Registration failed. Please try again.";

            if (status == 409) {
                errorMessage = "Email is already registered.";
            } else if (status == 400) {
                errorMessage = "Invalid registration details.";
            } else if (status == 500) {
                errorMessage = "Server error, please try again later.";
            }

            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

}
