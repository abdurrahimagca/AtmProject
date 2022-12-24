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
    public static String returnID{
        //todo:sqlden id çekilecek ve ana programa pushlanacak
    }
}
