import java.sql.SQLException;

public class Transactions {
    private static double deposit;


    //todo: para çek
    public static boolean withdraw(String id, double amount) throws SQLException {

        String temp  = SqlQuery.StringGetSQL("SELECT deposit FROM clients WHERE id=" + id, "deposit");

        try {
            deposit = Double.parseDouble(temp);
            System.out.println(deposit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(deposit < amount)
            return false;

        else if (deposit > amount)
        {
            deposit = deposit - amount;
            temp = String.valueOf(deposit);

            SqlQuery.UpdateData("UPDATE clients SET deposit=" + temp + "WHERE id=" + id);
            //todo: güncelleme işleminin doğru olup olmadığı kontrol edilebilri
            return true;
        }
    return false;
}



    //todo: para gönder

    //todo: borç öde
    //todo: para yatır
    //todo: pin değiştir
    //todo: ...
}
