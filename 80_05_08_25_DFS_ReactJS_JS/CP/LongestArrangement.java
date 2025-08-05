/*A merchant has two types of idols, gold and silver.
He has arranged the idols in the form of m*n grid, 
	- the gold idols are represented as 1's 
	- the silver idols are represented as 0's.

Your task is to find the longest consecutive arrangement of gold idols, 
The arrangement can be either horizontal, vertical, diagonal or 
antidiagonal, but not the combination of these.


Input Format:
-------------
Line-1: Two space separated integers m and n, grid size.
Next m lines : n space separated integers, arrangement of idols.

Output Format:
--------------
Print an integer, longest arranement of gold idols.


Sample Input:
---------------
4 5
1 0 1 1 1
0 1 0 1 0
1 0 1 0 1
1 1 0 1 1

Sample Output:
----------------
4
 */
import java.util.*;

public class LongestArrangement {
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int n = cin.nextInt();
        int grid[][] = new int[m][n];
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                grid[i][j] = cin.nextInt();
            }
        }
        System.out.println(find(grid,m,n));
        cin.close();
    }
    static int find(int grid[][], int m , int n){
        // Set<String> set = new HashSet<>();
        int res = 0;
        /*
        1 - horizontal
        2 - vertical
        3 - diag
        4 - anti diag
        */
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j]==1){
                    res = Math.max(dfs(i,j,grid,1),res);
                    res = Math.max(dfs(i,j,grid,2),res);
                    res = Math.max(dfs(i,j,grid,3),res);
                    res = Math.max(dfs(i,j,grid,4),res);
                }
            }
        }
        
        return res;
    }
    static int dfs(int i, int j, int grid[][],int dir){
        if(i>=0 && j>=0 && i<grid.length && j < grid[0].length && grid[i][j]==1){
            if(dir==1){
                return 1 + dfs(i,j+1,grid,dir);
            }
            else if(dir==2){
                return 1 + dfs(i+1,j,grid,dir);
            }
            else if(dir==3){
                return 1 + dfs(i+1,j+1,grid,dir);
            }
            return 1 + dfs(i+1,j-1,grid,dir);
        }
        return 0;
    }
}