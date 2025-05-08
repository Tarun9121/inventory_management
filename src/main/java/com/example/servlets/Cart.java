package com.example.servlets;

import com.example.dao.CartRepository;
import com.example.dao.JDBCConnection;
import com.example.dto.ProductDetailDto;
import com.example.models.User;
import com.example.service.CartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
    private CartRepository cartRepository;
    private CartService cartService;

    @Override
    public void init() {
        try {
            Connection conn = new JDBCConnection().connect(); // If needed elsewhere
            cartRepository = new CartRepository();
            cartService = new CartService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize CartServlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String method = req.getParameter("_method");
        String referer = req.getHeader("Referer");

        HttpSession session = req.getSession(false);

        if ("PUT".equalsIgnoreCase(method)) {
            doPut(req, res);
        } else if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(req, res);
        } else {
            int productId = Integer.parseInt(req.getParameter("productId"));
            if (session != null && session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                int userId = user.getId();

                boolean success = cartRepository.addProductToCart(userId, productId);
                session.setAttribute("cartMessage", success ? "Item added to cart successfully!" : "Failed to add item to cart. Try again!");
            } else {
                session.setAttribute("cartMessage", "You must be logged in to add to cart.");
            }
            res.sendRedirect(referer != null ? referer : "Cart");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int userId = user.getId();

            List<ProductDetailDto> cartProducts = cartRepository.getAllProductsInCartByUserId(userId);
            double totalPrice = cartService.getTotalCostOfProducts(cartProducts);

            req.setAttribute("cartProducts", cartProducts);
            req.setAttribute("totalPrice", totalPrice);

            RequestDispatcher dispatcher = req.getRequestDispatcher("categories_pages/cart.jsp");
            dispatcher.forward(req, res);
        } else {
            res.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String action = req.getParameter("action"); // "increase" or "decrease"
        int variantId = Integer.parseInt(req.getParameter("productId"));
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int userId = user.getId();

            boolean success = false;
            if ("increase".equalsIgnoreCase(action)) {
                success = cartRepository.increaseQuantity(userId, variantId);
                session.setAttribute("cartMessage", success ? "Item quantity increased!" : "Failed to increase quantity.");
            } else if ("decrease".equalsIgnoreCase(action)) {
                success = cartRepository.decreaseQuantity(userId, variantId);
                session.setAttribute("cartMessage", success ? "Item quantity decreased!" : "Failed to decrease quantity.");
            } else {
                session.setAttribute("cartMessage", "Invalid cart action.");
            }
        } else {
            session.setAttribute("cartMessage", "You must be logged in to update the cart.");
        }

        res.sendRedirect("Cart");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int variantId = Integer.parseInt(req.getParameter("productId"));
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int userId = user.getId();

            boolean success = cartRepository.removeProductFromCart(userId, variantId);
            session.setAttribute("cartMessage", success ? "Item removed from cart!" : "Failed to remove item from cart.");
        } else {
            session.setAttribute("cartMessage", "You must be logged in to update cart.");
        }

        res.sendRedirect("Cart");
    }
}
