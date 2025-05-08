package com.example.dao;

import com.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class UserRepository {
    Logger logger = Logger.getLogger(getClass().getName());

    public boolean updateAddress(int userId, String address) {
        String sql = "UPDATE users SET address = ? WHERE user_id = ?";
        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, userId);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUserProfile(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";
        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // if at least one row updated, return true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int registerUser(User user) {
        String sql = "insert into users (name, email, phone, password) values  (?, ?, ?, ?)";
        try (Connection conn = new JDBCConnection().connect();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            Optional<User> userOptional = getUserByEmail(user.getEmail());
            if(userOptional.isEmpty()) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPhone());
                preparedStatement.setString(4, user.getPassword());
                int rows = preparedStatement.executeUpdate();
                if(rows > 0) return 201;
                else return 400;
            }
            else
                return 409;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 500;
        }
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = new JDBCConnection().connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setPassword(resultSet.getString("password"));
                    user.setAddress(resultSet.getString("address"));

                    return Optional.of(user);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error while retrieving user by email: " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
