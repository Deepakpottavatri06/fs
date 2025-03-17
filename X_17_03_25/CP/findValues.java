/*Imagine you're a top-secret agent receiving an encrypted directive from headquarters. The message comes as a string of digits, and each digit (from 2 to 9) is a cipher for a set of potential code letters. To uncover the true instruction, you must translate the string into every possible combination of letters by substituting each digit with its corresponding set of letters. The final decoded messages listed in lexicographycal order.

Below is the mapping of digits to letters (as found on a traditional telephone keypad):

| Digit | Letters       |
|-------|---------------|
| 2     | a, b, c       |
| 3     | d, e, f       |
| 4     | g, h, i       |
| 5     | j, k, l       |
| 6     | m, n, o       |
| 7     | p, q, r, s    |
| 8     | t, u, v       |
| 9     | w, x, y, z    |

Note: The digit 1 does not correspond to any letters.

Example 1:
Input: 23  
Output: [ad, ae, af, bd, be, bf, cd, ce, cf]

Example 2:
Input: 2 
Output: [a, b, c]


Constraints:

- 0 <= digits.length <= 4  
- Each digit in the input is between '2' and '9'.
 */
import java.util.*;

public class findValues{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        System.out.println(find(n));
    }
    static String find(int n){
        Map<Integer,String []> map = new HashMap<>();
        map.put(2,new String []{"a","b","c"});
        map.put(3,new String []{"d","e","f"});
        map.put(4,new String []{"g","h","i"});
        map.put(5,new String []{"j","k","l"});
        map.put(6,new String []{"m","n","o"});
        map.put(7,new String []{"p","q","r","s"});
        map.put(8,new String []{"t","u","v"});
        map.put(9,new String []{"w","x","y","z"});
        List<String> res = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        findList(res,n,s,map);
        
        return res.toString();
    }
    static void findList(List<String> res, int n, StringBuilder s, Map<Integer,String []> map){
        if(n==0){
            res.add(s.toString());
            return;
        }
        int div = (int)(Math.pow(10,(Math.floor(Math.log10(n)))));
        
        int f = n / div;
        String temp [] = map.get(f);
        // System.out.println(f);
        for(String j : temp){
                s.append(j);
                findList(res,n%div,s,map);
                s.deleteCharAt(s.length()-1);
        }
        
        
    }
}
