package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public Connection connect() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/inventory_management";
        String username = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(url, username, password);
    }
}
