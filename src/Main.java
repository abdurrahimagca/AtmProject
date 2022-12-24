import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //todo: kart numarasının valid olduğundan emin olunmalı,bu database'de oldugundan emin olunmasıyla
        //todo: aynıdır
        System.out.println("Lutfen Kart Numaranizi Giriniz: ");
        Scanner sc = new Scanner(System.in);
        String cardNum = sc.next();
        System.out.println("Lutfen dort haneden olusan pininizi giriniz: ");
        String pin = sc.next();
        String cardsPin = null;

        String query = "SELECT PIN FROM clients WHERE CardNum=" + cardNum;

        //todo: pinin dört haneden oluştuğundan ve integer oldugundan emin olunmalı
        //todo: bu databaseden önce kontrol edilmelidir ?

        ResultSet rs = SqlQuery.getResult(query);

            try {
                if (rs != null) {
                    while (rs.next()) {
                        cardsPin = rs.getString("PIN");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        if(pin.equals(cardsPin))
        {
            System.out.println("Sifre Dogru");
        }
        else
        {
            //todo: şifre bir kaç kez daha istenmeli, üç kez hatadan sonra database'de şifre
            //todo: blocked olarak işaretlenmeli, bu bu kısma elif olarak eklenecektir.
            System.out.println("sifreniz hatali");
        }




    }
}