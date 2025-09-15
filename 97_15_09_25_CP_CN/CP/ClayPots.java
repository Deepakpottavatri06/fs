/*Hanu is a potter, he has prepared N Clay-Pots, put them in a line.
He decided to paint the clay-pots with k colors.
He has to paint all the clay-pots such that,
no more than two adjacent clay-pots have the same color.

Your task is to help Hanu, to find the total number of ways,
he can paint the clay-pots.

Input Format:
-------------
Two integers N and K, number of clay-pots, and number of colors.

Output Format:
--------------
Print an integer, Number of ways.


Sample Input:
---------------
3 2

Sample Output:
----------------
6

Explanation:
------------
pots		pot-1	pot-2	pot-3
----- 	----- 	----- 	-----
1 			c1 		c1 		c2
2 			c1 		c2 		c1
3 			c1 		c2 		c2
4 			c2 		c1 		c1
5 			c2 		c1 		c2
6 			c2 		c2 		c1

 */
import java.util.*;

public class ClayPots{
    static int res = 0;
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int k = cin.nextInt();
        System.out.println(find(n,k));
        cin.close();
    }
    static int find(int n, int k){
        // recur(1,n,k,false,k);
        // return res;
        int result = 0;
        int same[] = new int[n];
        int diff[] = new int[n];
        same[0] = 0;
        diff[0] = k;
        for(int i = 1; i < n; ++i){
            same[i] = diff[i-1];
            diff[i] = (same[i-1]+diff[i-1])*(k-1);
        }
        return same[n-1] + diff[n-1];

    }
    static void recur(int ind, int n, int k, boolean same, int curr){
        if(ind>=n){
            res += curr;
            return;
        }
        if(same){
            recur(ind+1,n,k,false,curr*(k-1));
        }
        else{
            recur(ind+1,n,k,false,curr*(k-1));
            recur(ind+1,n,k,true,curr);
        }
    }
}