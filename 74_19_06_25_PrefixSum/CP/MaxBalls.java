/*Bablu is playing with Magnets and Iron balls.
Bablu has given a flat surface of m*n size, 
each position contains either empty '0', an Iron ball 'B' 
or Wooden Block 'W' (The wooden block is an anti-magnetic agent), 

Your task is to help Bablu to find the maximum number of 
Iron Balls he can attract by using a Magnet.

The Magnet attracts all the iron balls in the same row and column 
from their positions until the wooden block.
since the wooden block is an anti-magnetic block.

Note: You can only put the magnet in an empty position.

Input Format:
-------------
Line-1: Two integers m and n, size of the surface.
Next 'm' lines:  'n' space-separated characters only ('0', 'B', 'W').

Output Format
--------------
Print an integer, the maximum number of Iron Balls can be attracted by using a Magnet


Sample Input-1:
----------------
3 4
0 B 0 0 
B 0 W B
0 B 0 0

Sample Output:
--------------
3 

Explanation:
------------
For the given grid,
	0 B 0 0 
	B 0 W B
	0 B 0 0
Placing a Magnet at (1,1) attacts 3 iron balls. 

 */
import java.util.*;

public class MaxBalls{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int n = cin.nextInt();
        
        char arr [][] = new char[m][n];
        for(int i=0; i < m ; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = cin.next().charAt(0);
            }
        }
        // System.out.println(Arrays.deepToString(arr));
        System.out.println(find(arr,m,n));
        cin.close();
    }
    static int find(char arr[][], int m, int n){
        int dp[][] = new int[m][n];
        // left to right
        
        for(int i = 0; i < m; i++){
            int countBalls = 0; 
            for(int j = 0; j < n ; j++){
                if(arr[i][j]=='W'){
                    countBalls= 0;
                }
                else if(arr[i][j]=='B'){
                    countBalls++;
                }
                else if(arr[i][j]=='0'){
                    dp[i][j] = countBalls;
                }
            }
        }
        
        // System.out.println("left to right"+Arrays.deepToString(dp));
        // top to bottom
        for(int j = 0; j < n ; j++){
            int countBalls = 0; 
            for(int i = 0; i < m; i++){
                if(arr[i][j]=='W'){
                    countBalls= 0;
                }
                else if(arr[i][j]=='B'){
                    countBalls++;
                }
                else if(arr[i][j]=='0'){
                    dp[i][j] += countBalls;
                }
            }
        }
        // System.out.println("top to bottom"+Arrays.deepToString(dp));
        // bottom to top
        for(int j = n-1; j >=0 ; j--){
            int countBalls = 0; 
            for(int i = m-1; i >= 0 ; i--){
                if(arr[i][j]=='W'){
                    countBalls= 0;
                }
                else if(arr[i][j]=='B'){
                    countBalls++;
                }
                else if(arr[i][j]=='0'){
                    dp[i][j] += countBalls;
                }
            }
        }
        //  System.out.println("bottom to top"+Arrays.deepToString(dp));
        int max = 0;
        for(int i = 0; i < m ; i++){
            int countBalls = 0; 
            for(int j = n-1; j >=0 ; j--){
                if(arr[i][j]=='W'){
                    countBalls= 0;
                }
                else if(arr[i][j]=='B'){
                    countBalls++;
                }
                else if(arr[i][j]=='0'){
                    dp[i][j] += countBalls;
                }
                max = Math.max(dp[i][j],max);
            }
        }
        
        // System.out.println(Arrays.deepToString(dp));
        
        return max;
    }
}