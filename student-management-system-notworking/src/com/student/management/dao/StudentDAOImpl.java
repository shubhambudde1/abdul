package com.student.management.dao;

import com.student.management.model.Student;
import com.student.management.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (id, name, grade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGrade());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getString("grade"));
                students.add(student);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return students;
    }

    @Override
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setGrade(rs.getString("grade"));
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, grade = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGrade());
            pstmt.setInt(3, student.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void handleSQLException(SQLException e) {
        System.out.println("Database error: " + e.getMessage());
    }
}
