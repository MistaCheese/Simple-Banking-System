import java.util.*;

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


        ArrayList<Integer> set = new ArrayList<>(); // Запись номеров столбцов для замены местами
        set.add(sc.nextInt());
        set.add(sc.nextInt());
        Collections.sort(set);

        int fTempIndex = 0;
        int sTempIndex = 0;

        for (int j = 0; j < x; j++) {   // Цикл для замены чисел в столбцах


            fTempIndex = bigArray[j][set.get(0)];
            sTempIndex = bigArray[j][set.get(1)];
            bigArray[j][set.get(0)] = sTempIndex;
            bigArray[j][set.get(1)] = fTempIndex;


        }
        for (int[] k : bigArray) {
            for (int k1 : k) {
                System.out.print(k1 + " ");
            }
            System.out.println();
        }

    }
}