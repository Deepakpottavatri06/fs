/*You are a robot explorer navigating a vast digital maze stored as a string of digits. 
Each digit or pair of digits on the path represents a movement instruction, which 
translates to a specific direction using the following map:

"1" → Move 'A'

"2" → Move 'B'

...

"26" → Move 'Z'

However, the maze has tricky encoding rules. Sometimes a single digit can be 
read alone, and sometimes two digits together form a valid move — but not every 
combination is valid. For example, "05" is invalid, while "5" is fine. 
Your robot can only navigate using valid instruction steps, and you must find 
how many unique navigation sequences the robot can follow to reach the end of 
the maze.

Given the string s of digits, determine the total number of distinct ways the 
robot can interpret and follow the path from start to end without making an 
invalid move.

If no valid navigation is possible, return 0.


Input Format:
-------------
A string s.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
123

Sample Output-1:
----------------
3

Explanation:
------------
123 can be converted as: ABC, LC, AW


Sample Input-2:
---------------
326

Sample Output-2:
----------------
2

Explanation:
------------
326 can be converted as: CBF, CZ
 */
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