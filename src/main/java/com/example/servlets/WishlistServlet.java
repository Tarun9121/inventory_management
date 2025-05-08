package com.example.servlets;

import com.example.dao.JDBCConnection;
import com.example.dao.WishlistDAO;
import com.example.models.Product;
import com.example.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Wishlist")
public class WishlistServlet extends HttpServlet {

    private WishlistDAO wishlistDAO;

    @Override
    public void init() throws ServletException {
        try {
            wishlistDAO = new WishlistDAO(new JDBCConnection().connect());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // "add" or "remove"
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            if ("remove".equals(action)) {
                wishlistDAO.removeFromWishlist(userId, productId);
                session.setAttribute("wishlistMessage", "Removed from Wishlist.");
            } else {
                wishlistDAO.addToWishlist(userId, productId);
                session.setAttribute("wishlistMessage", "Added to Wishlist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("wishlistMessage", "Error processing Wishlist.");
        }

        response.sendRedirect("ProductDetailServlet?productId=" + productId); // Redirect back to product detail page
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        List<Product> wishlistItems = wishlistDAO.getWishlistItems(user.getId());

        request.setAttribute("wishlistItems", wishlistItems);
        request.getRequestDispatcher("wishlist.jsp").forward(request, response);
    }
}
