package StudentManagementSystem.jdbc.StudentManagementSystem;

import Dao.CourseDAO;
import Dao.StudentDAO;
import Dao.EnrollmentDAO;
import Models.Course;
import Models.Student;
import Models.Enrollment;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static StudentDAO studentDAO = new StudentDAO();
    private static CourseDAO courseDAO = new CourseDAO();
    private static EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Enrollment System Management ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. View Enrollments");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1: studentManagementMenu(); break;
                    case 2: courseManagementMenu(); break;
                    case 3: enrollStudentInCourse(); break;
                    case 4: viewEnrollments(); break;
                    case 5: {
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    }
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 1. Student Management Menu
    private static void studentManagementMenu() throws SQLException {
        int choice;
        do {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. View Students");
            System.out.println("4. Update Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: removeStudent(); break;
                case 3: viewStudents(); break;
                case 4: updateStudent(); break;
                case 5: System.out.println("Exiting student management..."); break;
                default: System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);
    }

    private static void addStudent() throws SQLException {
        System.out.println("\nEnter Student Details:");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        Student student = new Student(0, name, age, course); // ID is auto-generated
        if (studentDAO.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student.");
        }
    }

    private static void removeStudent() throws SQLException {
        System.out.print("\nEnter the ID of the student to remove: ");
        int id = scanner.nextInt();

        if (studentDAO.deleteStudent(id)) {
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    private static void viewStudents() throws SQLException {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
        } else {
            System.out.println("\nList of Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void updateStudent() throws SQLException {
        System.out.print("\nEnter the ID of the student to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = studentDAO.getStudentById(id);
        if (student == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        System.out.print("Enter new Name (current: " + student.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) student.setName(name);

        System.out.print("Enter new Age (current: " + student.getAge() + "): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) student.setAge(Integer.parseInt(ageInput));

        System.out.print("Enter new Course (current: " + student.getCourse() + "): ");
        String course = scanner.nextLine();
        if (!course.isEmpty()) student.setCourse(course);

        if (studentDAO.updateStudent(student)) {
            System.out.println("Student details updated successfully!");
        } else {
            System.out.println("Failed to update student.");
        }
    }

    // 2. Course Management Menu
    private static void courseManagementMenu() throws SQLException {
        System.out.println("\n--- Course Management Menu ---");
        System.out.println("1. Add Course");
        System.out.println("2. Update Course");
        System.out.println("3. Delete Course");
        System.out.println("4. View All Courses");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: addCourse(); break;
            case 2: updateCourse(); break;
            case 3: deleteCourse(); break;
            case 4: viewAllCourses(); break;
            default: System.out.println("Invalid choice. Try again.");
        }
    }

    private static void addCourse() throws SQLException {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = scanner.nextInt();

        Course course = new Course(0, courseName, instructor, credits); // ID is auto-generated
        if (courseDAO.addCourse(course)) {
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Failed to add course.");
        }
    }

    private static void updateCourse() throws SQLException {
        System.out.print("Enter course ID to update: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Course course = courseDAO.getCourseById(courseId);
        if (course == null) {
            System.out.println("No course found with ID: " + courseId);
            return;
        }

        System.out.print("Enter new course name (current: " + course.getName() + "): ");
        String courseName = scanner.nextLine();
        if (!courseName.isEmpty()) course.setName(courseName);

        System.out.print("Enter new instructor name (current: " + course.getInstructor() + "): ");
        String instructor = scanner.nextLine();
        if (!instructor.isEmpty()) course.setInstructor(instructor);

        System.out.print("Enter new credits (current: " + course.getCredits() + "): ");
        String creditsInput = scanner.nextLine();
        if (!creditsInput.isEmpty()) course.setCredits(Integer.parseInt(creditsInput));

        if (courseDAO.updateCourse(course)) {
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Failed to update course.");
        }
    }

    private static void deleteCourse() throws SQLException {
        System.out.print("Enter course ID to delete: ");
        int courseId = scanner.nextInt();

        if (courseDAO.deleteCourse(courseId)) {
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("No course found with ID: " + courseId);
        }
    }

    private static void viewAllCourses() throws SQLException {
        List<Course> courses = courseDAO.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("\nNo courses available.");
        } else {
            System.out.println("\nList of Courses:");
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }

    // 3. Enroll Student in a Course
    private static void enrollStudentInCourse() throws SQLException {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();

        if (enrollmentDAO.enrollStudentInCourse(studentId, courseId)) {
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("Failed to enroll student.");
        }
    }

    // 4. View Enrollments
	private static void viewEnrollments() throws SQLException {
        List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("\nNo enrollments available.");
        } else {
            System.out.println("\nList of Enrollments:");
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }
        }
    }
    
}



