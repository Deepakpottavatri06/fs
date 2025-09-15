/*
The Kakatiyas had built a very elaborate dam(reservoir) system consisting of 
rows and rows of dams, all dams have the same capacity X. 

The first row has one dam, second row has two dams and 
third row has three dams and so on. like as below(V -> dam) 
				 V
				V V
               V V V
	        and so on..
There are altogether 100 rows. Water is drawn from the river at X liters each time.
The way water flows is that,
- After the first drawing of water the dam on the first row is filled
- After the second draw from the river, the dams in the second row are half-filled each.
- After the third draw from the river, both the dams in the second row fully filled.
- After the fourth draw from the river, the three dams in the third row are filled 
to an extent of 1/4, 1/2, 1/4.

Given N draws from the river, determine how full the jth dam in the ith row.

The row is number from (0,0) onwards,
	The first row is row =0, dam = 0
	The second row is row =1, dams are 0 and 1. 
	so on...

Input Format:
-----------------
Three space seperated integers, N, i, j

Output Format:
------------------
Print a double value as result


Sample Input-1:
-------------------
2 1 1

Sample Output-1:
---------------------
0.5


Sample Input-2:
-------------------
4 2 2

Sample Output-2:
---------------------
0.25

 */
import java.util.Scanner;

public class KakatiyaDams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
      
        long N = sc.nextLong();
        int i = sc.nextInt();  
        int j = sc.nextInt();  
        sc.close();

        if (j > i) {
            System.out.println("0.0");
            return;
        }


        double[][] dp = new double[i + 2][i + 2];
        dp[0][0] = (double) N;

        for (int r = 0; r <= i; r++) {
            for (int c = 0; c <= r; c++) {
                if (dp[r][c] > 1.0) {
                    double overflow = dp[r][c] - 1.0;
                    dp[r][c] = 1.0;
                    dp[r + 1][c] += overflow / 2.0;
                    dp[r + 1][c + 1] += overflow / 2.0;
                }
               
            }
        }

       
        double result = dp[i][j];

        System.out.println(result);
    }
}
