//package ebank;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//public class Account {
//    String name;
//    int age;
//    private int accNo;
//    private int balance;
//    private ArrayList<Transaction> transactions;
//
//    public Account(String name, int age, int accNo, int balance) {
//        this.name = name;
//        this.age = age;
//        this.accNo = accNo;
//        this.balance = balance;
//        this.transactions = new ArrayList<>();
//    }
//
//    public void printInfo() {
//        System.out.println("Account Holder: " + name);
//        System.out.println("Age: " + age);
//        System.out.println("Account Number: " + accNo);
//        System.out.println("Balance: " + balance);
//    }
//
//    public int getAccNo() {
//        return accNo;
//    }
//
//    public int getBalance() {
//        return balance;
//    }
//
//    public void credit(int amount) {
//        balance += amount;
//        Transaction creditTransaction = new Transaction(LocalDateTime.now(), "Credited", amount);
//        transactions.add(creditTransaction);
//
//    }
//
//    public void debit(int amount) {
//        if (amount <= balance) {
//            balance -= amount;
//            Transaction debitTransaction = new Transaction(LocalDateTime.now(), "Debited", amount);
//            transactions.add(debitTransaction);
//            System.out.println("Amount debited: " + amount);
//        } else {
//            System.out.println("Insufficient funds.");
//        }
//    }
//
//    public void transfer(Account targetAccount, int amount) {
//        if (amount <= balance) {
//            debit(amount);
//            targetAccount.credit(amount);
//            System.out.println("Transferred " + amount + " to Account No: " + targetAccount.getAccNo());
//        } else {
//            System.out.println("Insufficient funds.");
//        }
//    }
//
//    public void printTransactions() {
//        System.out.println("Transaction History for Account Number: " + accNo);
//        for (Transaction transaction : transactions) {
//            System.out.println(transaction);
//
//        }
//    }
//
//    public static class Transaction {
//        private LocalDateTime timestamp;
//        private String transactionType;
//        private int amount;
//
//        public Transaction(LocalDateTime timestamp, String transactionType, int amount) {
//            this.timestamp = timestamp;
//            this.transactionType = transactionType;
//            this.amount = amount;
//        }
//
//        @Override
//        public String toString() {
//            return timestamp + " - " + transactionType + ": " + amount;
//        }
//    }
//}

package ebank;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Account {
    String name;
    int age;
    private int accNo;
    private int balance;
    private ArrayList<Transaction> transactions;

    // Create a connection object to interact with the database
    Conn conn;

    public Account(String name, int age, int accNo, int balance) {
        this.name = name;
        this.age = age;
        this.accNo = accNo;
        this.balance = balance;
        this.transactions = new ArrayList<>();

        // Initialize the connection object
        this.conn = new Conn();

        // Insert account details into the database when an account is created
        insertAccountToDB();
//        insertinfo();
    }

//    private void insertinfo() {
//        try {
//            // Ensure the connection is not null
//            if (this.conn.c != null) {
//                String sql = "INSERT INTO info (name, age, accNo, balance) VALUES (name, age, accNo, balance)";
//                PreparedStatement pstmt = this.conn.c.prepareStatement(sql);
//                pstmt.setString(1, this.name);
//                pstmt.setInt(2, this.age);
//                pstmt.setInt(3, this.accNo);
//                pstmt.setInt(4, this.balance);
//                pstmt.executeUpdate();
//                System.out.println("Account inserted successfully!");
//            } else {
//                System.out.println("Connection is null!");
//            }
//        } catch (SQLException e) {
//            System.out.println("SQL Exception: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//    }

    // Method to insert account details into the database
    public void insertAccountToDB() {
        try {
            // Ensure the connection is not null
            if (this.conn.c != null) {
                String sql = "INSERT INTO accounts (name, age, accNo, balance) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = this.conn.c.prepareStatement(sql);
                pstmt.setString(1, this.name);
                pstmt.setInt(2, this.age);
                pstmt.setInt(3, this.accNo);
                pstmt.setInt(4, this.balance);
                pstmt.executeUpdate();
                System.out.println("Account inserted successfully!");
            } else {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void transfer(Account targetAccount, int amount) {
        if (amount <= balance) {
            debit(amount);
            targetAccount.credit(amount);
            System.out.println("Transferred " + amount + " to Account No: " + targetAccount.getAccNo());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void printInfo() {
        System.out.println("Account Holder: " + name);
        System.out.println("Age: " + age);
        System.out.println("Account Number: " + accNo);
        System.out.println("Balance: " + balance);
    }

    public int getAccNo() {
        return accNo;
    }

    public int getBalance() {
        return balance;
    }

    public void credit(int amount) {
        balance += amount;
        Transaction creditTransaction = new Transaction(LocalDateTime.now(), "Credited", amount);
        transactions.add(creditTransaction);

        // Insert transaction into the database
        insertTransactionToDB(creditTransaction);
    }

    public void debit(int amount) {
        if (amount <= balance) {
            balance -= amount;
            Transaction debitTransaction = new Transaction(LocalDateTime.now(), "Debited", amount);
            transactions.add(debitTransaction);

            // Insert transaction into the database
            insertTransactionToDB(debitTransaction);
            System.out.println("Amount debited: " + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Method to insert transaction details into the database
    private void insertTransactionToDB(Transaction transaction) {
        try {
            // SQL query to insert transaction details
            String query = "INSERT INTO transactions (accNo, transactionType, amount, timestamp) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setInt(1, accNo);
            pstmt.setString(2, transaction.getTransactionType());
            pstmt.setInt(3, transaction.getAmount());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(transaction.getTimestamp()));

            // Execute the insert query
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
        }
    }

    public void printTransactions() {
        System.out.println("Transaction History for Account Number: " + accNo);
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static class Transaction {
        private LocalDateTime timestamp;
        private String transactionType;
        private int amount;

        public Transaction(LocalDateTime timestamp, String transactionType, int amount) {
            this.timestamp = timestamp;
            this.transactionType = transactionType;
            this.amount = amount;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return timestamp + " - " + transactionType + ": " + amount;
        }
    }
}
