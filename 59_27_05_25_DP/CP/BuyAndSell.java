
/*Keerthilal wants to try his luck in Diamonds business. 
He decides to buy and sell diamonds. 

He is given the prices of one diamond for N days by his friend.
Initially, it is assumed that he has no diamonds.

You need to help Keerthilal in making the maximum profit that is possible. 
He can sell a diamond only after he buys a diamond. 

Note: 
    - He is allowed to do any number of transactions
      but, he can buy and sell only one diamond per transaction.
    
    - He must complete one transaction before the next transaction.
    
    - After each transaction completed, there is a break day.
    i.e After he sells his diamond, he cannot buy diamond on next day.

		
Input Format:
-------------
Space separated integers, price of the diamond for N days.

Output Format:
--------------
Print an integer, maximum profit.


Sample Input-1:
---------------
7 1 5 3 6 4

Sample Output-1:
----------------
5

Explanation: 
------------
Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.


Sample Input-2:
---------------
1 2 3 1 3

Sample Output-2:
----------------
3

Explanation: 
------------
Buy on day 1 (price = 1) and sell on day 2 (price = 2), profit = 2-1 = 1.
day 3 is a break.
Buy on day 4 (price = 1) and sell on day 5 (price = 3), profit = 3-1 = 2.
Total Maximum Profit = 1+2 = 3

 */
import java.util.Arrays;
import java.util.Scanner;

public class BuyAndSell {
    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp[] = cin.nextLine().split(" ");
            int arr[] = new int[inp.length];
            for (int i = 0; i < inp.length; i++) {
                arr[i] = Integer.parseInt(inp[i]);
            }
            System.out.println(find(arr, inp.length));

        }
    }

    static int find(int arr[], int n) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(i, max);
        }
        int memo[][] = new int[n][max + 2];
        for (int i = 0; i < n; i++)
            Arrays.fill(memo[i], -1);
        return recur(arr, 0, -1, memo);
    }

    static int recur(int arr[], int i, int currStock, int memo[][]) {
        if (i >= arr.length) {
            return 0;
        }
        if (memo[i][currStock + 1] != -1) {
            return memo[i][currStock + 1];
        }
        if (currStock != -1) {

            int opt1 = Integer.MIN_VALUE;
            if (arr[i] >= currStock) {
                opt1 = (arr[i] - currStock) + recur(arr, i + 2, -1, memo);// sell
            }
            int opt2 = recur(arr, i + 1, currStock, memo);// don't sell

            return memo[i][currStock + 1] = Math.max(opt1, opt2);
        } else {
            int opt1 = recur(arr, i + 1, -1, memo);// don't buy
            int opt2 = recur(arr, i + 1, arr[i], memo);// buy

            return memo[i][currStock + 1] = Math.max(opt1, opt2);
        }
    }
}