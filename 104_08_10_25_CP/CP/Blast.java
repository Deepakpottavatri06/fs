/*Murali playing a mobile game, Blast the letters.

In the game he is given a word W and value R.
Murali has to perform the blasting operation as follows:
	- He has to blast the mimeograph M of length R in W, 
	  a mimeograph is a string such that each letter in it should be same.
	- After blasting the mimeograph, the rest of the string on its
	  left side and right side, concatenated together.

Murali has to perform the blasting operation repeatedly, 
until no more blasting is possible. Your task is to return 
the final string after all the blast operations have been done.

Input Format:
-------------
Line-1: A string and an integer, W and R.

Output Format:
--------------
Print a string, the final string after all the blast operations have been done.


Sample Input-1:
--------------- 
ababbaaab 3

Sample Output-1:
----------------
aba


Sample Input-2:
--------------- 
caaabbbaacdddd 2

Sample Output-2:
----------------
cabc
*/
import java.util.*;

public class Blast {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String w = cin.next();
        int r = cin.nextInt();
        System.out.println(blast(w, r));
        cin.close();
    }

    static String blast(String s, int r) {
        // Stack to store pairs: [character, count]
        Deque<int[]> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek()[0] == c) {
                stack.peek()[1]++;
                
                if (stack.peek()[1] == r) {
                    stack.pop();
                }
            } else {
                stack.push(new int[]{c, 1});
            }
        }

        // Build final string from stack
        StringBuilder sb = new StringBuilder();
        for (int[] pair : stack) {
            sb.append(String.valueOf((char) pair[0]).repeat(pair[1]));
        }

        // System.out.println(sb);
        return sb.reverse().toString(); 
    }
}
