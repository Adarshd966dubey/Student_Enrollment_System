package Dao;

import Models.Course;
import DB.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // Add a new course to the database
    public boolean addCourse(Course course) throws SQLException {
        String query = "INSERT INTO courses (courseId, name, instructor, credits) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
        	stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getName());
            stmt.setString(3, course.getInstructor());
            stmt.setInt(4, course.getCredits());
            return stmt.executeUpdate() > 0; // Return true if the course is added successfully
        }
    }

    // Update an existing course in the database
    public boolean updateCourse(Course course) throws SQLException {
        String query = "UPDATE courses SET name=?, instructor=?, credits=? WHERE courseId=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getInstructor());
            stmt.setInt(3, course.getCredits());
            stmt.setInt(4, course.getCourseId());
            return stmt.executeUpdate() > 0; // Return true if the course is updated successfully
        }
    }

    // Delete a course from the database
    public boolean deleteCourse(int courseId) throws SQLException {
        String query = "DELETE FROM courses WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            return stmt.executeUpdate() > 0; // Return true if the course is deleted successfully
        }
    }

    // Retrieve a course by its ID
    public Course getCourseById(int courseId) throws SQLException {
        String query = "SELECT * FROM courses WHERE courseId=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                            rs.getInt("courseId"),
                            rs.getString("name"),
                            rs.getString("instructor"),
                            rs.getInt("credits")
                    );
                }
            }
        }
        return null; // Return null if the course is not found
    }

    // Retrieve all courses from the database
    public List<Course> getAllCourses() throws SQLException {
        String query = "SELECT * FROM courses";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("courseId"),
                        rs.getString("name"),
                        rs.getString("instructor"),
                        rs.getInt("credits")
                ));
            }
        }
        return courses;
    }
}


