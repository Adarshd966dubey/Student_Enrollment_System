package Dao;

import Models.Enrollment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DB.DatabaseConnection;


public class EnrollmentDAO {
    // Enroll Student in a Course
    public boolean enrollStudentInCourse(int studentId, int courseId) throws SQLException {
        String query = "INSERT INTO enrollment_course (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            return stmt.executeUpdate() > 0;
        }
    }

    // Get All Enrollments
    public List<Enrollment> getAllEnrollments() throws SQLException {
        String query = "SELECT * FROM enrollment_course";
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id")
                ));
            }
        }
        return enrollments;
    }
}
