/*Aruna as a type writer, typing a document in her laptop. Suddently her system got
hacked and whatever she types as words are displaying in reverse and with simple 
braces. She types only lowercase letters.

Inorder to get the actual words, Aruna has to reverse each word starting with the
word which is in innermost most braces and remove those braces.
Help Aruna to get actual words.

Constraints:
------------
  - 0 <= word.length <= 2000
  - Word only contains lower case English characters and parentheses.
    It's guaranteed that all braces are balanced.


Input Format:
-------------
Line-1: a string represents an encoded word.

Output Format:
--------------
return the original string.


Sample Input-1:
---------------
(pqrs)

Sample Output-1:
----------------
srqp


Sample Input-2:
---------------
(uoy(are)woh)

Sample Output-2:
----------------
howareyou

Explanation
------------
Initially "are" will be revesed as "era".
Then (uoyerawoh) will be reversed.
 */
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class OgString{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp = cin.next();
            System.out.println(find(inp));
        }
    }
    static String find(String inp){
        Stack<Character> st = new Stack<>();
        int i = 0;
        int len = inp.length();
        while(i < len){
            char c = inp.charAt(i);
            if(c!=')'){
                st.push(c);
            }
            else{
                Deque<Character> q = new ArrayDeque<>();
                while(!st.empty() && st.peek()!='('){
                    q.offer(st.pop());
                }
                st.pop();
                // System.out.println("after proccessing :"+ st);
                while(!q.isEmpty()){
                    st.push(q.poll());
                }
            }
            i++;
        }
        // System.out.println(st);
        StringBuilder str = new StringBuilder();
        while(!st.empty()){
            str.insert(0,st.pop());
        }
        return str.toString();
    }
}