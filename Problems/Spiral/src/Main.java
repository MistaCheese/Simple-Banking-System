import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int exm = sc.nextInt();

        int[][] mainArray = new int[exm][exm];
        int x = 1;

        for (int i = 0; i < exm * exm; i++) { // Минус 2 с каждого ряда

          for (int j = 0; j < exm; j++) {
              mainArray[0][i] = x;
              ++x;
          }
        }



        for (int[] i : mainArray) {
            for (int i1 : i) {
                System.out.print(i1 + " ");
            }
            System.out.println();
        }

    }
}