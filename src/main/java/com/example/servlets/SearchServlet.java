package com.example.servlets;

import com.example.dao.ProductDAO;
import com.example.models.Product;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("query");
        if (keyword == null || keyword.trim().isEmpty()) {
            response.sendRedirect("home.jsp");
            return;
        }

        ProductDAO dao = new ProductDAO();
        List<Product> productList = dao.searchProducts(keyword);
        request.setAttribute("products", productList);
        request.setAttribute("keyword", keyword);

        RequestDispatcher dispatcher = request.getRequestDispatcher("searchResults.jsp");
        dispatcher.forward(request, response);
    }
}
