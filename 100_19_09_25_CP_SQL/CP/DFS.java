/*Given an 2D character array, Letters[][], of size r*c.
You have to construct the word W, using the given array.

Rules to construct the word are as follows:
	- All the letters of the word W, should be adjacent to each other 
	in the array Letters(either horizontal or vertical).
	- You can use each charcater in Letters[][] only once.

If you are able to construct the word W, return 'true'
Otherwise 'false'.

Input Format:
-------------
Line-1 -> two integers R and C, array size.
R lines -> C space separated characters.
Last line -> a string, word W

Output Format:
--------------
print the boolean result.


Sample Input-1:
---------------
3 3
a b c
d e f
g h i
bad

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 3
a b c
d e f
g h i
ace

Sample Output-2:
----------------
false


Sample Input-3:
---------------
3 3
a b c
d e f
g h i
add

Sample Output-3:
----------------
false
 */

 import java.util.*;

public class DFS{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        int m = cin.nextInt(), n = cin.nextInt();
        char arr[][] = new char[m][n];
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = cin.next().charAt(0);
            }
        }
        char word[] = cin.next().toCharArray();
        System.out.println(find(word,m,n,arr));
        cin.close();
    }
    static boolean find(char word [], int m, int n, char [][] arr){
        for(int i = 0; i < m ; i++){
            for(int j = 0; j < n; j++){
                if(word[0]==arr[i][j] && dfs(word,arr,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }
    static boolean dfs(char word [], char arr[][], int i, int j, int ind){
        if(ind>=word.length){
            return true;
        }
        if(i>=arr.length || j>=arr[0].length || i<0 || j<0 || arr[i][j]!=word[ind]){
            return false;
        }
        char temp = arr[i][j];
        arr[i][j] = '0';
        boolean ret = dfs(word,arr,i+1,j,ind+1) || 
                        dfs(word,arr,i-1,j,ind+1) || 
                        dfs(word,arr,i,j+1,ind+1) ||
                        dfs(word,arr,i,j-1,ind+1);
        arr[i][j] = temp;
        return ret;
        
    }
}