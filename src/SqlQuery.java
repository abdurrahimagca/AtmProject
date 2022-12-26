

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlQuery {
    public static ResultSet getResult(String query) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "root");
            Statement stmt = con.createStatement();
            return stmt.executeQuery(query);

        } catch (Exception e) {
            return null;
        }

    }

    //todo: fonksiyon bool veri döndürmeli
    public static void UpdateData(String query) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "root");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String StringGetSQL(String query, String label) {
        String temp = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                temp = rs.getString(label);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

}

