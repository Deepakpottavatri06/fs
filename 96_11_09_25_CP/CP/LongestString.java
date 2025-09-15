/*Kiran is given a string S, and an integer N.
Kiran wants to find the longest substring which has following properties:
	- the substring of S should be maximum in length, and 
	- should contains maximum of N unique characters in it.
Your task is to help Kiran to find the longest substring 'ls' with 
above properties and return the length of the substring 'ls'.

Input Format:
-------------
Line-1: A string S
Line-2: An integer N, number of distinct characters.

Output Format:
--------------
Print an integer, length of longest substring with a max of N unique characters.


Sample Input-1:
---------------
philippines
3

Sample Output-1:
----------------
6


Sample Input-2:
---------------
abaccdbcca
2

Sample Output-2:
----------------
3
 */
import java.util.*;

public class LongestString{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String inp = cin.next();
        int n = cin.nextInt();
        System.out.println(find(inp,n));
        cin.close();
    }
    static int find(String inp, int n){
        Map<Character,Integer> map  = new HashMap<>();
        int best = 0;
        int left = 0;
        for(int i = 0; i < inp.length(); i++){
            map.put(inp.charAt(i),map.getOrDefault(inp.charAt(i),0)+1);
            // System.out.println(map);
            while((left<=i) && map.size()>n){
                
                map.put(inp.charAt(left),map.get(inp.charAt(left))-1);
                // System.out.println("  In Map : "+map);
                if(map.get(inp.charAt(left))==0){
                    map.remove(inp.charAt(left));
                }
                left++;
            }
            if(map.size()==n) best = Math.max(i-left+1,best);
            // System.out.println(set);
        }
        return best;
    }
}