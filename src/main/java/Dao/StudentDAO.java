package Dao;

import Models.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DB.DatabaseConnection;


public class StudentDAO {
    // Add Student
    public boolean addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getCourse());
            return stmt.executeUpdate() > 0;
        }
    }

    // Update Student
    public boolean updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    // Delete Student
    public boolean deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM students WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    // Get All Students
    public List<Student> getAllStudents() throws SQLException {
        String query = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course")
                ));
            }
        }
        return students;
    }

    // Get Student by ID
    public Student getStudentById(int id) throws SQLException {
        String query = "SELECT * FROM students WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("course")
                    );
                }
            }
        }
        return null;
    }
}

