import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int exm = sc.nextInt();
        int p1 = 0;
        int[][] array = new int[exm][exm];
        try {
            for (int i = 0; i < exm; i++) {


                for (int i1 = 0; i1 < exm - i; i1++) {
                    array[i][i1 + p1] = i1;

                }


                ++p1;


            }
        } catch (Exception e) {
            System.out.println(e);
        }
        p1 = exm - 1;
        try {
            for (int i = exm - 1; i > 0; i--) {


                for (int i1 = 0; i1 < p1 + 1; i1++) {
                    array[i][p1 - i1] = i1;

                }


                --p1;


            }
        } catch (Exception ignored) {

        }


        for (int[] k : array) {
            for (int k1 : k) {
                System.out.print(k1 + " ");
            }
            System.out.println();
        }

    }
}