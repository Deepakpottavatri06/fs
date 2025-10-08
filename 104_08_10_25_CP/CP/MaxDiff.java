/*Shaggy Rogers is working with numbers.
He is given a number N, 
He wants to replace all occurrences of a digit in the number N,
with another digit between [0-9]. May be with same number too.

But there should not be any leading zeros in the number after the replacement,
Or the number should not become zero.

Rogers can perform the replacement of the occurrences of the digit in N for two 
times, He will generate two new numbers P and Q, such that the value of |P-Q| 
should be Maximum.

Your task is to help Shaggy Roers to find the maximum difference of P and Q possible.


Input Format:
-------------
An integer N, the number

Output Format:
--------------
Print an integer, the maximum difference of P and Q


Sample Input-1:
---------------
123

Sample Output-1:
----------------
820

Explanation:
------------
Replacement-1: replace 1 with 9 you will get P as 923
Replacement-2: replace 2 with 0 you will get Q as 103
So Max difference is 820.


Sample Input-2:
---------------
4254

Sample Output-2:
----------------
8008

Explanation:
------------
Replacement-1: replace 4 with 9 you will get P as 9259
Replacement-2: replace 4 with 1 you will get Q as 1259
So Max difference is 8008.
*/
import java.util.*;

public class MaxDiff{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        System.out.println(find(inp));
        cin.close();
    }
    static int find(String inp){
        StringBuilder p = new StringBuilder(inp);
        StringBuilder q = new StringBuilder(inp);
        for(int i = 0; i < inp.length(); i++){
            if(p.charAt(i)!='9'){
                char t = p.charAt(i);
                for(int j = i; j<inp.length(); j++){
                    if(t==p.charAt(j)) p.setCharAt(j,'9');
                }
                break;
            }
        }

        char first = q.charAt(0);
        for(int i = 0; i < inp.length(); i++){
            if(i==0){
                if(q.charAt(i)!='1'){
                    char t = q.charAt(i);
                    for(int j = i; j<inp.length(); j++){
                        if(t==q.charAt(j)) q.setCharAt(j,'1');
                    }
                    break;
                }
                
            } else {
                if(q.charAt(i)!='0' && first!=q.charAt(i)){
                    char t = q.charAt(i);
                    for(int j = i; j<inp.length(); j++){
                        if(t==q.charAt(j)) q.setCharAt(j,'0');
                    }
                    break;
                }
            }
        }

            
        return Integer.parseInt(p.toString()) - Integer.parseInt(q.toString());
        
    }
}
