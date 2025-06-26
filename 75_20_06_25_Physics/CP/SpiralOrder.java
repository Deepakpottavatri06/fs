import java.util.*;

public class SpiralOrder {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int r = cin.nextInt();
        int c = cin.nextInt();
        int mat[][] = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = cin.nextInt();
            }
        }
        find(mat, r, c);
        cin.close();
    }

    static void find(int mat[][], int r, int c) {
        int res[][] = new int[r][c];

        int resi = 0;
        int resj = 0;

        int right = c - 1;
        int bot = r - 1;
        int left = 0;
        int top = 0;

        while (top <= bot && left <= right) {

            for (int j = left; j <= right; j++) {
                res[resi][resj] = mat[top][j];
                resj = (resj + 1) % c;
                resi += (resj == 0) ? 1 : 0;
            }
            top++;

            for (int i = top; i <= bot; i++) {
                res[resi][resj] = mat[i][right];
                resj = (resj + 1) % c;
                resi += (resj == 0) ? 1 : 0;
            }

            right--;

            if (top <= bot) {
                for (int j = right; j >= left; j--) {
                    res[resi][resj] = mat[bot][j];
                    resj = (resj + 1) % c;
                    resi += (resj == 0) ? 1 : 0;
                }
                bot--;
            }

            if (left <= right) {
                for (int i = bot; i >= top; i--) {
                    res[resi][resj] = mat[i][left];
                    resj = (resj + 1) % c;
                    resi += (resj == 0) ? 1 : 0;
                }
                left++;

            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }

    }
}