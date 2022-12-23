import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        //kart numarasının valid olduğundan emin olunmalı
        System.out.println("Lutfen Kart Numaranizi Giriniz: ");
        Scanner sc = new Scanner(System.in);
        String cardNum = sc.next();
        System.out.println("Lutfen dort haneden olusan pininizi giriniz: ");
        String pin = sc.next();
        String cardsPin = null;

        String query = "SELECT PIN FROM clients WHERE CardNum=" + cardNum;

        //pinin dört haneden oluştuğundan ve integer oldugundan emin olunmalı

        ResultSet rs = SqlQuery.getResult(query);
        while(true)
        {
            assert rs != null;
            if (!rs.next()) break;
            cardsPin = rs.getString("PIN");
        }
        if(pin.equals(cardsPin))
        {
            System.out.println("Sifre Dogru");
        }
        else
        {
            //todo: şifre bir kaç kez daha istenmeli, üç kez hatadan sonra database'de şifre
            //blocked olarak işaretlenmeli, bu bu kısma elif olarak eklenecektir.
            System.out.println("sifreniz hatali");
        }




    }
}