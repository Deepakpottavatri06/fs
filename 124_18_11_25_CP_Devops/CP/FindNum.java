/*Mr. Dhanush has provided the string NUM, which solely contains digits.
He is looking for substrings that are numbers and have an equal number of 
distinct digits in each of them. It follows the rule if the number is 123123, 
but not if the number is 12312.

Your task is to assist Mr. Dhanush in determining the number of distinct 
substrings (numbers) of NUM that adhere to the mentioned rule.


Input Format:
-------------
A string NUM, consist of diits [0-9]

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
6767

Sample Output-1:
----------------
5

Explanation: 
------------
The list of words are: 6,7,67,76,6767


Sample Input-2:
---------------
2345432

Sample Output-2:
----------------
16

Explnation:
-----------
The list of words are: 2,3,4,5,23,34,45,54,43,32,234,345,543,432,2345,5432

 */
import java.util.*;

public class FindNum{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        String num = cin.next();
        System.out.println(find(num));
    }
    static int find(String num){
        int res = 0;
        int n = num.length();
        
        for(int i = 0; i < n; i++){
            Map<Character, Integer> map = new HashMap<>();
            StringBuilder str = new StringBuilder();
            Set<String> set = new HashSet<>();
            for(int j= 0; j <=i ; j++){
                map.put(num.charAt(j),map.getOrDefault(num.charAt(j),0)+1);
                str.append(num.charAt(j));
            }
            if(check(map)){
                res++;
            }
            set.add(str.toString());
            int winSize = i+1;
            for(int k = i+1; k < n; k++){
                map.put(num.charAt(k),map.getOrDefault(num.charAt(k),0)+1);
                map.put(num.charAt(k-winSize),map.getOrDefault(num.charAt(k-winSize),0)-1);
                str.append(num.charAt(k));
                str.deleteCharAt(0);
                if(map.get(num.charAt(k-winSize))==0){
                    map.remove(num.charAt(k-winSize));
                }
                if(!set.contains(str.toString()) && check(map)){
                    res++;
                }
                set.add(str.toString());
            }
        }
        return res;
    }
    static boolean check(Map<Character,Integer> map){
        Integer val = null;
        for(Map.Entry<Character,Integer> e: map.entrySet()){
            if(val==null){
                val = e.getValue();
            }
            else if(val!=e.getValue()){
                return false;
            }
        }
        return true;
    }
}