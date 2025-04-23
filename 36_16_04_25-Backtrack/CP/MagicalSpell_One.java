/*In the ancient land of Palindoria, wizards write magical spells as strings of 
lowercase letters. However, for a spell to be effective, each segment of the 
spell must read the same forward and backward.

Given a magical spell 'spell', your task is to partition it into sequences of 
valid magical spell segments. 
Your goal is to help the wizard discover all valid combinations of magical spell 
segmentations.

Example 1:
----------
Input:  
aab
  
Output:  
[[a, a, b], [aa, b]]

Example 2:

Input:  
a  
Output:  
[[a]]

Spell Constraints:

- The length of the spell is between 1 and 16 characters.  
- The spell contains only lowercase English letters.  
*/
import java.util.*;

public class MagicalSpell_One{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String word = cin.nextLine();
        System.out.println(find(word));
        cin.close();
    }
    static List<List<String>> find(String word){
        List<List<String>> res = new ArrayList<>();
        backtrack(word,res,new ArrayList<>(),0);
        return res;
    }
    static void backtrack(String word, List<List<String>> res , List<String> curr, int ind){
        if(ind==word.length()){
            res.add(new ArrayList<>(curr));
            return;
        }
        
        for(int i = ind ; i < word.length() ; i++){
            String w = word.substring(ind,i+1);
            if(check(w)){
                // System.out.println(w);
                curr.add(w);
                backtrack(word,res,curr,i+1);
                curr.remove(curr.size()-1);
            }
        }
        
    }
    static boolean check(String word){
        int n = word.length();
        for(int i = 0; i < n/2; i++){
            if(word.charAt(i)!=word.charAt(n-1-i)){
                return false;
            }
        }
        return true;
    }
}