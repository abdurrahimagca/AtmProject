import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        do {
            Scanner sc = new Scanner(System.in);
            String CardNum = sc.next();
            String Pin = sc.next();

            Card card = new Login(CardNum, Pin);
        }while(!Login.login());



    }
}