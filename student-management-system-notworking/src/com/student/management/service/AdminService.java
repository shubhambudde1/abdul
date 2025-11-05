package com.student.management.service;

import com.student.management.dao.AdminDAO;
import com.student.management.dao.AdminDAOImpl;

public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(AdminDAOImpl adminDAO) {
        this.adminDAO = adminDAO;
    }

    public boolean authenticate(String username, String password) {
        return adminDAO.validateAdmin(username, password);
    }
}