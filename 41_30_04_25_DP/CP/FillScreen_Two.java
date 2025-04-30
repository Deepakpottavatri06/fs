// package CP;
/*A digital advertising company is setting up electronic billboards across the city. 
Each billboard screen has dimensions of rows x cols, indicating how many lines (rows) 
and how many characters per line (cols) the screen can display. The company has 
prepared an advertising slogan consisting of several words, provided as a list of strings. 
The slogan must repeatedly appear on the billboard, word by word, maintaining the 
exact original order. Each word must fit entirely on a single line without breaking. 
Consecutive words on the same line must be separated by exactly one blank space.

Determine how many complete times the given advertising slogan can be displayed 
fully on the billboard screen.

Input format:
-------------
Line 1: Space seperated words, slogon
Line 2: Two space separated integers, rows & cols


Output format:
--------------
An integer, number of times the given advertising slogan can be displayed fully on the billboard screen.


Example 1:
----------
Input=
fast cars
2 8

Output=
1

Explanation:  
fast----  
cars----  
(The character '-' represents empty spaces on the screen.)


Example 2:
----------
Input=
win big now
3 7

Output=
2

Explanation:  
win-big  
now-win  
big-now  
(The character '-' represents empty spaces on the screen.)


Example 3:
----------
Input=
eat fresh daily
4 6

Output=1
 
Explanation:  
eat---  
fresh-  
daily-  
eat---  
(The character '-' represents empty spaces on the screen.)


Constraints:

- 1 <= slogan.length <= 1000
- Each word in slogan consists only of lowercase English letters.
- 1 <= rows, cols <= 2 *10^4 */
import java.util.*;

public class FillScreen_Two{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        int r = cin.nextInt();
        int c = cin.nextInt();
        System.out.println(find(inp,r,c));
        cin.close();
    }
    static int find(String inp [], int r, int c ){
        int count[] = new int[]{0};
        dfs(inp,0,0,0,r,c,count);
        return count[0];
    }
    static void dfs(String inp[], int ind, int currR, int currC, int r, int c,int count[]){
        if(currR==r){
            return;
        }
        
        if(currC+inp[ind].length() <= c){
            if(ind==inp.length-1){
                count[0]++;
                dfs(inp,0,currR,currC+inp[ind].length()+1,r,c,count);
            }
            else{
                dfs(inp,ind+1,currR,currC+inp[ind].length()+1,r,c,count);
            }
        }
        else{
            dfs(inp,ind,currR+1,0,r,c,count);
        }
    }
}