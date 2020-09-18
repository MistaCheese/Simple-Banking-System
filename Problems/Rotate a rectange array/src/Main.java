import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int width = sc.nextInt();
        int height = sc.nextInt();

        int[][] mainArray = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mainArray[i][j] = sc.nextInt();
            }
        }


        for (int i = 0; i < height; i++) {
            for (int j = width - 1; j >= 0; j--) {
                System.out.print(mainArray[j][i] + " ");
            }
            System.out.println();
        }


    }
}