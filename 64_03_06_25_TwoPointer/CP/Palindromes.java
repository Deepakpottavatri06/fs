/*Mr. Parandamayya is working with Strings.
He is given a String S. He has to find the palindromes in S, 
A palindrome can be a substring of S (Strictly substrings only, not subsequences).

Your task is to find the count the number of palindromes can be formed from S.

NOTE: Count each occurence of palindromes if duplicate substrings found. 

Input Format:
-------------
A string S

Output Format:
--------------
Print an integer, number of palindromes.


Sample Input-1:
---------------
divider

Sample Output-1:
----------------
9

Explanation:
-------------
Palindromes are d, i, v, i, d, e, r, ivi, divid

Sample Input-2:
---------------
abcdef

Sample Output-2:
----------------
6

Explanation:
-------------
Palindromes are a, b, c, d, e, f.
 */
import java.util.Scanner;

public class Palindromes {
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp = cin.next();
            System.out.println(find(inp));
            cin.close();
        }
    }
    static int find(String inp){
        int res = 0;
        int n = inp.length();
        for(int i = 0; i < n; i++){
            int curr = 0;
            int l = i;
            int r = i;
            while((l>=0 && r<n)){
                if(inp.charAt(l)==inp.charAt(r)){
                    curr++;
                }
                else{
                    break;
                }
                l--;
                r++;
            }
            l = i;
            r = i+1;
            while((l>=0 && r<n)){
                if(inp.charAt(l)==inp.charAt(r)){
                    curr++;
                }
                else{
                    break;
                }
                l--;
                r++;
            }
            res+=curr;
        }
        
        return res;
    }
}