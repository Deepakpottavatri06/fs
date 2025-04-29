/*You are participating in a futuristic space exploration mission where you must 
navigate through a series of planets aligned in a straight line.
The planets are numbered from 0 to n-1, and you start your journey on planet 0.

You are given a 0-indexed array planets, where each element planets[i] represents 
the maximum number of planets you can move forward from planet i. In simpler terms, 
standing on planet i, you can move to any planet from i+1 to i+planets[i], 
as long as you don't exceed the last planet.

Your goal is to reach the final planet (planet n-1) in the fewest number of 
moves possible.

It is guaranteed that a path to the final planet always exists.

Return the minimum number of moves needed to reach planet n-1.

Example 1
----------
Input:
2 3 1 0 4
Output:
2

Explanation:
- Move from planet 0 to planet 1.
- Move from planet 1 to planet 4 (last planet).
 */
import java.util.*;

public class JumpGame_One{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int arr [] = new int[inp.length];
        for(int i = 0; i < inp.length ; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        System.out.println(findMinMoves(arr));
        cin.close();
    }
    static int findMinMoves(int arr []){
        int minMoves [] = new int[]{Integer.MAX_VALUE};
        int dp[] = new int[arr.length+1];
        Arrays.fill(dp,-1);
        // dfs(arr,0,minMoves,0);
        // return minMoves[0];
        return dfsMemo(arr,0,dp);
    }
    static int dfsMemo(int arr[], int ind, int dp[]){
        if(ind==arr.length-1){
            return 0 ;
        }
        if(dp[ind]!=-1){
            return dp[ind];
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= arr[ind]; i++){
            if(i+ind>arr.length-1) continue;
            int val = dfsMemo(arr,ind+i,dp);
            if(val!=Integer.MAX_VALUE){
                min = Math.min(1+val,min);
            }
        }
        return dp[ind] = min;
        
    }
     static void dfs(int arr[], int ind, int minMoves[], int moves){
        if(ind==arr.length-1){
            minMoves[0] = Math.min(minMoves[0],moves);
            return ;
        }
        if(ind>=arr.length){
            return;
        }
        
        for(int i = 1; i <= arr[ind]; i++){
            dfs(arr,ind+i,minMoves,moves+1);
        }
        
    }
}
