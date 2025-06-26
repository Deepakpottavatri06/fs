/*Ravi is playing with strings.Given Three alphabets [a,b,c] , ravi has to 
make strings such that no two adjacents alphabets are same.

For example, He can make strings as "ab", "acb", "b" and "cbabcabcb" which 
are acceptable.where as the strings "bb", "bcc" and "aabbc" are not acceptable.

Given two integers N and L, Ravi made a list of all acceptable strings of 
length N sorted in lexicographical order.

Return the Lth string of this list or return an empty string if there are less 
than L acceptable strings of length N.

Input Format:
-------------
Line-1: Two space separated integers N and L.

Output Format:
--------------
Print a string result.


Sample Input-1:
---------------
2 3

Sample Output-1:
----------------
ba

Explanation:
-------------
Strings in lexigraphical order are ab,ac,ba,bc,ca,cb. and 3rd one is ba.


Sample Input-2:
---------------
3 4

Sample Output-2:
----------------
acb

Explanation:
------------
Strings in lexigraphical order are aba,abc,aca,acb,bab....

 */
import java.util.Scanner;

public class LthString{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            int l = cin.nextInt();
            
            System.out.println(find(n,l));
        }
    }
    static String find(int n, int l){
        StringBuilder curr = new StringBuilder();
        StringBuilder res  = new StringBuilder();
        int globalL [] = new int[]{l};
        backtrack(n,globalL,res,curr);
        return res.toString();
    }
    static void backtrack(int n, int l[], StringBuilder res, StringBuilder curr){
        if(l[0]==0) return;
        if(n==0 && l[0]==1){
            res.setLength(0);
            res.append(curr);
            l[0]--;
            return;
        }
        if(n==0){
            // System.out.println(curr);
            l[0]--;
            return;
        }
        
        for(int i = 0; i < 3; i++){
            char last = '0';
            if(curr.length()>0){
                last = curr.charAt(curr.length()-1);
            }
            
            if(last != (char)('a'+i)){
                curr.append((char)('a'+i));
                backtrack(n-1,l,res,curr);
                curr.deleteCharAt(curr.length()-1);
            }
        }
    }
}