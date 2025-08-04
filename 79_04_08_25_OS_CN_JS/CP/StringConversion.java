/*Prabhath is working on words.  He used to take out every letter that was repeated 
in the word. 
A word W is given to Prabhath. His objective is to eliminate every duplicate 
letter from W such that the resultant word R contains every unique letter from W
and did not contain any duplicate letters. 
And R should be at the top of the dictionary order.

NOTE:
-----
He is not allowed to shuffle the relative order of the letters of the word W to
create the word R.

Input Format:
-------------
A String, the word W.

Output Format:
--------------
Print a String, resultant word R.


Sample Input-1:
---------------
cbadccb

Sample Output-1:
----------------
adcb


Sample Input-2:
---------------
silicosis

Sample Output-2:
----------------
ilcos

 */

 import java.util.*;

public class StringConversion{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        StringBuilder res = new StringBuilder();
        Set<Character> set = new HashSet<>();
        find(set,inp,new StringBuilder(),res,0);
        System.out.println(res);
        cin.close();
    }
    static void find(Set<Character> set , String inp, StringBuilder curr, StringBuilder res, int ind){
        if(ind==inp.length()){
            if(curr.length()>res.length()){
                res.setLength(0);
                res.append(curr.toString());
            }
            else if(curr.length()==res.length()&& curr.toString().compareTo(res.toString())<0){
                res.setLength(0);
                res.append(curr.toString());
            }
            return;
        }
        
        // option 1: take
        if(!set.contains(inp.charAt(ind))){
            curr.append(inp.charAt(ind));
            set.add(inp.charAt(ind));
            find(set,inp,curr,res,ind+1);
            set.remove(inp.charAt(ind));
            curr.deleteCharAt(curr.length()-1);
        }
        // option 2: not take
        find(set,inp,curr,res,ind+1);
        
    }
}