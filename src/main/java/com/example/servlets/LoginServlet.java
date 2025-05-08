package com.example.servlets;

import com.example.dao.UserRepository;
import com.example.models.User;
import com.example.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;
    private UserRepository userRepository;

    @Override
    public void init() {
        userService = new UserService();
        userRepository = new UserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        int status = userService.checkCrediantals(email, password);

        if (status == 200) {
            HttpSession session = req.getSession();
            User user = userRepository.getUserByEmail(email).get();
            user.setPassword(null);
            session.setAttribute("user", user);

            resp.sendRedirect("home.jsp");
        } else {
            // Set error message
            String errorMessage = (status == 401)
                    ? "Incorrect password. Please try again."
                    : "No account found with this email.";

            req.setAttribute("errorMessage", errorMessage);

            // Forward back to login page with error message
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }
    }

}
