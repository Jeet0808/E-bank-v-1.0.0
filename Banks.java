package ebank;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Banks {
    String nameofbank;
    String Curancyofbank;
    int funds;
    String nameofowner;
    int specialbno;
    String manager;

    Conn conn;

    public Banks(String nameofbank, String curancyofbank, int funds, String nameofowner, int specialbno ,String manager) {
        this.nameofbank = nameofbank;
        this.Curancyofbank = curancyofbank;
        this.funds = funds;
        this.nameofowner = nameofowner;
        this.specialbno = specialbno;
        this.conn = new Conn();  // Ensure the connection instance is initialized
        this.manager = manager;

        insertbanksToDB();
    }

    public void bankinfo() {
        System.out.println("Bank name: " + nameofbank);
        System.out.println("Currency of bank: " + Curancyofbank);
        System.out.println("Funds in bank: " + funds);
        System.out.println("Name of bank owner: " + nameofowner);
        System.out.println("Name of bank manager: " + manager);
    }

    // Method to insert bank details into the database
    public void insertbanksToDB() {
        try {
            // Ensure the connection is not null
            if (this.conn.c != null) {
                String sql = "INSERT INTO banks (nameofbank, Curancyofbank, funds, nameofowner, specialbno) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = this.conn.c.prepareStatement(sql);

                // Correct data types for each placeholder
                pstmt.setString(1, this.nameofbank);        // Name of the bank (String)
                pstmt.setString(2, this.Curancyofbank);     // Currency of the bank (String)
                pstmt.setInt(3, this.funds);                // Funds (int)
                pstmt.setString(4, this.nameofowner);       // Name of the owner (String)
                pstmt.setInt(5, this.specialbno);           // Special number (int)

                pstmt.executeUpdate();
                System.out.println("Bank inserted successfully!");
            } else {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
