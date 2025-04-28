/*Amogh is an Antiquarian, The person who collects antiques.
He found a rear keyboard which has following keys,
Keys are 'N', 'S', 'C' and 'P'

1st Key - 'N': Print one character 'N' on Console.
2nd Key - 'S': Select the whole Console.
3rd Key - 'C': Copy selected content to buffer.
4th Key - 'P': Print the buffer on Console, and append it after what has 
already been printed.

Now, your task is to find out maximum numbers of 'N's you can print
after K keystrokes . 

Input Format:
-------------
An integer K

Output Format:
--------------
Print an integer, maximum numbers of 'N's you can print.


Sample Input-1:
-------------------
3

Sample Output-1:
-------------------- 
3

Explanation: 
---------------
We can print at most get 3 N's on console by pressing following key sequence:
N, N, N



Sample Input-2:
-------------------
7

Sample Output-2:
---------------------
9

Explanation: 
---------------
We can print at most get 9 N's on console by pressing following key sequence:
N, N, N, S, C, P, P
 */
import java.util.*;

public class MaxN_Third{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int k = cin.nextInt();
        int dp [] = new int[k+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        System.out.println(maxNPrints(k,dp));
        cin.close();
    }
    static int maxNPrints(int k, int dp[]){
        if(k<=0){
            return 0;
        }
        if(dp[k]!=-1){
            return dp[k];
        }

        // first keep pressing k
        int count = 1 + maxNPrints(k-1, dp);

        // ctrl + A, ctrl + C and ctrl + P
        // _i_ __ __ __ __ _k_
        // i -> (No of Max 'N' characters till 'i')
        // k -> (decide to paste at this position) 
        // _i_ _A_ _C_ __ __ _k_ (k - i = 5)
        // we have remaining 5 positions and to paste 'N' char to console we need to perform:
        // ctrl+A,ctrl+C = costs 2 ops
        // so remaining positions (including k) : 5-2 = 3
        // we can paste 3 times(k-i-2)
        // total 'N' on console: (k-i-2)*dp[i] + dp[i]
        // therfore , (k-i+1)*dp[i]
        for(int i = 1; i < k-2 ; i++){
            int totalN = maxNPrints(i, dp)*(k-i-2+1);
            count = Math.max(count,totalN);
        }
        dp[k] = count;
        return count;

    }
}