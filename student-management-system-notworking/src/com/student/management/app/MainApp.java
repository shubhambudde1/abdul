package com.student.management.app;

import com.student.management.dao.AdminDAOImpl;
import com.student.management.dao.StudentDAOImpl;
import com.student.management.dao.AdminDAO;
import com.student.management.dao.StudentDAO;
import com.student.management.service.*;
import com.student.management.model.Student;

import java.util.Scanner;
import java.util.List;

public class MainApp {
    private final Scanner scanner;
    private final StudentService studentService;
    private final AdminService adminService;

    public MainApp() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService(new StudentDAOImpl());
        this.adminService = new AdminService(new AdminDAOImpl());
    }

    public void start() {
        if(login()) {
            showMainMenu();
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }

    private boolean login() {
        System.out.println("=== Admin Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        return adminService.authenticate(username, password);
    }

    private void showMainMenu() {
        while(true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch(choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addStudent() {
        System.out.println("\n=== Add New Student ===");
        com.student.management.model.Student student = new com.student.management.model.Student();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        student.setName(name);

        System.out.print("Enter Student Grade: ");
        String grade = scanner.nextLine();
        student.setGrade(grade);

        studentService.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private void viewAllStudents() {
        System.out.println("\n=== View All Students ===");
        List<com.student.management.model.Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            for (com.student.management.model.Student student : students) {
                System.out.println(student);
            }
        }
    }

    private void updateStudent() {
        System.out.println("\n=== Update Student ===");
        System.out.print("Enter Student ID to update: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        com.student.management.model.Student existingStudent = studentService.getStudentById(studentId);

        if (existingStudent == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter New Student Name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingStudent.setName(name);
        }

        System.out.print("Enter New Student Grade (leave blank to keep current): ");
        String grade = scanner.nextLine();
        if (!grade.isEmpty()) {
            existingStudent.setGrade(grade);
        }

        studentService.updateStudent(existingStudent);
        System.out.println("Student updated successfully!");
    }

    private void deleteStudent() {
        System.out.println("\n=== Delete Student ===");
        System.out.print("Enter Student ID to delete: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        studentService.deleteStudent(studentId);
        System.out.println("Student deleted successfully!");
    }
    public static void main(String[] args) {
        new MainApp().start();
    }

}
