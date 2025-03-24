/*Imagine you’re managing a busy cafe where every order is logged. You have a 
record—a list of dish names ordered throughout the day—and you want to determine
which dishes are the most popular. Given an list of strings representing the dish
names and an integer P, your task is to return the P most frequently ordered dishes.

The results must be sorted by the number of orders, from the most frequent to 
the least. If two dishes have been ordered the same number of times, they should
be listed in alphabetical order.

Input Format:
-------------
Line-1: comma separated line of words, list of words.
Line-2: An integer P, number of words to display. 

Output Format:
--------------
Print P number of most common used words.

Example 1:
----------
Input=
coffee,sandwich,coffee,tea,sandwich,muffin
2
Output=
[coffee, sandwich]

Explanation: "coffee" and "sandwich" are the two most frequently ordered items. 
Although both appear frequently, "coffee" is placed before "sandwich" because 
it comes earlier alphabetically.

Example 2:
----------
Input=
bagel,muffin,scone,bagel,bagel,scone,scone,muffin,muffin
3
Output=
[bagel, muffin, scone] 

Explanation: "bagel", "muffin", and "scone" are the three most popular dishes 
with order frequencies of 3, 3, and 2 respectively. Since "bagel" and "muffin" 
have the same frequency, they are ordered alphabetically.

Constraints:

- 1 ≤ orders.length ≤ 500  
- 1 ≤ orders[i].length ≤ 10  
- Each orders[i] consists of lowercase English letters.  
- P is in the range [1, The number of unique dish names in orders]. */
import java.util.*;

class TrieNode{
    TrieNode [] children;
    boolean end ;
    int count = 0;
    TrieNode(){
        children = new TrieNode[26];
        end = false;
    }
    
}
class Trie{
    TrieNode root;
    Trie(){
        root = new TrieNode();
    }
    void insert(String str){
        TrieNode node = root;
        for(char c: str.toCharArray()){
            int ind = c-'a';
            if(node.children[ind]==null){
                node.children[ind] = new TrieNode();
            }
            node = node.children[ind];
        }

        node.end = true;
        node.count++;
    }
    boolean search (String str){
        TrieNode node = root;
        for(char c : str.toCharArray()){
            if(node.children[c-'a']==null){
                return false;
            }
            node = node.children[c-'a'];
        }
        return node.end;
    }
    
}

class Pair{
    String t ;
    int v ;
    Pair(String t, int v){
        this.t = t;
        this.v = v;
    }
} 
public class MostFreq{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp [] = cin.nextLine().split(",");
        int p = cin.nextInt();
        System.out.println(find(inp,p));
        cin.close();
    }
    static List<String> find(String inp [], int p){
        List<String> res = new ArrayList<>();

        Trie t = new Trie();
        
        for(String i : inp){
            t.insert(i);
            
        }
        return res;
    }
}