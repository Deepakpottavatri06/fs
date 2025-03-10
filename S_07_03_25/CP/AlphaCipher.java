/*AlphaCipher is a string formed from another string by rearranging its letters

You are given a string S.
Your task is to check, can any one of the AlphaCipher is a palindrome or not.

Input Format:
-------------
A string S

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
carrace

Sample Output-1:
----------------
true


Sample Input-2:
---------------
code

Sample Output-2:
----------------
false
 */
import java.util.*;

public class AlphaCipher{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        System.out.println(decode(inp));
        cin.close();
    }
    static boolean decode(String inp){
        int mask = 0;
        for(char c:inp.toCharArray()){
            int bit = 1<<(c-'a');
            mask^=bit;
        }
        return (mask&(mask-1))==0;
    }
}