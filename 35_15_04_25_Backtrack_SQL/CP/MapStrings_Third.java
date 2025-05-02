/*Given two strings S1 and S2, find if S2 can match S1 or not.

A match that is both one-to-one (an injection) and onto (a surjection), 
i.e. a function which relates each letter in string S1 to a separate and 
distinct non-empty substring in S2, where each non-empty substring in S2
also has a corresponding letter in S1.

Return true,if S2 can match S1.
Otherwise false.

Input Format:
-------------
Line-1 -> Two strings S1 and S2

Output Format:
--------------
Print a boolean value as result.


Sample Input-1:
---------------
abab kmitngitkmitngit

Sample Output-1:
----------------
true


Sample Input-2:
---------------
aaaa kmjckmjckmjckmjc

Sample Output-2:
----------------
true

Sample Input-3:
---------------
mmnn pqrxyzpqrxyz

Sample Output-3:
----------------
false
 */
import java.util.*;

public class MapStrings_Third{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        String inp2 = cin.next();
        System.out.println(find(inp,inp2));
        cin.close();
    }
    static boolean find(String inp, String inp2){
        Map<String,String> map = new HashMap<>();
        return backtrack(map,inp,inp2,0,0);
    }
    static boolean backtrack(Map<String,String> map ,String inp1, String  inp2, int ind1, int ind2 ){
        if(ind1==inp1.length()  ){
            return ind2 == inp2.length();
        }
        
        if(ind2==inp2.length()){
            return false;
        }
        
        for(int i = ind2; i < inp2.length(); i++){
            String word = inp2.substring(ind2,i+1);
            System.out.println("ind1 :"+ind1+" s1 :"+inp1.charAt(ind1)+" s2: "+word);
            if(map.containsKey(inp1.substring(ind1,ind1+1))){
                if(map.get(inp1.substring(ind1,ind1+1)).equals(word)){
                    boolean t = backtrack(map,inp1,inp2,ind1+1,i+1);
                    if(t){
                        return true;
                    }
                }
            }
            else{
                map.put(inp1.substring(ind1,ind1+1),word);
                boolean t = backtrack(map,inp1,inp2,ind1+1,i+1);
                if(t){
                    return true;
                }
                map.remove(inp1.substring(ind1,ind1+1));
            }
            
        }
        return false;
    }
}