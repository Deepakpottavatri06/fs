/*A detective is investigating a case involving a secret message hidden within a 
longer note. The detective knows that the culprit rearranged the letters of a 
short code-word into multiple secret locations within a larger note.

Given two strings, note (the longer text) and codeWord (the short secret code), 
your task is to help the detective find all starting positions within the note 
where any rearrangement or shuffled of codeWord is located.

Input Format:
-------------
Single line space separated strings, two words.

Output Format:
--------------
Print the list of integers, indices.


Sample Input-1:
---------------
bacdgabcda abcd
 
Sample Output-1:
----------------
[0, 5, 6]

Explanation:
- At index 0: "bacd" is an anagram of "abcd"
- At index 5: "abcd" matches exactly
- At index 6: "bcda" is an anagram of "abcd"
Thus, the positions [0, 5, 6] are returned.

Sample Input-2:
---------------
bacacbacdcab cab

Sample Output-2:
----------------
[0, 3, 4, 5, 9]
 */

 import java.util.*;

public class FindPositions_Program_One{
    public static void main(String [] ar){
        Scanner cin = new Scanner(System.in);
        String longtext = cin.next();
        String code = cin.next();
        System.out.println(find(longtext,code));
        cin.close();
    }
    static String find(String longtext, String code){
        Map<Character,Integer> codeMap = new HashMap<>();
        for(char c: code.toCharArray()){
            codeMap.put(c,codeMap.getOrDefault(c,0)+1);
        }
        
        Map<Character,Integer> win = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int len = code.length();
        int n = longtext.length();
        for(int i = 0; i < len ; i++){
            win.put(longtext.charAt(i),win.getOrDefault(longtext.charAt(i),0)+1);
        }
        if(win.equals(codeMap)){
            res.add(0);
        }
        int ind = 0;
        for(int i = len; i < n ; i++){
            win.put(longtext.charAt(ind),win.getOrDefault(longtext.charAt(ind),0)-1);
            if(win.get(longtext.charAt(ind))==0){
                win.remove(longtext.charAt(ind));
            }
            ind++;
            win.put(longtext.charAt(i),win.getOrDefault(longtext.charAt(i),0)+1);
            if(win.equals(codeMap)){
                res.add(ind);
            }
        }
        return res.toString();
        
    }
}