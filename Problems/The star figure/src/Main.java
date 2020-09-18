import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int i = sc.nextInt();
        String k = ".";

        String[][] mainArray = new String[i][i];

        for (int j = 0; j < i; j++) {
            for (int j1 = 0; j1 < i; j1++) {
                mainArray[j][j1] = k;
            }
        }

        for (int j = 0; j < i; j++) {

            mainArray[j][j] = "*";
            mainArray[j][(int) Math.ceil(i / 2)] = "*";
            mainArray[(int) Math.ceil(i / 2)][j] = "*";


        }
        for (int j = i - 1; j >= 0; j--) {

            mainArray[j][(i - 1) - j] = "*";
        }







        for (String[] h : mainArray) {
            for (String p : h) {
                System.out.print(p + " ");
            }
            System.out.println();
        }

    }
}