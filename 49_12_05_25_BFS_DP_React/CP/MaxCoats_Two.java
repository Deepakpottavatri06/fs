/*Siddu wants to get some rain coats before the rainy season begins. 
There are N rain coats in a store. He is provided an array price[], 
where price[i] represents the dollar price of the i-th rain coat. 

Siddu has D dollars to spend, and he wants to buy as many rain coats as 
he can, to give to his family and friends as gifts.

Your task is to assist Siddu in purchasing the most number of rain coats 
possible using D dollars.

Note: Siddu can purchase the rain coats in any order.

Input Format:
-------------
Line-1: Two space separated integers, N and D
Line-2: N space separated integers, prices of N rain coats.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
7 15
6 12 7 5 13 10 1

Sample Output-1:
----------------
3

Explanation:
------------
Siddu purchases Rain coats with price [1$, 5$, 7$] or [1$, 6$, 7$] or [1$, 5$, 6$].


Sample Input-2:
---------------
10 3
15 13 11 4 11 5 9 14 14 5

Sample Output-2:
----------------
0

Explanation:
------------
Siddu can't purchase any rain coat, because he has only 3$s with him.
 */
import java.util.*;

public class MaxCoats_Two{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int d = cin.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = cin.nextInt();
        }
        // System.out.println(findMax(arr,d,n));
        System.out.println(greedy(arr,d));
        cin.close();
    }
    static int greedy(int arr[], int d){
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0;i < arr.length ;i++ ){
            if(d<arr[i]){
                break;
            }
            d-=arr[i];
            count++;
        } 
        return count;
    }
    static int findMax(int arr[], int d, int n){
        int dp[][] = new int[n+1][d+1];
        for(int i = 0; i < n+1; i++){
            Arrays.fill(dp[i],-1);
        }
        return memo(arr,dp,d,0);
    }
    static int memo(int arr[], int dp[][], int d, int ind){
        if(ind>=arr.length){
            return 0;
        }
        if(dp[ind][d]!=-1){
            return dp[ind][d];
        }
        
        if(d<arr[ind]){
            dp[ind][d] = memo(arr,dp,d,ind+1);
        }
        else{
            dp[ind][d] = Math.max(1+memo(arr,dp,d-arr[ind],ind+1),memo(arr,dp,d,ind+1));
        }
        return dp[ind][d];
        
    }
}