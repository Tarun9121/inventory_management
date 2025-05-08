package com.example.servlets;

import com.example.dao.ProductRepository;
import com.example.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String categoryIdParam = req.getParameter("categoryId");
        if (categoryIdParam != null) {
            int categoryId = Integer.parseInt(categoryIdParam);
            List<Product> productList = new ProductRepository().getAllProductsByCategoryId(categoryId);
            req.setAttribute("products", productList);
            req.getRequestDispatcher("categories_pages/dresses.jsp").forward(req, res);
        } else {
            res.sendRedirect("login.jsp");
        }
    }

}

