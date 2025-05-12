/*Pranav has a puzzle board filled with square boxes in the form of a grid.
Some cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

Pranav wants to find out the number of empty spaces which are completely 
surrounded by the square boxes (left, right, top, bottom) in the board.

You are given the board in the form of a grid M*N, filled wth 0's and 1's.
Your task is to help Pranav to find the number of empty groups surrounded by
the boxes in the puzzle board.

Input Format:
-------------
Line-1: Two integers M and N, the number of rows and columns in the board.
Next M lines: contains N space-separated either 0 or 1.

Output Format:
--------------
Print an integer, the number of empty spaces in the puzzle board.


Sample Input-1:
---------------
6 7
1 1 1 1 0 0 1
1 0 0 0 1 1 0
1 0 0 0 1 1 0
0 1 1 1 0 1 0
1 1 1 0 0 1 1
1 1 1 1 1 1 1

Sample Output-1:
----------------
2

Explanation:
------------
The 2 empty groups are as follows:
1st group starts at cell(1,1), 2nd group starts at cell(3,4).
The groups which are starts at cell(0,4), cell(1,6) and cell(3,0)
are not valid empty groups, because they are not completely surrounded by boxes.


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
1

Explanation:
------------
The only empty group starts at cell(1,1) is surrounded by boxes.



 */
import java.util.*;

public class FindBoxes_One{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int n = cin.nextInt();
        int arr [][] = new int[m][n];
        for(int i = 0; i < m ; i++){
            for (int j = 0; j < n ; j++ ){
                arr[i][j] = cin.nextInt();
            } 
        }
        System.out.println(findboxes(arr,m,n));
        cin.close();
    }
    static int findboxes(int [][] arr, int m , int n){
        int count = 0;
        for(int i = 1; i < m-1 ; i++){
            for(int j = 1; j < n-1 ; j++){
                if(arr[i][j]==0){
                    count += bfs(arr,i,j);
                }
            }
        }
        return count;
    }
    static int bfs(int arr[][], int r , int c){
        int m = arr.length;
        int n = arr[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r,c});
        
        int dir [][] = new int[][]{
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };
        int res = 1;
        while(!q.isEmpty()){
            int temp[] = q.poll();
            arr[temp[0]][temp[1]] = -1;
            if(temp[0]==0 || temp[0]==m-1 || temp[1]==0 || temp[1]==n-1){
                res = 0;
            }
            for(int d[] : dir){
                int newR = temp[0] + d[0];
                int newC = temp[1] + d[1];
                if(newR>=0 && newR < m && newC>=0 && newC<n && arr[newR][newC]==0){
                    q.add(new int[]{newR,newC});
                }
            }
        }
        
        return res;
    }
}