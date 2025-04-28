/*You are a stealthy archaeologist exploring a circular ring of ancient tombs 
located deep within a jungle. Each tomb holds a certain number of precious 
artifacts. 
However, these tombs are protected by an ancient magical curse: 
if you disturb two adjacent tombs during the same night, the entire ring 
activates a trap that seals you in forever.

The tombs are arranged in a perfect circle, meaning the first tomb is adjacent 
to the last. You must plan your artifact retrieval carefully to maximize the 
number of artifacts collected in a single night without triggering the curse.

Given an integer array  artifacts  representing the number of artifacts in each 
tomb, return the   maximum   number of artifacts you can collect without 
disturbing any two adjacent tombs on the same night.

Example 1:  
Input:
2 4 3
Output:  
4   

Explanation: You cannot loot tomb 1 (artifacts = 2) and tomb 3 (artifacts = 3), 
as they are adjacent in a circular setup.


Example 2:  
Input:
1 2 3 1
Output:  
4

Explanation: You can loot tomb 1 (1 artifact) and tomb 3 (3 artifacts) without 
breaking the ancient rule.  
Total = 1 + 3 = 4 artifacts.


Example 3:  
Input:
1 2 3
Output:  
3 

Constraints:  
-  1 <= artifacts.length <= 100 
-  0 <= artifacts[i] <= 1000 
 */
import java.util.*;

public class HouseRobberTwo_One{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int arr [] = new int[inp.length];
        for(int i = 0; i < inp.length ; i++){
            arr[i] = Integer.parseInt(inp[i]);
        }
        System.out.println(find(arr,inp.length));
        cin.close();
    }
    static int find(int [] arr, int n){
        int dp [] = new int[n];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        int d1 = recur(Arrays.copyOfRange(arr,0,n),1,dp);
        Arrays.fill(dp,-1);
        dp[0] = 0;
        int d2 = recur(Arrays.copyOfRange(arr,1,n+1),1,dp);
        return Math.max(d1,d2);
    }
    static int recur(int arr [], int ind, int dp[]){
        
        if((ind)>=arr.length){
            // System.out.println("ind : "+ind);
            return 0;
        }
        if(dp[ind]!=-1){
            return dp[ind];
        }
        return dp[ind] = Math.max(
            arr[ind-1] + recur(arr,ind+2,dp),
            recur(arr,ind+1,dp));
    }
}