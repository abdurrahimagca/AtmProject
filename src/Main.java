import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //String cardNum = sc.nextLine();
        Scanner sc2 = new Scanner(System.in);
        //int pin = sc2.nextInt();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/atm","root","root");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT Name,Surname FROM clients");
            while(rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) );
            con.close();


        }catch(Exception e){ System.out.println(e);}



    }
}