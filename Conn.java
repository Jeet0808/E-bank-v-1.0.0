package ebank;

import java.sql.*;

public class Conn {
    Connection c;
    private Statement s;

    public Conn() {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Make sure the driver is loaded

            // Establish the connection to the database
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/storemydata", // Corrected URL format for MySQL
                    "root", "jeet2006"
            );

            // Create a statement object to execute SQL queries
            s = c.createStatement();

            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Class Not Found");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Add methods to close the connection if necessary
    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
