/*"Emphatic Pronunciation" of a given word is where we take the word and
replicate some of the letter to emphasize their impact.

Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
We define emphatic pronunciation of a word, which is derived by replicating 
a group (or single) of letters in the original word. 

So that the replicated group is atleast 3 characters or more and 
greater than or equal to size of original group. 
For example Good -> Goood is an emphatic pronunciation,
but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
In the question you are given the "Emphatic pronunciation" word, 
you have to findout how many words can legal result in the 
"emphatic pronunciation" word.

Input Format:
-------------
Line-1 -> A String contains a single word, Emphatic Pronunciation word
Line-2 -> Space seperated word/s

Output Format:
--------------
Print an integer as your result


Sample Input-1:
---------------
goood
good goodd

Sample Output-1:
----------------
1


Sample Input-2:
---------------
heeelllooo
hello hi helo

Sample Output-2:
----------------
2

 */
import java.util.*;

public class Emphatic{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String emp = cin.nextLine();
        String inp [] = cin.nextLine().split(" ");
        System.out.println(findWords(emp,inp));
    }
    static int findWords(String emp, String inp[]){
        Map<Character,Integer> map = new HashMap<>();
        for(char c: emp.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        Map<Character,Integer> freq = new HashMap<>();
        int count = 0;
        outer : for(String i : inp){
            freq.clear();
            for(char c : i.toCharArray()){
                if(map.containsKey(c)){
                    freq.put(c,freq.getOrDefault(c,0)+1);
                    if(freq.get(c)>map.get(c)){
                        continue outer;
                    }
                }
                else{
                    continue outer;
                }
            }
            count++;
        }
        return count;
    }
}