/*Mr Robert is working with strings.
He selected two strings S1 and S2, may differ in length,
consists of lowercase alphabets only.

Mr Robert has to update the strings S1, S2 to meet any one of the criteria:
	- All the alphabets in S1 should be less than all the alphabets in S2.
	- All the alphabets in S2 should be less than all the alphabets in S1.
	- Both S1 and S2 should have only one distinct alphabet in it.
To Achieve, one of the criteria, you are allowed to replace any one letter in 
the string with any other lowercase alphabet.

Your task is to help Mr Robert, to find the minimum replacements to be done to 
update the strings S1 and S2 to meet one of the criteria mentioned above.


Input Format:
-------------
Two space separated strings S1 and S2.

Output Format:
--------------
Print an integer, minimum number of replacements.


Sample Input-1:
---------------
apple ball

Sample Output-1:
----------------
3

Explanation:
------------
Consider the best way to make the criteria true:
- Update S2 to "baaa" in 2 replacements, and Update S1 to "cpple" in 1 replacement
then every alphabet in S2 is less than every alphabet in S1.
        (OR)
- Update S1 to "ppppp" in 3 replacements, then every alphabet in S2 is less 
than every alphabet in S1.


Sample Input-2:
---------------
kmit kmec

Sample Output-2:
----------------
2


 */
import java.util.Scanner;

public class MinReplacement{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String s1 = cin.next();
            String s2 = cin.next();
            System.out.println(find(s1,s2));
            cin.close();
        }
    }
    
    static int find(String s1,String s2){
        int ops = Integer.MAX_VALUE;
        int freqS1 [] = new int[26];
        int freqS2 [] = new int[26];
        for(char c : s1.toCharArray()){
            freqS1[c-'a']++;
        }
        for(char c : s2.toCharArray()){
            freqS2[c-'a']++;
        }
        ops = Math.min(check(freqS1,freqS2),ops);
        ops = Math.min(check(freqS2,freqS1),ops);
        ops = Math.min(oneDistinct(freqS1,freqS2),ops);
        return ops;
    }
    static int check(int s1[], int s2 []){
         // all s1 < all s2 ==> max(s1) < min(s2)
         int Minops = Integer.MAX_VALUE;
        for(int bound = 1; bound < 26; bound++){
            int ops = 0;
            for(int i = bound; i < s1.length; i++){
                ops += s1[i];
            }
            for(int i = 0; i < bound ; i++){
                ops += s2[i];
            }
            Minops = Math.min(ops,Minops);
        }
        return Minops;
    }
    static int oneDistinct(int s1[], int s2[]){
        int Minops = Integer.MAX_VALUE;
        for(int i = 0; i < 26; i++){
            int ops = 0;
            for(int j = 0; j < 26; j++){
                ops+=(i!=j)? s1[j] : 0;
            }
            Minops = Math.min(ops,Minops);
        }
        int total = Minops;
        Minops = Integer.MAX_VALUE;
        for(int i = 0; i < 26; i++){
            int ops = 0;
            for(int j = 0; j < 26; j++){
                ops+=(i!=j)? s2[j] : 0;
            }
            Minops = Math.min(ops,Minops);
        }
        total += Minops;
        return total;
    }
}