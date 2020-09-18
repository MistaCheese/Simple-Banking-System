import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int exm = 0;

        String[][] twoDArray = new String[4][4];
        for (int i = 0; i < 4; i++) {
            twoDArray[i] = sc.nextLine().split("");
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (twoDArray[i][j].contains(twoDArray[i][j + 1])
                        && twoDArray[i][j].contains(twoDArray[i + 1][j])
                        && twoDArray[i][j].contains(twoDArray[i + 1][j + 1])) {
                    ++exm;
                }
            }
        }


        if (exm == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}