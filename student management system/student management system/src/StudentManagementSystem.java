import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private String grade;

    public Student(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Grade: " + grade;
    }
}

class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (getStudentById(student.getId()) == null) {
            students.add(student);
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Student with ID " + student.getId() + " already exists!");
        }
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system!");
            return;
        }
        System.out.println("\nList of Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(int id, String newName, String newGrade) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setName(newName);
            student.setGrade(newGrade);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Find Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number between 1-6");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addStudentHandler(scanner, manager);
                    break;
                case 2:
                    manager.viewAllStudents();
                    break;
                case 3:
                    findStudentHandler(scanner, manager);
                    break;
                case 4:
                    updateStudentHandler(scanner, manager);
                    break;
                case 5:
                    deleteStudentHandler(scanner, manager);
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter 1-6");
            }
        }
    }

    private static void addStudentHandler(Scanner scanner, StudentManager manager) {
        System.out.println("\nAdd New Student");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();

        manager.addStudent(new Student(id, name, grade));
    }

    private static void findStudentHandler(Scanner scanner, StudentManager manager) {
        System.out.print("\nEnter Student ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student student = manager.getStudentById(id);
        if (student != null) {
            System.out.println("Student found:\n" + student);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void updateStudentHandler(Scanner scanner, StudentManager manager) {
        System.out.print("\nEnter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student student = manager.getStudentById(id);

        if (student != null) {
            System.out.print("Enter new name (" + student.getName() + "): ");
            String newName = scanner.nextLine();
            System.out.print("Enter new grade (" + student.getGrade() + "): ");
            String newGrade = scanner.nextLine();
            manager.updateStudent(id, newName, newGrade);
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void deleteStudentHandler(Scanner scanner, StudentManager manager) {
        System.out.print("\nEnter Student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        manager.deleteStudent(id);
    }
}