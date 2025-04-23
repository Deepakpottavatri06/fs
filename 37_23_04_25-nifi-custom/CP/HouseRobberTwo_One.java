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