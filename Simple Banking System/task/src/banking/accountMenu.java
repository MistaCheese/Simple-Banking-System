package banking;

import java.util.Scanner;

import static java.lang.Integer.valueOf;

public class accountMenu {
    public static void accMenu(String[] cardInfo) { // 1. Card Number 2. Card Pin 3. Card Balance
        Scanner sc = new Scanner(System.in);
        String menuNavigate;

        while (true) {
            System.out.println("\n1. Balance\n" + "2. Add income\n" + "3. Do transfer\n" + "4. Close account\n" + "5. Log out\n" + "0. Exit");
            menuNavigate = sc.next();

            switch (menuNavigate) {
                case "1":
                    System.out.println("\nBalance: " + sqLite.getAccInfo(cardInfo[0], cardInfo[1])[2]); // Баланс карты По номеру и пин коду
                    break;
                case "2":
                    System.out.println("Enter income: ");
                    int setBalance = Integer.parseInt(sc.next());
                    sqLite.updateBalance(cardInfo[0], setBalance); // Увеличить баланс на указанное число
                    System.out.println("Income was added!");
                    break;
                case "3":
                    System.out.println("Transfer");
                    System.out.println("Enter card number:");
                    String cardRecipient = sc.next();
                    if (transferBalanceToOtherClient(cardInfo, cardRecipient)) { // Проверка условий
                        System.out.println("Enter how much money you want to transfer:");
                        int sumBalance = sc.nextInt();
                        if (transferBalanceToOtherClient(cardInfo, cardRecipient, sumBalance)) { // Проверка, что хватает баланса на транзакцию
                            sqLite.updateBalance(cardRecipient, sumBalance); // Пересылка денег на другую карту
                            sqLite.transferBalance(cardInfo[0], sumBalance); // Списание средств с карты отправителя
                            System.out.println("Success!");
                        }

                    } else {
                        break;
                    }
                    break;

                case "4":
                    sqLite.deleteAccount(cardInfo[0]);
                    System.out.println("The account has been closed!");
                    Main.logout = 1;
                    break;
                case "5":
                    System.out.println("\nYou have successfully logged out!");
                    Main.logout = 1;
                    break;
                case "0":
                    Main.logout = 2;
                    break;
            }
            if (Main.logout > 0) {
                break;
            }
        }
    }

    public static boolean transferBalanceToOtherClient(String[] cardInfo, String cardRecipient) {
        boolean checkAll;

        if (cardInfo[0].contains(cardRecipient)) { // Если перевод совершается на свой же акк
            System.out.println("You can't transfer money to the same account!");
            checkAll = false;
        } else if (sqLite.getAccInfo(cardInfo[0]) == null) { // Если такого номера нет в базе
            System.out.println("Such a card does not exist");
            checkAll = false;

        } else if (!String.valueOf(algorithmLuna(cardRecipient)).contains(String.valueOf(cardRecipient.charAt(cardRecipient.length() - 1)))) { // Номер карты не проходит проверку алгоритма Луна
            System.out.println("Probably you made mistake in the card number. Please try again!");
            checkAll = false;

        } else {
            checkAll = true;

        }

        if (sqLite.getAccInfo(cardInfo[0], cardInfo[1])[0] == null && checkAll) { // Если номера карты не существует
            System.out.println("Such a card does not exist.");
            checkAll = false;
        }
        return checkAll;
    }

    public static boolean transferBalanceToOtherClient(String[] cardInfo, String cardRecipient, int sum) {
        boolean checkAll = true;

        if (sum > Integer.parseInt(sqLite.getAccInfo(cardInfo[0], cardInfo[1])[2])) { // Проверка, что сумма для перевода не превышает баланс
            System.out.println("Not enough money!");
            checkAll = false;
        }
        return checkAll;
    }



    public static int algorithmLuna(String[] cardInfo) {
        int algLuna = 0; // Итоговая сумма по алгоритму Луна
        for (int i = 0; i < cardInfo[0].length() - 1; i++) { // Алгоритм Луна по поиску контрольного 16го числа для номера карты

            int exm = Integer.parseInt(String.valueOf(cardInfo[0].charAt(i))); // Вытаскиваем посимвольно цифры
            if (i % 2 == 0) {
                if (exm * 2 < 9) {
                    algLuna += exm * 2;
                } else if (exm * 2 > 9) {
                    algLuna += exm * 2 - 9;
                }
            } else {
                algLuna += exm;
            }
        }

        if (algLuna % 10 == 0) {
            algLuna = 0;                             // Вычесление недостающего числа
        } else {
            algLuna = Math.abs((algLuna % 10) - 10); // Вычесление недостающего числа
        }
        return algLuna;
    }

    public static int algorithmLuna(String cardInfo) {
        int algLuna = 0; // Итоговая сумма по алгоритму Луна
        for (int i = 0; i < cardInfo.length() - 1; i++) { // Алгоритм Луна по поиску контрольного 16го числа для номера карты

            int exm = Integer.parseInt(String.valueOf(cardInfo.charAt(i))); // Вытаскиваем посимвольно цифры
            if (i % 2 == 0) {
                if (exm * 2 < 9) {
                    algLuna += exm * 2;
                } else if (exm * 2 > 9) {
                    algLuna += exm * 2 - 9;
                }
            } else {
                algLuna += exm;
            }
        }

        if (algLuna % 10 == 0) {
            algLuna = 0;                             // Вычесление недостающего числа
        } else {
            algLuna = Math.abs((algLuna % 10) - 10); // Вычесление недостающего числа
        }
        return algLuna;
    }
}
