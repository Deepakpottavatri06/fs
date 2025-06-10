/*Luke likes to construct and play with arrays. His dad gave him an array M of 
length N and assigned him the following tasks to be done in order:
 - reate continuous groups of size i from the array M (where i goes from 1 to N).
 - For each group of size i, find the minimum value.
 - Among all the minimums from that step, select the maximum.
 - Add this selected value as the i-th element of a new array P.
 - Repeat until i = N.

Note: Use 1-based indexing for array P in logic.

Input Format:
-------------
input1: Integer N – length of array M
input2: Integer array M of length N

Output Format:
--------------
Return the array P as output.

Sample Input:
-------------
3
1 2 3

Sample Output:
--------------
3 2 1


Sample Input: 
-------------
4
20 10 30 40

Sample Output: 
--------------
40 30 10 10

 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayP{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int arr [] = new int[n];
        for(int i = 0; i < n ; i++){
            arr[i] = cin.nextInt();
        }
        // System.out.println(find(arr,n));
        find(arr,n);
        cin.close();
    }
    static List<Integer> find(int arr[], int n){
        int dp[][] = new int[n][n];
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n ; i++){
            int max_value = 0;
            for(int j = i; j < n ; j++){
                if(i==0){
                    dp[i][j] = arr[j];
                    max_value = Math.max(max_value,dp[i][j]);
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1]);
                    max_value = Math.max(max_value,dp[i][j]);
                }
            }
            res.add(max_value);
        }
        
        for(int i : res){
            System.out.print(i+" ");
        }
        return res;
    }
}