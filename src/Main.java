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
            //System.out.println(query);
            ResultSet checkRs = SqlQuery.getResult(query);
            if (!checkRs.next())
                System.out.println("hatali bir kart numarasi girdiniz lutfen tekrar deneyiniz.. ");
            else
                break;
           } while (true);


        System.out.println("Lutfen dort haneden olusan pininizi giriniz: ");
        int attempts = 0;
        do {
            pin = sc.next();
            if (attempts > 3)
            {
                //todo: cardnum PIN degeri BLOCKED olarak değiştiriip uygulamadan set edilmelidir.
                //todo: bu bir sql querysidir.

            }
            else if (pin.length() == 4)
            {
                try {
                    int x = Integer.parseInt(pin);
                    query = "SELECT PIN FROM clients WHERE CardNum=" + cardNum;
                    ResultSet rs = SqlQuery.getResult(query);

                        //burada varolan uyarı anlamsız, not null veri döndürüyor
                    while (rs.next())
                        cardsPin = rs.getString("PIN");

                    if (pin.equals(cardsPin))
                    {
                        System.out.println("Sifre Dogru");
                        break;
                    }
                    else
                    {
                        System.out.println("sifreniz hatali, lutfen tekrar deneyiniz..");
                    }

                } catch (Exception e) {
                    attempts++;
                    System.out.println("Pin rakamlardan olusmalidir. ");
                }
            }
            else
            {
                attempts++;
                System.out.println("Lutfen Dort Haneli Pininizi Giriniz: ");
            }

        } while (true);


    }
}