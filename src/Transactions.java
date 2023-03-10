

public class Transactions {

    //hata: eger fonksiyon exception verirse sorun olabilir
    private static double stringToDouble(String text) {
        //handle in 5 usages that if catch statement works, return an escape integer
        double val = 0;
        try {

            val = Double.parseDouble(text);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return val;
    }



    public static boolean withdraw(String id, double amount) {
        double deposit;
        if (amount < 10 || amount > 1000) {
            System.out.println("cekilecek tutar 10'dan kucuk olamaz");
            return false;
        }

        String temp = SqlQuery.StringGetSQL("SELECT deposit FROM clients WHERE id=" + id, "deposit");


        try {
            deposit = Double.parseDouble(temp);
            System.out.println(deposit);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (deposit < amount) {
            System.out.println("cekilmek istenen tutar bakiyeden fazla.. ");
            return false;
        } else if (deposit > amount) {
            deposit = deposit - amount;
            temp = String.valueOf(deposit);

            SqlQuery.UpdateData("UPDATE clients SET deposit=" + temp + "WHERE id=" + id);

            return true;
        }
        return false;
    }


    public static boolean deposit(String id, double amount) {
        double deposit;

        String temp = SqlQuery.StringGetSQL("SELECT deposit FROM clients WHERE id=" + id, "deposit");
        if (amount < 1) {
            System.out.println("yatiralacak tutar sifirdan kucuk olamaz");
            return false;
        }

        deposit = stringToDouble(temp);

        deposit = deposit + amount;
        temp = String.valueOf(deposit);
        SqlQuery.UpdateData("UPDATE clients SET deposit=" + temp + "WHERE id=" + id);

        return true;

    }



    public static boolean transfer(String id, String IBAN, double amount) {
        //todo: iban uzunlugunun kontrol edilmesi gerekli
        if(IBAN.length()!=24) return false;

        double depositSender, depositReceiver;
        String temp = SqlQuery.StringGetSQL("SELECT deposit FROM clients WHERE id=" + id, "deposit");
        depositSender = stringToDouble(temp);
        depositSender = depositSender - amount;
        if (amount < 1) {
            System.out.println("Gondereceginiz tutar 0'dan buyuk olmal??d??r. ");
            return false;
        }
        if (depositSender < amount) {
            System.out.println("Bakiye yetersiz. ");
            return false;
        }
        temp = SqlQuery.StringGetSQL("SELECT deposit FROM clients WHERE IBAN LIKE '%" + IBAN + "'", "deposit");
        depositReceiver = stringToDouble(temp);

        depositReceiver = depositReceiver + amount;

        temp = String.valueOf(depositReceiver);
        SqlQuery.UpdateData("UPDATE clients SET deposit=" + temp + " WHERE IBAN LIKE '%" + IBAN + "'");

        temp = String.valueOf(depositSender);
        SqlQuery.UpdateData("UPDATE clients SET deposit=" + temp + "WHERE id=" + id);
        return true;

    }


    public static boolean payOffDebt(String id, double amount) {
        double deposit, debt;
        String temp = SqlQuery.StringGetSQL("SELECT debt FROM clients WHERE id=" + id, "debt");
        debt = stringToDouble(temp);
        temp = SqlQuery.StringGetSQL("SELECT deposit FROM clients WHERE id=" + id, "deposit");
        deposit = stringToDouble(temp);
        if (amount > deposit) {
            System.out.println("Odemek istediginiz tutar bakiyenizden fazla olamaz. ");
            return false;
        } else if (amount > debt) {

            deposit = deposit - debt;
            debt = 0;
            temp = String.valueOf(deposit);
            SqlQuery.UpdateData("UPDATE clients SET deposit=" + temp + "WHERE id=" + id);
            SqlQuery.UpdateData("UPDATE clients SET debt=0 WHERE id=" + id);
            System.out.println("girdiginiz tutar borcunuzdan fazladir, borcunuz: " + debt + " TL ??denmistir. ");
            return true;

        } else {
            deposit = deposit - amount;
            //todo: handle if temp is not a string
            temp = String.valueOf(deposit);
            SqlQuery.UpdateData("UPDATE clients SET deposit=" + temp + "WHERE id=" + id);
            debt = debt - amount;
            temp = String.valueOf(debt);
            SqlQuery.UpdateData("UPDATE clients SET debt=" + temp + "WHERE id=" + id);
            return true;
        }
    }


}
