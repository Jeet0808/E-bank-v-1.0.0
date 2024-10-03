package ebank;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class NewEmploye {
    String name;
    String email;
    String password;
    String regNo;
    private int salary;
    private Account account;

    private ArrayList<SalaryTransaction> salaryTransactions = new ArrayList<>();
    private Timer salaryTimer;
    Conn conn;

    public NewEmploye(String name, String email, String password, String regNo, int salary, Account account) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.regNo = regNo;
        this.salary = salary;
        this.account = account;
        this.conn = new Conn();



        startSalaryIncrement();
        insertempintodb();
    }

    private void startSalaryIncrement() {
        salaryTimer = new Timer();
        salaryTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                increaseSalary();
            }
        }, 0, 10000);
    }

    private void increaseSalary() {
        salary += 100;
        account.credit(100);
        addSalaryTransaction(100);
    }

    public void stopSalaryIncrement() {
        if (salaryTimer != null) {
            salaryTimer.cancel();
        }
    }

    public void printEmpInfo() {
        System.out.println("Your name is: " + name);
        System.out.println("Your email is: " + email);
        System.out.println("Your registered ID is: " + regNo);
    }

    public void printSalaryDetails() {
        System.out.println("Your salary is: " + salary);
    }

    public void printSalaryTransactions() {
        System.out.println("Salary Transactions for Registration Number: " + regNo);
        for (SalaryTransaction transaction : salaryTransactions) {
            System.out.println("Date: " + transaction.dateTime + " -- Salary added: " + transaction.amount);
        }
    }

    public void addSalaryTransaction(int amount) {
        SalaryTransaction transaction = new SalaryTransaction(amount, LocalDateTime.now());
        salaryTransactions.add(transaction);
    }

    public class SalaryTransaction {
        private int amount;
        private LocalDateTime dateTime;

        public SalaryTransaction(int amount, LocalDateTime dateTime) {
            this.amount = amount;
            this.dateTime = dateTime;
        }

        @Override
        public String toString() {
            return "Amount: " + amount + ", Date: " + dateTime;
        }
    }

    // Method to insert account details into the database
    public void insertempintodb() {
        try {
            // Ensure the connection is not null
            if (this.conn.c != null) {
                String sql = "INSERT INTO employe (name, email, password, regNo,salary) VALUES (?, ?, ?, ?,?)";
                PreparedStatement pstmt = this.conn.c.prepareStatement(sql);
                pstmt.setString(1, this.name);
                pstmt.setString(2, this.email);
                pstmt.setString(3, this.password);
                pstmt.setString(4, this.regNo);
                pstmt.setInt(5, this.salary);

                pstmt.executeUpdate();
                System.out.println("employe inserted successfully!");
            } else {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
