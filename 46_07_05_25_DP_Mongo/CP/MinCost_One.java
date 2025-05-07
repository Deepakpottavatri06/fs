/*In the present situation, most of the movies releasing in OTTs.
The Showtime OTT in US, introduced a new offer for the customers, 
they can purchase either 1-day, 7-day, or 30-day subscription,
and the cost is as follows price[0], price[1], price[2].

The Subscription allows you to watch as many movies as you want with in subscribed days. 
For example:
If you purchased, a 7-day subscription on day 5, then you can watch
the movies for 7 days: day 5, 6, 7, 8, 9, 10 and 11.

Your task is to find out the minimum cost, you spend to watch the movies
in the given list of days .

NOTE: Days are numbered from 1, 2, 3, ...365, in sorted order.

Input Format:
-------------
Line 1: Space separated integer days[], list of days.
Line 2: 3 space separated integer price[], cost of subscription.

Output Format:
--------------
Print an integer, minimum cost. 


Sample Input-1:
---------------
1 4 6 7 8 20
2 7 15

Sample Output-1:
----------------
11

Explanation:
------------
For example, here is a way to buy subscription, to watch the movies in given days:
On day 1, buy a 1-day subscription for price[0] = $2, which cover day 1.
On day 4, buy a 7-day subscription for price[1] = $7, which cover days 4, 5, ..., 10.
On day 20, buy a 1-day subscription for price[0] = $2, which cover day 20.
In total you spent $11.


Sample Input-2:
---------------
1 2 3 4 5 6 7 8 9 10 30 31
2 7 15

Sample Output-2:
----------------
17

Explanation:
------------
For example, here is a way to buy subscription, to watch the movies in given days:
On day 1, buy a 30-day subscription for price[2] = $15, which cover days 1, 2, 3,....,30.
On day 31, buy a 1-day subscription for price[0] = $2, which cover day 31.
In total you spent $17.


 */
import java.util.*;

public class MinCost_One{
    public static void main(String [] ae){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int prices [] = new int[3];
        prices[0] = cin.nextInt();
        prices[1] = cin.nextInt();
        prices[2] = cin.nextInt();
        int arr[] = new int[inp.length];
        for(int i = 0; i < inp.length ; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        Arrays.sort(arr);
        int dp[][] = new int[arr.length+1][366];
        for(int i = 0; i < arr.length + 1; i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(findMin(arr,0,0,prices,dp));
        cin.close();
    }
    static int findMin(int arr[], int ind, int daysCovered, int prices[],int dp[][]){
        if(ind>=arr.length){
            return 0;
        }
        if(daysCovered>arr[ind]){
            return dp[ind][daysCovered] =  findMin(arr,ind+1,daysCovered,prices,dp);
        }
        if(dp[ind][daysCovered]!=-1){
            return dp[ind][daysCovered];
        }
        int oneDay  = prices[0] + findMin(arr, ind + 1 ,arr[ind]+1,prices,dp);
        int sevenDays = prices[1] + findMin(arr, ind + 1, arr[ind]+7,prices,dp);
        int thirtyDays = prices[2] +findMin(arr, ind + 1, arr[ind]+30,prices,dp); 
        
         dp[ind][daysCovered] = Math.min(oneDay,Math.min(sevenDays,thirtyDays));
         return dp[ind][daysCovered];
    }
}