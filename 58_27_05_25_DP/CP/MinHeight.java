/*A Builder builds a wall in a strange way.
The wall has to built with a width of W.
And there are N building-blocks are available with some width and height.

The wall is built as follows:
	- Pick the first few buiding-blocks and place them in the available 
	width of the wall.
	- Once no more building block in the order can't be kept in the available 
	width of the wall, place a concrete rack on the highest building-block 
	among the row of building blocks.
	- Construct the rest of the wall by repeating above two steps until
	all the blocks used.

Your task is to find the minimum possible height of the wall built 
with width W, after using all N building blocks.


Input Format:
-------------
Line-1: Two space separaed integers, N and W.
Next N lines: Two space separaed integers, width and height of the brick.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
7 5
1 2
2 2
2 3
2 3
1 4
3 4
4 2

Sample Output-1:
----------------
11


Sample Input-2:
---------------
5 3
1 1
2 2
1 2
2 3
3 2

Sample Output-2:
----------------
7
 */
import java.util.Arrays;
import java.util.Scanner;

public class MinHeight{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt();
        int w = cin.nextInt();
        int arr[][] = new int[m][2];
        for(int i = 0; i < m; i++){
            arr[i][0] = cin.nextInt();
            arr[i][1] = cin.nextInt();
        }
        System.out.println(find(arr,m,w));
        cin.close();
    }
    
    static int find(int arr[][], int n, int w){
      int memo[][] = new int[n][w+1];
      for(int i = 0; i < n; i++){
          Arrays.fill(memo[i],-1);
      }
      return recurMemo(0,arr,memo,w,w,0);
        
    }
    static int recurMemo(int i, int arr[][], int memo[][],int remW ,int maxW, int maxH){
        if(i>=arr.length){
            return maxH;
        }
        
        if(memo[i][remW]!=-1){
            return memo[i][remW];
        }
        // next row
        int option1 = maxH + recurMemo(i+1,arr,memo,maxW-arr[i][0],maxW,arr[i][1]);
        int option2 = Integer.MAX_VALUE;
        if(remW>=arr[i][0]){
            int m = Math.max(arr[i][1],maxH);
            option2 = recurMemo(i+1,arr,memo,remW-arr[i][0],maxW,m);
        }
        memo[i][remW] = Math.min(option1,option2);
        return memo[i][remW];
    }

}