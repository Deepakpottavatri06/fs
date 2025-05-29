import java.util.*;

public class MinAscii{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String s1 = cin.next();
        String s2 = cin.next();
        System.out.println(find(s1,s2));
        cin.close();
    }
    static int find(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int memo[][] = new int[m][n];
        for(int i[]: memo){
            Arrays.fill(i,-1);
        }
        return memo(s1,s2,0,0,memo);
    }
    static int memo(String s1, String s2, int i , int j, int dp[][]){
        if(i>=s1.length()){
            int sum = 0;
            for(int j2 = j; j2 < s2.length(); j2++){
                sum+= (int)(s2.charAt(j2));
            }
            return sum;
        }
        if(j>=s2.length()){
            int sum = 0;
            for(int j2 = i; j2 < s1.length(); j2++){
                sum+= (int)(s1.charAt(j2));
            }
            return sum;
        }
        
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        if(s1.charAt(i)==s2.charAt(j)){
            // System.out.println("Found match : "+s1.charAt(i)+" "+s2.charAt(j));
            return dp[i][j] = memo(s1,s2,i+1,j+1,dp);
        }
        // System.out.println(" char values :"+(int)(s1.charAt(i))+" "+(int)(s2.charAt(j)) );
        // System.out.println(s1.charAt(i)+" "+s2.charAt(j)+" for i : "+i+" for j :"+j);
        int opt1 = (int)(s1.charAt(i)) + memo(s1,s2,i+1,j,dp);
        int opt2 = (int)(s2.charAt(j)) + memo(s1,s2,i,j+1,dp);
        int opt3 = (int)(s2.charAt(j))  +(int)(s1.charAt(i)) + memo(s1,s2,i+1,j+1,dp);
        
        return dp[i][j] = Math.min(opt1,Math.min(opt2,opt3));
    }
}