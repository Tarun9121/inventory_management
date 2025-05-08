package com.example.dao;

import com.example.models.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminRepository {

    private Connection connection;

    public AdminRepository(Connection connection) {
        this.connection = connection;
    }

    public Admin login(String email, String password) {
        Admin admin = null;
        try {
            String sql = "SELECT * FROM admins WHERE email = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPhone(rs.getString("phone"));
                admin.setAddress(rs.getString("address"));
                // (we don't usually set password back for security)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}

