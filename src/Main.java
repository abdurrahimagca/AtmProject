import java.sql.*;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    do {
      System.out.println("Lutfen Kart Numaranizi giriniz... ");
      String CardNum = sc.next();
      System.out.println("Lutfen pininizi giriniz... ");
      String Pin = sc.next();

      Card card = new Login(CardNum, Pin);
    } while (!Login.login());

    String currentID = Card.returnID();
    int checker = -1;

    do {
      // terminal her seferinde temizlenmeli
      System.out.println(
          "Kullanibilir bakiyeniz: "
              + SqlQuery.StringGetSQL(
                  "SELECT deposit FROM clients WHERE id=" + currentID, "deposit"));
      System.out.println("Lutfen yapmak istediginiz islemi seciniz. ");
      System.out.println("1: Para Cek     2: Para Yatir");
      System.out.println("3: Para Gonder  4: Borc Ode");
      System.out.println("      0:Cikis Yap    ");
      checker = sc.nextInt();
      Scanner scd = new Scanner(System.in);
      double amount;
      switch (checker) {
        case 0:
          System.out.println("Yine Bekleriz");
          break;
        case 1:
          System.out.println(
              "lutfen cekmek istediginiz tutari giriniz, bu minimum 10 en fazla 1000'dir");
          amount = scd.nextDouble();
          if (Transactions.withdraw(currentID, amount)) {
            System.out.println("para cekme basarili");
          } else {
            System.out.println("para cekme islemi basarisiz");
          }
          break;
        case 2:
          System.out.println("Lutfen yatirmak istediginiz tutari giriniz. ");
          amount = scd.nextDouble();
          if (Transactions.deposit(currentID, amount)) {
            System.out.println("para yatirma islemi basarili.");
          } else {
            System.out.println("para yatirma islemi basarisiz.");
          }
          break;
        case 3:
          System.out.println("Para gondermek istediginiz IBANI giriniz.. ");
          System.out.printf("TR ");
          String IBAN = sc.next();
          System.out.println("Gondermek istediginiz miktari giriniz.. ");
          amount = scd.nextDouble();
          if (Transactions.transfer(currentID, IBAN, amount)) {
            System.out.println("para gonderildi");
          } else System.out.println("para gonderilemedi.");
          break;
        case 4:
          System.out.printf("Borcunuz: ");
          System.out.printf(
              SqlQuery.StringGetSQL("SELECT debt FROM clients WHERE id=" + currentID, "debt"));
          System.out.printf(" lutfen odemek istediginiz tutari giriniz.. ");
          amount = scd.nextDouble();
          if (Transactions.payOffDebt(currentID, amount))
            System.out.println("borcunuz basariyla odendi");
          else System.out.println("borc odenemedi");
          break;
        default:
          System.out.println("Eksik ya da hatali bir tuslama yaptiniz lutfen tekrar deneyiniz..");
      }

    } while (checker != 0);
  }
}
