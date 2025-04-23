
import java.util.*;

public class DistinctWays_Third{
    
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        int dp[] = new int[inp.length()+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        System.out.println(find(inp,0,dp));
        cin.close();
    }
    static int find(String inp, int ind, int dp[]){
        if(ind>=inp.length()){
            return 1;
        }
        int possible = 0;
        if(dp[ind+1]!=-1){
            // System.out.println("Cached");
            return dp[ind+1];
        }
        if(inp.charAt(ind)=='0'){
            return 0;
        }
        if(
        (ind<inp.length()-1) && 
        (inp.charAt(ind)=='1' || 
        (inp.charAt(ind)=='2' && (inp.charAt(ind+1)<'7')))
        ){
            possible += find(inp,ind+2,dp);
        }
        possible +=find(inp,ind+1,dp);
        return dp[ind+1] = possible;
    }
}