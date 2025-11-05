package com.student.management.dao;

import com.student.management.model.Admin;
import com.student.management.util.DatabaseUtil;
import java.sql.*;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public boolean validateAdmin(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    private void handleSQLException(SQLException e) {
        System.out.println("Database error: " + e.getMessage());
    }
}
