/*Arjun wants to build some homes in a land of size R*C.
He wanted to construct homes in rectangular shape.
The place which is remained will be used for gradening.
Accordingly he has prepared the plan and given as
an 2d array plan[][], where 1 indicates home, and 0 indicates garden area.

A home is set of cells with value 1 in rectangular shape.
He wants to findout all the homes in the plan and store their co-ordinates in 
the following order, coords[i] = [x1,y1,x2,y2], where (x1,y1) is the starting
co-ordinate (top left corner), and (x2,y2) is the ending co-ordinate 
(bottom right corner) of i-th home.

Your task is to help Arjun to find all the homes and return the coords[][] of 
all the homes from top left corner to bottom right corner.

NOTE: No two homes are adjacent to each other in 4 directions,
(left, right, top, bottom).

Input Format:
-------------
Line-1: Two integers R and C, size of the land.
Next R lines: C space separated integers, either 0 or 1
0- represents garden area land and 1- represents the home.

Output Format:
--------------
Print 2d array, the co-ordinates of all homes.


Sample Input-1:
---------------
2 3
1 0 0
0 1 1
 
Sample Output-1:
----------------
[0, 0, 0, 0][1, 1, 1, 2]


Sample Input-2:
---------------
4 4
1 1 0 1
0 0 0 0
1 1 0 1
1 1 0 1
 
Sample Output-2:
----------------
[0, 0, 0, 1][0, 3, 0, 3][2, 0, 3, 1][2, 3, 3, 3]

 */
import java.util.*;

public class HomeCoords{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int r = cin.nextInt();
            int c = cin.nextInt();
            int arr[][] = new int[r][c];
            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    arr[i][j] = cin.nextInt();
                }
            }
            findHome(arr,r,c);
            cin.close();
        }
    }
    static void findHome(int arr[][], int r, int c){
        List<List<Integer>> res = new ArrayList<>();
        int bottom[];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(arr[i][j]==1){
                    bottom = new int[]{i,j};
                    dfs(arr,i,j,bottom);
                    res.add(Arrays.asList(i,j,bottom[0],bottom[1]));
                }
            }
        }
        // System.out.println(res);
        for(int i = 0; i < res.size();i++){
            System.out.print(res.get(i));
        }
    }
    static void dfs(int arr[][], int i , int j, int bottom[]){
        if(i<0 || j < 0 || i==arr.length || j == arr[0].length || arr[i][j]!=1){
            return;
        }
        arr[i][j] = -1;
        if(i+j > bottom[0] + bottom[1]){
            bottom[0] = i;
            bottom[1] = j;
        }
        dfs(arr,i+1,j,bottom);
        dfs(arr,i-1,j,bottom);
        dfs(arr,i,j+1,bottom);
        dfs(arr,i,j-1,bottom);
    }
}