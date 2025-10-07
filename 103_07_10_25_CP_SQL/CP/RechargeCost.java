/*A warehouse is organized as a grid of cells (N x M), where each cell represents 
a location with a certain energy cost for the robot to move through.
The robot starts at the top-left corner (0, 0) and must reach the bottom-right 
corner (N-1, M-1).

The robot can move only:
➡️ Right (→)
⬇️ Down (↓)

However:
The robot’s battery can only store up to E energy units before it must recharge.
A recharge adds a penalty cost R to the total energy consumption.
The robot can choose when to recharge (after any move) if its next move would 
exceed the battery limit.

Find the minimum total energy cost (including recharge penalties) required for 
the robot to reach its destination.


Input Format:
-------------
N M E R
<energy grid of size N x M>

Output Format:
--------------
Minimum total energy cost to reach destination


Sample Input:
-------------
3 3 6 5
1 3 2
4 2 1
3 1 2

Sample Output:
-------------
14


Explanation:
------------
Let’s examine different paths and their costs.

Path 1: Right → Right → Down → Down
Cells: (0,0) → (0,1) → (0,2) → (1,2) → (2,2)
Cost path: 1 + 3 + 2 + 1 + 2 = 9
Battery limit = 6 → must recharge once after crossing (0,2).
Total cost = 9 + 5 = 14


Path 2: Down → Down → Right → Right
Cells: (0,0) → (1,0) → (2,0) → (2,1) → (2,2)
Cost: 1 + 4 + 3 + 1 + 2 = 11
Battery limit = 6 → need recharge after (1,0).
Total = 11 + 5 = 16

There might be more paths, The minimum one is 14


 */
import java.util.*;

public class RechargeCost{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            int m = cin.nextInt();
            int e = cin.nextInt();
            int r = cin.nextInt();
            int grid[][] = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0;j < m; j++){
                    grid[i][j] = cin.nextInt();
                }
            }
            System.out.println(find(grid,n,m,e,r));
            cin.close();
        }
    }
    static int find(int grid[][], int n, int m, int e, int r){
        int minCost [] = new int[]{Integer.MAX_VALUE};
        dfs(grid,e,e,r,0,0,0,minCost);
        return minCost[0];
    }
    static void dfs(int grid[][], int roboE, int e, int r, int i, int j,int currCost, int minCost[]){
        if(i<0 || j <0 || i>= grid.length || j>=grid[0].length){
            return;
        }
        if(roboE<grid[i][j]){
            currCost += r;
            roboE = e;
        }
        currCost += grid[i][j];
        roboE -= grid[i][j];
        if(i==grid.length-1 && j==grid[0].length-1){
            minCost[0] = Math.min(minCost[0],currCost);
            return;
        }
        dfs(grid,roboE,e,r,i,j+1,currCost,minCost);
        dfs(grid,roboE,e,r,i+1,j,currCost,minCost);
        
    }
}