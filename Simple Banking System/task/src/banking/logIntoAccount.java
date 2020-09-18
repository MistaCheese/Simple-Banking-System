package banking;



import java.util.Scanner;

public class logIntoAccount {
    public static void logMenu() { // Проверка номера карты ипин кода
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter your card number:"); // Вводи номера
        String cardNumberInput = sc.nextLine();
        System.out.println("Enter your PIN:");  // Ввод пина
        String cardPinInput = sc.nextLine();


        if (!sqLite.checkNumberAndPin(cardNumberInput, cardPinInput)) {  // Проверка логина + пин
            System.out.println("\nWrong card number or PIN!");
        } else if (sqLite.checkNumberAndPin(cardNumberInput, cardPinInput)) { // Если ввели верно
            System.out.println("\nYou have successfully logged in!");
            accountMenu.accMenu(sqLite.getAccInfo(cardNumberInput,cardPinInput));  // Меню внутри аккаунта с передачей введенных номера карты и пина
        }

    }
}
