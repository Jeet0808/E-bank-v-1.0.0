package ebank;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
    long phono; // Phone number field
    int otp;    // OTP field

    // Create a connection object to interact with the database
    Conn conn;

    // Parameterized constructor
    public Register(long phono, int otp) {
        this.phono = phono;
        this.otp = otp;

        this.conn = new Conn();

        // Insert phone details into the database when an account is created
        insertPhonenoToDB();
    }

    public void getStatus() {
        System.out.println("Phone number: " + phono);
        System.out.println("OTP: " + otp);
    }


    // Method to insert phone number and OTP into the database
    public void insertPhonenoToDB() {
        try {
            // Ensure the connection is not null
            if (this.conn.c != null) {
                // Update the SQL query to use 'mobileno' instead of 'phono'
                String sql = "INSERT INTO registors (mobileno, otp) VALUES (?, ?)";

                PreparedStatement pstmt = this.conn.c.prepareStatement(sql);

                // Use setLong for the phone number and setInt for the OTP
                pstmt.setString(1, String.valueOf(this.phono));
                pstmt.setInt(2, this.otp);

                pstmt.executeUpdate();
                System.out.println("Phone number and OTP inserted successfully!");
            } else {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
