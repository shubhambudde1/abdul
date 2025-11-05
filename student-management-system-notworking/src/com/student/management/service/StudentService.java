package com.student.management.service;

import com.student.management.dao.StudentDAO;
import com.student.management.model.Student;
import java.util.List;

public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
    }
}
