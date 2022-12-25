import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Card {


    public Login(String cardNum, String pin) {
        super(cardNum, pin);
    }

    private static String cardsPin;
    private static String cardsNum;
    private static int attempts = 0;

    private static boolean isCardValid(String cardNum) throws SQLException {
        ResultSet checkRs = SqlQuery.getResult(("SELECT Name FROM clients WHERE CardNum=" + cardNum));

        if (!checkRs.next()) {
            System.out.println("hatali bir kart numarasi girdiniz lutfen tekrar deneyiniz.. ");
            return false;
        }

        return true;


    }

    private static boolean isPinTrue(String cardsPin, String cardPin) {

        if (attempts > 3) {
            //todo: her seferinde yeni obje olusturuldugundan bunun yapılması o kadar da kolay degil attemps degişkeni daima sıfır oluyor
            SqlQuery.UpdateData("UPDATE clients SET PIN='BLOCKED' WHERE CardNum=" + cardNum);

            return false;

        } else if (cardPin.length() == 4) {
            try {
                int x = Integer.parseInt(cardPin);
                ResultSet rs = SqlQuery.getResult(("SELECT PIN FROM clients WHERE CardNum=" + cardNum));

                //burada varolan uyarı anlamsız, not null veri döndürüyor
                while (rs.next())
                    cardsPin = rs.getString("PIN");

                if (pin.equals(cardsPin)) {
                    System.out.println("Sifre Dogru");
                    return true;
                } else {
                    System.out.println("sifreniz hatali, lutfen tekrar deneyiniz..");
                }

            } catch (Exception e) {
                attempts++;
                System.out.println("Pin rakamlardan olusmalidir. ");
                return false;

            }
        } else {
            attempts++;
            System.out.println("Lutfen Dort Haneli Pininizi Giriniz: ");
            return false;

        }
        return false;
    }

    public static boolean login() throws SQLException {
        if (isCardValid(cardNum) && isPinTrue(cardNum, pin)) {
            System.out.println("Hosgeldiniz. Basariyla giris yaptiniz..");
            //todo: sql tabanindan kullanicinin adi cekilip ekrana basilmali
            return true;
        }
        return false;
    }

}
