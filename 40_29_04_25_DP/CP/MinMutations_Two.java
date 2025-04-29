import java.util.*;

public class MinMutations_Two{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        String target = cin.next();
        int dp [][] = new int[inp.length()+1][target.length()+1];
        for(int i = 0; i < inp.length()+1; i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(findMin(inp,target,0,0,dp));
        cin.close();
    }
    static int findMin(String inp, String target, int i, int j, int dp[][]){
        if(i==inp.length()){
            return target.length()-j;
        }
        if(j==target.length()){
            return inp.length() - i;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        // System.out.println("Values : "+inp.charAt(i) + " "+target.charAt(j));
        if(inp.charAt(i)==target.charAt(j)){
            return dp[i][j] =  findMin(inp,target,i+1,j+1,dp);
        }
        
            // System.out.println("Added one");
        int insert = findMin(inp,target,i,j+1,dp); // insert
        int delete = findMin(inp,target,i+1,j,dp); // delete
        int replace = findMin(inp,target,i+1,j+1,dp); // replace
        int val =  1 + Math.min(Math.min(insert,delete),replace);
        
        return dp[i][j] = val;
    }
}