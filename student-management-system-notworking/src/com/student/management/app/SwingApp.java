package com.student.management.app;

import com.student.management.dao.AdminDAOImpl;
import com.student.management.dao.StudentDAOImpl;
import com.student.management.model.Student;
import com.student.management.service.AdminService;
import com.student.management.service.StudentService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingApp extends JFrame {
    private final StudentService studentService;
    private JTable studentTable;
    private JTextField searchField;
    private JPanel mainPanel; // Declare mainPanel as a class member

    public SwingApp() {
        this.studentService = new StudentService(new StudentDAOImpl());
        initializeUI();
        showLoginDialog();
    }

    private void showLoginDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Admin Login",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!new AdminService(new AdminDAOImpl()).authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
                System.exit(0);
            } else {
                refreshStudentTable();
            }
        } else {
            System.exit(0);
        }
    }

    private void showUpdateStudentDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField gradeField = new JTextField();

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);
        panel.add(new JLabel("New Name:"));
        panel.add(nameField);
        panel.add(new JLabel("New Grade:"));
        panel.add(gradeField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Update Student",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                Student updatedStudent = new Student(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        gradeField.getText()
                );
                studentService.updateStudent(updatedStudent);
                refreshStudentTable();
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void showDeleteStudentDialog() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JTextField idField = new JTextField();
        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Delete Student",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                studentService.deleteStudent(id);
                refreshStudentTable();
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }


    private void initializeUI() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu operationsMenu = new JMenu("Operations");

        JMenuItem addItem = new JMenuItem("Add Student");
        JMenuItem viewAllItem = new JMenuItem("View All Students");
        JMenuItem viewByIdItem = new JMenuItem("View Student by ID");
        JMenuItem updateItem = new JMenuItem("Update Student");
        JMenuItem deleteItem = new JMenuItem("Delete Student");
        JMenuItem refreshItem = new JMenuItem("Refresh");

        addItem.addActionListener(e -> showAddStudentDialog());
        viewAllItem.addActionListener(e -> refreshStudentTable());
        viewByIdItem.addActionListener(e -> showViewStudentByIdDialog());
        updateItem.addActionListener(e -> showUpdateStudentDialog());
        deleteItem.addActionListener(e -> showDeleteStudentDialog());
        refreshItem.addActionListener(e -> refreshStudentTable());

        operationsMenu.add(addItem);
        operationsMenu.add(viewAllItem);
        operationsMenu.add(viewByIdItem);
        operationsMenu.add(updateItem);
        operationsMenu.add(deleteItem);
        operationsMenu.add(refreshItem);
        menuBar.add(operationsMenu);
        setJMenuBar(menuBar);

        // Main Content
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchStudent());
        searchPanel.add(new JLabel("Search by ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> showAddStudentDialog());
        searchPanel.add(addButton);

        // Table
        studentTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(studentTable);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        refreshStudentTable();
    }

    private void showAddStudentDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField gradeField = new JTextField();

        panel.add(new JLabel("Student ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add New Student",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                Student student = new Student(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        gradeField.getText()
                );
                studentService.addStudent(student);
                refreshStudentTable();
                JOptionPane.showMessageDialog(this, "Student added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void refreshStudentTable() {
        List<Student> students = studentService.getAllStudents();
        String[] columnNames = {"ID", "Name", "Grade"};
        Object[][] data = new Object[students.size()][3];

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getName();
            data[i][2] = s.getGrade();
        }

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
                data, columnNames
        ));
    }

    private void searchStudent() {
        try {
            int id = Integer.parseInt(searchField.getText());
            Student student = studentService.getStudentById(id);

            if (student != null) {
                JOptionPane.showMessageDialog(this,
                        "Student Found:\nID: " + student.getId() +
                                "\nName: " + student.getName() +
                                "\nGrade: " + student.getGrade()
                );
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID format!");
        }
    }


    private void showViewStudentByIdDialog() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JTextField idField = new JTextField();
        panel.add(new JLabel("Student ID:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "View Student by ID",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                Student student = studentService.getStudentById(id);

                if (student != null) {
                    JOptionPane.showMessageDialog(this,
                            "Student Details:\nID: " + student.getId() +
                                    "\nName: " + student.getName() +
                                    "\nGrade: " + student.getGrade()
                    );
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SwingApp().setVisible(true);
        });
    }
}
