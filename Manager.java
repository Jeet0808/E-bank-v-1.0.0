package ebank;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import java.sql.PreparedStatement;


public class Manager {
    String name;
    String degree;
    String mno;
    Scanner sc = new Scanner(System.in);
    Conn conn;



    public Manager(String name,String degree,String mno){
        this.name = name;
        this.degree = degree;
        this.mno = mno;
        this.conn = new Conn();

        insertmanagerintodb();

    }

    private void insertmanagerintodb() {
        try{
            if(this.conn.c!=null){
                // writing query

                String query = "insert into managers (name, degree ,mno) values (?,?,?)";

                PreparedStatement pstatment = this.conn.c.prepareStatement(query);
                pstatment.setString(1,this.name);
                pstatment.setString(2,this.degree);
                pstatment.setString(3,this.mno);
                pstatment.executeUpdate();
                System.out.println("manager details inserted successfully!");

            }else{
                System.out.println("Connection is null!");
            }
        }catch (SQLException e){
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void getmaninfo(){
        System.out.println("name of manager is : "+name);
        System.out.println("degre is : "+degree);
        System.out.println("registered no  is : "+mno);

    }





}
