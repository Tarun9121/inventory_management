package com.example.dao;

import com.example.models.SupplierOrder;

import java.sql.*;
import java.util.*;

public class SupplierOrderDAO {
    private final Connection conn;

    public SupplierOrderDAO(Connection conn) {
        this.conn = conn;
    }

    public List<SupplierOrder> getOrdersBySupplierId(int supplierId) throws SQLException {
        List<SupplierOrder> list = new ArrayList<>();

        String sql = "SELECT o.order_id AS order_id, o.user_id, o.order_date, o.total_amount, o.status, " +
                "oi.variant_id, oi.quantity, oi.price, oi.product_id, p.name AS product_name " +
                "FROM orders o " +
                "JOIN order_items oi ON o.order_id = oi.order_id " +
                "JOIN products p ON oi.product_id = p.product_id " +
                "WHERE p.supplier_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SupplierOrder o = new SupplierOrder();
                o.setOrderId(rs.getInt("order_id"));
                o.setUserId(rs.getInt("user_id"));
                o.setOrderDate(rs.getTimestamp("order_date"));
                o.setTotalAmount(rs.getDouble("total_amount"));
                o.setStatus(rs.getString("status"));
                o.setVariantId(rs.getInt("variant_id"));
                o.setQuantity(rs.getInt("quantity"));
                o.setPrice(rs.getDouble("price"));
                o.setProductId(rs.getInt("product_id"));
                o.setProductName(rs.getString("product_name"));

                list.add(o);
            }
        }

        return list;
    }
}
