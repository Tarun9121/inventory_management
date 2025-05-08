package com.example.servlets;

import com.example.dao.SupplierOrderDAO;
import com.example.models.SupplierOrder;
import com.example.dao.JDBCConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/SupplierOrdersServlet")
public class SupplierOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Integer supplierId = (Integer) session.getAttribute("supplierId");

        if (supplierId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = new JDBCConnection().connect()) {
            SupplierOrderDAO dao = new SupplierOrderDAO(conn);
            List<SupplierOrder> orders = dao.getOrdersBySupplierId(supplierId);
            request.setAttribute("orders", orders);
            RequestDispatcher rd = request.getRequestDispatcher("supplier_orders.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
