import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        //todo:bura tamamen ayrı bi class olabilecek kadar uygulamadan bağımsız bir main ?
        //todo: kart numarasının valid olduğundan emin olunmalı,bu database'de oldugundan emin olunmasıyla
        //todo: aynıdır
        System.out.println("Lutfen Kart Numaranizi Giriniz: ");
        Scanner sc = new Scanner(System.in);
        String cardNum;
        String query;
        String pin;
        String cardsPin = null;
        do {
            cardNum = sc.next();
            query = "SELECT Name FROM clients WHERE CardNum=" + cardNum;
            ResultSet checkRs = SqlQuery.getResult(query);
            if(!checkRs.next())
                System.out.println("hatali bir kart numarasi girdiniz lutfen tekrar deneyiniz.. ");
            else
                break;
        }while (true);


        //todo en mantıklı ve akla yatkın olan bu kısmın bir fonksiyon olması ? 59-60. satıra bakınız.
        System.out.println("Lutfen dort haneden olusan pininizi giriniz: ");
        int attempts = 0;
        do
        {
            pin = sc.next();
            if(attempts > 3)
            {
                //todo: cardnum PIN degeri BLOCKED olarak değiştiriip uygulamadan set edilmelidir.
                //todo: bu bir sql querysidir.

            }
            else if (pin.length()==4)
            {
                try
                {
                    int x = Integer.parseInt(pin);
                    break;
                }catch(Exception e){
                    attempts++;
                    System.out.println("Pin rakamlardan olusmalidir. ");
            }
            }
            else {
                attempts++;
                System.out.println("Lutfen Dort Haneli Pininizi Giriniz: ");

            }

        }while(true);

        query = "SELECT PIN FROM clients WHERE CardNum=" + cardNum;

        //todo: pinin dört haneden oluştuğundan ve integer oldugundan emin olunmalı
        //todo: bu databaseden önce kontrol edilmelidir ?

        ResultSet rs = SqlQuery.getResult(query);

            try {
                    //burada varolan uyarı anlamsız, not null veri döndürüyor
                    while (rs.next()) {
                        cardsPin = rs.getString("PIN");

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