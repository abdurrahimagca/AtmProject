import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Card {

  public Login(String cardNum) {
    super(cardNum);
  }

  public static boolean isCardValid() throws SQLException {
    ResultSet checkRs = SqlQuery.getResult(("SELECT id FROM clients WHERE CardNum=" + cardNum));

    while (checkRs.next()) {
      return true;
    }
    System.out.println("hatali bir kart numarasi girdiniz lutfen tekrar deneyiniz.. ");
    return false;
  }

  public static boolean isPinTrue() {

    String cardsPin =
        SqlQuery.StringGetSQL("SELECT PIN FROM clients WHERE CardNum=" + cardNum, "PIN");

    if (cardsPin.equals("BLOCKED")) {
      System.out.println("Pininiz bloke");
      System.exit(2);
    }

    if (!(pin.length() == 4)) {
      System.out.println("Pininiz Dort Haneden Olusmalidir");
      return false;
    }
    try {
      int x = Integer.parseInt(pin);
    } catch (Exception e) {

      System.out.println("Pin rakamlardan olusmalidir. ");
      return false;
    }
    if (!(pin.equals(cardsPin))) {
      System.out.println("sifreniz hatalidir. ");
      return false;
    }
    return true;
  }
}
