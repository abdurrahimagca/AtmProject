public class Transactions {
    private static double deposit;
    private static String temp = null;

    //todo: para çek
    public static boolean withdraw(String id, double amount) {
        System.out.println("withdraw line 7");
        temp = SqlQuery.StringGetSQL("SELECT deposit FROM clients WHERE id=" + id, "id");
        System.out.println("withdraw line 9");
        try {
            deposit = Double.parseDouble(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(deposit < amount)
            return false;
        else if (deposit > amount)
        {
            deposit = deposit - amount;
            temp = String.valueOf(deposit);
            SqlQuery.UpdateData("UPDATE client SET deposit=" + temp + " WHERE id=" + id);
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
