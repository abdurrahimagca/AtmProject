import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Lutfen Kart Numaranizi ve Pininizi giriniz... ");
            String CardNum = sc.next();
            String Pin = sc.next();

            Card card = new Login(CardNum, Pin);
        }while(!Login.login());
        //todo: sqlden veri alınan her fonksiyon sqlquery içine atılmalı
        String currentID = Card.returnID();



        //todo: do-while dongusu icinde opsiyonlar sunulacak
        int checker=-1;
        do {
            //terminal her seferinde temizlenmeli
            System.out.println("Kullanibilir bakiyeniz: "+ SqlQuery.StringGetSQL("SELECT deposit FROM clients WHERE id=" + currentID,"deposit"));
            System.out.println("Lutfen yapmak istediginiz islemi seciniz. ");
            System.out.println("1: Para Cek     2: Para Yatir");
            System.out.println("3: Para Gonder  4: Kredi Karti Borcu Ode");
            System.out.println("5: Fatura Ode    0:Cikis Yap");
            checker = sc.nextInt();
            switch (checker){
                case 0:
                    System.out.println("Yine Bekleriz");
                    break;
                case 1:
                    System.out.println("para cekme fonksiyonu");
                    Scanner scd = new Scanner(System.in);
                    double amount = scd.nextDouble();
                    if(Transactions.withdraw(currentID,amount))
                    {
                        System.out.println("para cekme basarili");
                    }
                    else{
                        System.out.println("para cekme islemi basarisiz");
                    }
                    break;
                case 2:
                    System.out.println("para yatti");
                    break;
                case 3:
                    System.out.println("para gonder");
                    break;
                case 4:
                    System.out.println("kk borcu odendi");
                    break;
                case 5:
                    System.out.println("fatura odendi");
                    break;
                default:
                    System.out.println("Eksik ya da hatali bir tuslama yaptiniz lutfen tekrar deneyiniz..");

            }

        }while(checker != 0);


    }
}