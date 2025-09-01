/*Mr Saurabh is given a list of words.
Your task is to help Mr Saurabh to find the size of largest group

A group is formed using the following rules:
- Pick one smallest word (in length) and form a group with this word
  (if it is not part of any other group yet)
- Add a letter at any place in the word without changing the relative order 
  of the letters in it, and if it forms a word which is existing in the list[],
  then add the word to the group.
- Repeat the above two steps, till all the words in the list are part of groups.

NOTE:You move form more than one group.

Input Format:
-------------
Space separated words, wordsList[].

Output Format:
--------------
Print an integer result


Sample Input-1:
---------------
many money n an mony any one mone on

Sample Output-1:
----------------
5

Explanation:
------------
the words group is : [n, on, one, mone, money]


Sample Input-2:
---------------
a ab abb babb abab baba bab abbaa

Sample Output-2:
----------------
4 */
import java.util.*;

public class MaxGroupSize{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            String inp [] = cin.nextLine().split(" ");
            System.out.println(find(inp));
        }
    }
    static int find(String inp[]){
        Map<String,Integer> map = new HashMap<>();
        Arrays.sort(inp,(x,y)->x.length()-y.length());
        int ind = 0;
        for(String s : inp){
            map.put(s,ind++);
        }
        int dp [] = new int[inp.length];
        Arrays.fill(dp,1);
        int max =1;
        for(int i=0; i < inp.length; i++){
            StringBuilder str = new StringBuilder(inp[i]);
            for(int j = 0; j < str.length();j++){
                char t = str.charAt(j);
                str.deleteCharAt(j);
                
                if(map.containsKey(str.toString())){
                    
                    dp[i] = Math.max(dp[map.get(str.toString())]+1,dp[i]);
                }
                str.insert(j,t);
            }
            
            max = Math.max(dp[i],max);
        }
        // System.out.println(Arrays.toString(dp));
        return max;
    }
}