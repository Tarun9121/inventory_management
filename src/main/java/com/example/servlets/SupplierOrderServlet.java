//package com.example.servlet;
//
//import com.example.dao.OrderDAO;
//import com.example.models.Order;
//import com.example.dao.JDBCConnection;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.WebServlet;
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.List;
//
//@WebServlet("/SupplierOrdersServlet")
//public class SupplierOrdersServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        Integer supplierId = (Integer) session.getAttribute("supplierId");
//
//        if (supplierId == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        try (Connection conn = new JDBCConnection().connect()) {
//            OrderDAO orderDAO = new OrderDAO(conn);
//            List<Order> orders = orderDAO.getOrdersBySupplierId(supplierId);
//            request.setAttribute("orders", orders);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("supplier_pages/supplier_orders.jsp");
//            dispatcher.forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("error.jsp");
//        }
//    }
//}
