package com.example.servlets;

import com.example.dao.CartRepository;
import com.example.dao.JDBCConnection;
import com.example.dto.ProductDetailDto;
import com.example.models.User;
import com.example.service.CartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private CartRepository cartRepository;
    private CartService cartService;

    @Override
    public void init() {
        Connection conn = null; // Adjust as needed
        try {
            conn = new JDBCConnection().connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        cartRepository = new CartRepository();
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int userId = user.getId();

            // Fetch Cart Products and Total Price
            List<ProductDetailDto> cartProducts = cartRepository.getAllProductsInCartByUserId(userId);
            double totalPrice = cartService.getTotalCostOfProducts(cartProducts);

            // Fetch User Address (Assuming you have a method for this)
            String address = user.getAddress();

            req.setAttribute("cartProducts", cartProducts);
            req.setAttribute("totalPrice", totalPrice);
            req.setAttribute("address", address);

            System.out.println("req: checkout page data fetch");
            System.out.println("user: " + user);
            System.out.println("cart products: " + cartProducts);
            System.out.println("total price: " + totalPrice);
            System.out.println("address: " + address);
            System.out.println();

            RequestDispatcher dispatcher = req.getRequestDispatcher("order_pages/checkout.jsp");
            dispatcher.forward(req, res);
        } else {
            res.sendRedirect("login.jsp");
        }
    }

}
