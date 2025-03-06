/*Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

The puzzle board has some patterns formed with boxes in it, 
the patterns may be repeated. The patterns are formed with boxes (1's) only, 
that are connected horizontally and vertically but not diagonally.

Pranav wants to find out the number of unique patterns in the board.

You are given the board in the form of a grid M*N, filled wth 0's and 1's.
Your task is to help Pranav to find the number of unique patterns in 
the puzzle board.

Input Format:
-------------
Line-1: Two integers M and N, the number of rows and columns in the grid-land.
Next M lines: contains N space-separated integers [0, 1].

Output Format:
--------------
Print an integer, the number of unique patterns in the puzzle board.


Sample Input-1:
---------------
5 5
0 1 0 1 1
1 1 1 0 1
0 1 0 1 0
1 0 1 1 1
1 1 0 1 0

Sample Output-1:
----------------
3

Explanation-1:
------------
The unique patterns are as follows:
  1			1 1	    1 
1 1 1		  1 ,	1 1
  1	   ,	
   
   
Sample Input-2:
---------------
6 6
1 1 0 0 1 1
1 0 1 1 0 1
0 1 0 1 0 0
1 1 0 0 0 1
0 0 1 0 1 1
1 1 0 1 0 0

Sample Output-2:
----------------
5

Explanation-2:
------------
The unique patterns are as follows:
1 1		1 1		    1		1 1	,	1
1   ,     1 ,	    1 1 ,		

 */
import java.util.*;

public class DistinctIslands{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt();
        int arr[][] =  new int[n][m];
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m ; j++){
                arr[i][j] = cin.nextInt();
            }
        }
        System.out.println(findDistinct(n,m,arr));
        cin.close();
    }
    static int findDistinct(int n, int m , int arr[][]){
        
        Set<String> set = new HashSet<>();
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m ; j++){
                if(arr[i][j]==1){
                    set.add(findPattern(arr,i,j));
                }
            }
        }
        
        return set.size();
    }
    static String findPattern(int arr[][], int i, int j){
        StringBuilder pattern = new StringBuilder();
        Queue<int[]> q = new ArrayDeque<>();
        int dirs [][] = new int[][]{
            {0,1},
            {0,-1},
            {1,0},
            {-1,0}
        };
        q.add(new int[]{i,j,0,0});
        while(!q.isEmpty()){
            // System.out.println("In while");
            int temp[] = q.poll();
            int r = temp[0];
            int c = temp[1];
            int relr = temp[2];
            int relc = temp[3];
            arr[r][c] = 0;
            pattern.append("("+relr+","+relc+")");
            for(int d[] : dirs){
                if(r+d[0]<arr.length && r+d[0]>=0 && c+d[1]>=0 && c+d[1]<arr[0].length && arr[r+d[0]][c+d[1]]==1 ){
                    q.add(new int[]{r+d[0],c+d[1],relr+d[0],relc+d[1]});
                }
            }
        }
        
        return pattern.toString();
    }
}