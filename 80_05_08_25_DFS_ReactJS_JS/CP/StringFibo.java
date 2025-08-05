/*Pramod is working on Strings consist of digits only. He wants to findout, 
whether the given string can form Fibonacci sequence or not.

A String can form a Fibonacci Sequence, if it contains at least 
three numbers, and numbers are in the following order:
first, second, third  = first + second, fourth = third + second, .. so on.

Return true, if the given string can form fibonacci sequence,
otherwise, return false.

Note: Numbers in the fibonacci sequence contains no leading 0's.
for example, 2, 03,5 or 2,3,05 or 02,3,5 are not valid.

Input Format:
-------------
A String consist of digits only

Output Format:
--------------
Print a boolean value as result.


Sample Input-1:
---------------
23581321

Sample Output-1:
----------------
true

Explanation: 
------------
Fibonacci Sequence is : 2, 3, 5, 8, 13, 21
2, 3, 2+3=5, 3+5=8, 5+8=13, 8+13=21.

Sample Input-2:
---------------
199100199

Sample Output-2:
----------------
true

Explanation: 
------------
Fibonacci Sequence is : 1 99 100 199
1, 99, 1+99=100, 99+100=199.

 */
import java.util.Scanner;
public class StringFibo {
    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp = cin.next();
            System.out.println(find(inp));
        }
    }
    static boolean find(String s){
        for (int i = 1; i < s.length()/2; i++) {
            if (s.charAt(0) == '0' && i > 1) continue;

            long first = Long.parseLong(s.substring(0, i));
            
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == '0' && j - i > 1) continue;

                long second = Long.parseLong(s.substring(i, j));

                if (check(s, j, first, second, 2)) {
                    return true;
                }
            }   
        }
        return false;
    }
    static boolean check(String s, int index, long first, long second, int count){
        if (index == s.length()) {
            return count >= 3;
        }

        long sum = first + second;
        String sumStr = String.valueOf(sum);

        if (!s.startsWith(sumStr, index)) {
            return false;
        }

        return check(s, index + sumStr.length(), second, sum, count + 1);
    }
}

