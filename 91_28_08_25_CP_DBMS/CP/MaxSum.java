/*You have given a bulb grid, where the bulb which is turned ON is indicated 
with 1, and turned OFF is indicated with 0.

You are allowed to perform an operation:
    - Select a row/column in the buld grid, and toggle the bulbs,
    the bulbs which are turned ON will be truned OFF and the bulbs which are 
    turned OFF will be turned ON.

Your task is to find the highest possible sum of all the binary equivalents 
of each row in the bulb grid, after performing the above operation any 
number of times on the bulb grid.


Input Format:
-------------
Line-1: Two space separated integers, M and N
Next M lines: N space separated integers, grid[][]

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
3 5
0 1 1 1 1 
1 0 1 1 1 
0 0 0 0 1 

Sample Output-1:
----------------
78

Explanation:
------------
Given grid is 
0 1 1 1 1
1 0 1 1 1
0 0 0 0 1

Perform operation on row-0 and row-2
1 0 0 0 0
1 0 1 1 1
1 1 1 1 0

Perform operation on col-1 and col-4
1 1 0 0 1 = 25
1 1 1 1 0 = 30
1 0 1 1 1 = 23
So, now the total value of Binary Equivalent is 78


Sample Input-2:
---------------
2 3
0 1 0
0 0 1

Sample Output-2:
----------------
11

 */
import java.util.*;

public class MaxSum{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int m = cin.nextInt();
            int n = cin.nextInt();
            int arr[][] = new int[m][n];
            for(int i =0; i<m; i++){
                for(int j = 0; j < n ; j++){
                    arr[i][j] = cin.nextInt();
                }
            }
            System.out.println(find(arr,m,n));
        }
    }
    static int find(int arr[][], int m, int n){
        
        for(int i = 0; i < m; i++){
            if(arr[i][0]==1) continue;
            for(int j = 0; j < n ; j++){
                arr[i][j] = 1 - arr[i][j];
            }
        }
        for(int j = 1; j < n; j++){
            int countOnes = 0;
            for(int i = 0; i < m ; i++){
                if(arr[i][j]==1) countOnes++;
            }
            int countZeroes = m - countOnes;
            if(countOnes<countZeroes){
                for(int i = 0; i < m ; i++){
                    arr[i][j] = 1 - arr[i][j];
                }
            }
        }
        
        int res = 0;
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n ; j++){
                str.append(arr[i][j]);
            }
            res += Integer.parseInt(str.toString(),2);
            str.setLength(0);
        }
        
        return res;
    }
}