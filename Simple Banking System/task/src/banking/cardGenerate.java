package banking;

import java.util.Random;

public class cardGenerate {


    public static String[] newCardGenerate() { // Генерация всей карты

        String balanceNewCard = "0"; // нулевой баланс новой карты
        String[] newCard = new String[3];
        newCard[0] = cardNumberGenerate();
        newCard[1] = pinGenerate();
        newCard[2] = balanceNewCard;
        sqLite.insertToBase(newCard[0], newCard[1]);




        return newCard;
    }

    public static String pinGenerate() { // Генерация PIN кода

        Random random = new Random();
        String pin = "";
        for (int i = 0; i < 4; i++) {
            pin = pin + random.nextInt(10);
        }

        return pin;
    }

    public static String cardNumberGenerate() { // Генерация номера карты

        Random random = new Random();
        String cardNumberGenerate = "400000";

        for (int i = 0; i < 9; i++) {

            cardNumberGenerate = cardNumberGenerate + random.nextInt(10);
        }
        int algLuna = 0; // Итоговая сумма по алгоритму Луна
        for (int i = 0; i < cardNumberGenerate.length(); i++) { // Алгоритм Луна по поиску контрольного 16го числа для номера карты

            int exm = Integer.parseInt(String.valueOf(cardNumberGenerate.charAt(i))); // Вытаскиваем посимвольно цифры
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

        cardNumberGenerate += algLuna;


        return cardNumberGenerate;
    }
}
