/*A group of researchers is analyzing satellite imagery of agricultural fields, 
represented by a grid of land sections. Each section is marked either as 
fertile (1) or infertile (0). To efficiently plan crop planting, the researchers 
need to identify the largest rectangular area consisting exclusively of fertile 
land sections.

Given a binary grid representing the land (1 for fertile and 0 for infertile), 
your task is to compute the area of the largest rectangle consisting entirely 
of fertile land sections.

Input Format:
-------------
Line-1: Two integers rows(r) and columns(c) of grid.
Next r lines: c space separated integers, each row of the grid.

Output Format:
--------------
Print an integer, area of the largest rectangle consisting entirely of fertile land sections.

Example:
--------
input=
4 5
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

output=
6
 */
import java.util.*;

public class maxArea_Two{
    public static void main(String [] arg){
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int n = cin.nextInt();
        int arr [][] = new int[m][n];
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = cin.nextInt();
            }
        }
        System.out.println(findMax(arr,m,n));
        cin.close();
    }
    static int findMax(int arr[][], int m , int n){
        int maxArea = 0;
        int dp[][] = new int[m+1][n];
        for(int j = 0; j < n ; j++){
            for(int i = 1; i <= m ; i++ ){
                dp[i][j] = arr[i-1][j] + dp[i-1][j];
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        
        for(int i = 1; i <= m ; i++){
            for(int j = i ; j <= m; j++){
                int counter = 0;
                int maxCounter = 0;
                for(int k = 0; k < n ; k++){
                    if(dp[j][k]-dp[i-1][k] ==(j-i+1)){
                        counter++;
                        maxCounter = Math.max(counter,maxCounter);
                    }
                    else{
                        counter = 0;
                    }
                }
                maxArea = Math.max(maxArea,maxCounter*(j-i+1));
            }
        }
        
        return maxArea;
        
    }
}
