package banking;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    final static String one = "1";
    final static String two = "2";
    final static String zero = "0";
    static int logout = 0;

    public static void main(String[] args) {
        String dbName = "test.s3db";


        if (args.length > 0) {
            sqLite.SetNameDB(args[1]);
            sqLite.createDB(); // Создание БД и таблицы по умолчанию, когда указано имя БД в аргументах запуска
        } else {
            sqLite.SetNameDB(dbName);
        }
        try {
            sqLite.connectToDB();

        } catch (Exception ignored) {
        }

        System.out.println("1. Create an account\n" + "2. Log into account\n" + "0. Exit");
        ArrayList<String[]> cardDB = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {  // Основной цикл работы

            String mainInput = sc.next();
            switch (mainInput) {
                case one:

                    cardDB.add(cardGenerate.newCardGenerate());   // Выводим номер карты и пин
                    System.out.println("\nYour card number:");
                    System.out.println(cardDB.get(cardDB.size() - 1)[0]);
                    System.out.println("Your card PIN:");
                    System.out.println(cardDB.get(cardDB.size() - 1)[1]);
                    break;

                case two:
                    logIntoAccount.logMenu(); // Авторизация в меню карты
                    break;

            }
            if (mainInput.contains(zero)) { // Выход из основного меню
                System.out.println("Bye!");


                break;

            }
            if (logout == 2) {  // Выход из аккаунта
                System.out.println("Bye!");
                break;
            }
            System.out.println("\n1. Create an account\n" + "2. Log into account\n" + "0. Exit");
        }
    }
}
