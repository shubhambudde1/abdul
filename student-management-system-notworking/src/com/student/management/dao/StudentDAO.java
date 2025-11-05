package com.student.management.dao;

import com.student.management.model.Student;
import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(int studentId);
    void updateStudent(Student student);
    void deleteStudent(int studentId);
}
