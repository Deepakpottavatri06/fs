/*Given a classic mobile phone, and the key pad of the phone looks like below.
	1		2		3
		   abc	   def
		 
	4		5		6
   ghi     jkl     mno
  
	7		8		9
  pqrs     tuv    wxyz
	
	*		0		#


You are given a string S contains digits between [2-9] only, 
For example: S = "2", then the possible words are "a", "b", "c".

Now your task is to find all possible words that the string S could represent.
and print them in a lexicographical order. 

Input Format:
-------------
A string S, consist of digits [2-9]

Output Format:
--------------
Print the list of words in lexicographical order.


Sample Input-1:
---------------
2

Sample Output-1:
----------------
[a, b, c]


Sample Input-2:
---------------
24

Sample Output-2:
----------------
[ag, ah, ai, bg, bh, bi, cg, ch, ci]
 */
import java.util.*;

public class Combinations{
    public static void main(String [] arg){
        Scanner cin = new Scanner(System.in);
        // int n = cin.nextInt();
        String n = cin.nextLine();
        System.out.println(find(n));
    }
    static List<String> find(String n){
        String [][] map = new String[][]{
            {},
            {},
            {"a","b","c"},
            {"d","e","f"},
            {"g","h","i"},
            {"j","k","l"},
            {"m","n","o"},
            {"p","q","r","s"},
            {"t","u", "v"},
            {"w","x","y","z"}
        };
        List<String> res = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        btrack(n,res,str,map);
        // Collections.sort(res);
        return res;
    }
    static void btrack(String n, List<String> res, StringBuilder str, String [][] map){
        if(n.length()==0){
            res.add(str.toString());
            return ;
        }
        // int d = (int) Math.log10(n);
        // int firstD = n/((int)Math.pow(10,d));
        // int rem = n%((int)Math.pow(10,d));
        int firstD = Integer.parseInt(n.substring(0,1));
        String rem = (n.substring(1));
        for(String i : map[firstD]){
            str.append(i);
            btrack(rem,res,str,map);
            str.deleteCharAt(str.length()-1);
        }
        
    }
}