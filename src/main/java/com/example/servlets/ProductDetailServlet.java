package com.example.servlets;

import com.example.dao.ProductRepository;
import com.example.dto.ProductDetailDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String productIdParam = req.getParameter("productId");
        if(productIdParam != null) {
            ProductDetailDto productDetailDto = new ProductRepository().getProductWithDetail(Integer.parseInt(productIdParam));
            req.setAttribute("product", productDetailDto);
            req.getRequestDispatcher("categories_pages/product-detail.jsp").forward(req, res);
        }
        else {
            res.sendRedirect("login.jsp");
        }
    }
}
