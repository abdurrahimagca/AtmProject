import java.sql.ResultSet;
import java.sql.SQLException;

public class Card {

    protected static String cardNum;
    protected static String pin;

    public Card(String cardNum, String pin) {
        this.cardNum = cardNum;
        this.pin = pin;
    }


    public static String getCardNum() {
        return cardNum;
    }

    public static String getPin() {
        return pin;
    }
    public static String returnID() throws SQLException {
        //todo:sqlden id çekilecek ve ana programa pushlanacak
        ResultSet rs = SqlQuery.getResult(("SELECT id FROM clients WHERE CardNum=" + cardNum));
        while (rs.next())
        {
             return rs.getString("id");
        }
        return null;
    }
}
