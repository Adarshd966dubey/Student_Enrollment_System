package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3305/Studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "New@1234";

    // Method to establish and return a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

