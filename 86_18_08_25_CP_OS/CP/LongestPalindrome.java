/*Mr Gnanesh is working with words. He has given a list of words. 
Each word in the list contains only two lowercase letters [a-z].

He wants to create biggest word BW, by concatenating words from the list, which 
is a palindrome too. He is allowed to use each word from the list atmost once.

Return the length of the biggest word can be formed using the list.
If it is impossible to create such word, return 0.

Input Format:
-------------
Space separated strings, words[], two letter words.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
ab ba dd

Sample Output-1:
----------------
6

Explanation: 
------------
The biggest word is, ab,dd,ba => abddba, which is a palindrome.


Sample Input-2:
---------------
pq rs sr mk km pq

Sample Output-2:
----------------
8

Explanation: 
------------
The biggest word is, rs,sr,mk,km => rsmkkmsr or mkrssrkm..etc, 
which is a palindrome.


Sample Input-3:
---------------
aa bb cc

Sample Output-3:
----------------
2
 */
import java.util.*;

public class LongestPalindrome{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(" ");
        System.out.println(find(inp));
        cin.close();
    }
    static int find(String inp[]){
        int res = 0;
        Map<String,Integer> map = new HashMap<>();
        for(String s: inp){
            map.put(s,map.getOrDefault(s,0)+1);
        }
        boolean center = false;
        
        for(String key: new ArrayList<>(map.keySet())){
            String rev = new StringBuilder(key).reverse().toString();
            if(key.equals(rev)){
                int count = (map.get(key)/2);
                res += (count)*4;
                if(map.get(key)%2!=0){
                    center = true;
                }
            }
            else if(key.compareTo(rev)<0 && map.containsKey(rev)){
                res += 4*Math.min(map.get(key),map.get(rev));
            }
        }
        if(center){
            res+=2;
        }
        
        
        return res;
    }
    

}