import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt(); // Длина массива
        int y = sc.nextInt();  // Длина вложенного массива

        int[][] bigArray = new int[x][]; // Дмумерный массив
        int[] smallArray = new int[y]; // Одномерный вложенный массив

        for (int j = 0; j < x; j++) {   // Запись данных в двумерный массив
            for (int i = 0; i < y; i++) {
                smallArray[i] = sc.nextInt();
            }
            bigArray[j] = smallArray.clone();
        }

        int tickets = sc.nextInt(); // Билеты
        int tick = 0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (bigArray[i][j] == 0) {
                    ++tick;
                } else {
                    tick = 0;
                }
                if (tick == tickets) {
                    System.out.println(i + 1);
                    break;
                }

            }
            if (tick == tickets) {

                break;
            } else {
                tick = 0;
            }
        }

        if (tick < tickets) {
            System.out.println(0);
        }


    }
}