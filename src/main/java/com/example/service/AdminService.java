package com.example.service;

import com.example.dao.AdminRepository;
import com.example.models.Admin;
import java.sql.Connection;

public class AdminService {

    private AdminRepository adminRepository;

    public AdminService(Connection connection) {
        this.adminRepository = new AdminRepository(connection);
    }

    public Admin loginAdmin(String email, String password) {
        return adminRepository.login(email, password);
    }
}
