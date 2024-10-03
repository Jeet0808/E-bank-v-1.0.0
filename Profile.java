package ebank;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Profile {
    String name;
    String Email;
    String nickname;
    String links;
    // Create a connection object to interact with the database
    Conn conn;

    public Profile(String name, String email, String nickname, String links) {
        this.name = name;
        this.Email = email;
        this.nickname = nickname;
        this.links = links;

        this.conn = new Conn();

        // Insert account details into the database when a profile is created
        insertProfileToDB();
    }

    public void printpro() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + Email);
        System.out.println("Nickname: " + nickname);
        System.out.println("Links: " + links);
    }

    // Method to insert profile details into the database
    public void insertProfileToDB() {
        try {
            // Ensure the connection is not null
            if (this.conn.c != null) {
                String sql = "INSERT INTO profiles (name, Email, nickname, links) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = this.conn.c.prepareStatement(sql);

                // Use setString for all string parameters
                pstmt.setString(1, this.name);
                pstmt.setString(2, this.Email);
                pstmt.setString(3, this.nickname);
                pstmt.setString(4, this.links);

                pstmt.executeUpdate();
                System.out.println("Profile inserted successfully!");
            } else {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
