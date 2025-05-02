/*You are a database integrity engineer working for a global cloud company. 
Your team maintains a distributed database network, where each server either:
    - Stores equivalent data to another server (serverX == serverY).
    - Stores different data from another server (serverX != serverY).

The transitive consistency rule must be followed:
    - If A == B and B == C, then A == C must be true.
    - If A == B and B != C, then A != C must be true.

Your task is to analyze the given constraints and determine whether they 
follow transitive consistency. If all relations are consistent, return true; 
otherwise, return false

Input Format:
-------------
Space separated strnigs, list of relations

Output Format:
--------------
Print a boolean value, whether transitive law is obeyed or not.


Sample Input-1:
---------------
a==b c==d c!=e e==f

Sample Output-1:
----------------
true


Sample Input-2:
---------------
a==b b!=c c==a

Sample Output-2:
----------------
false

Explanation:
------------
{a, b} form one equivalence group.
{c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
However, b != c contradicts b == a and c == a.

Sample Input-3:
---------------
a==b b==c c!=d d!=e f==g g!=d

Sample Output-3:
----------------
true
 */
import java.util.*;

class DisjointSet{
    int parent [];
    int rank [];
    DisjointSet(){
        parent = new int[26];
        rank = new int[26];
        for(int i = 0; i < 26; i++){
            parent[i] = i;
            rank[i] = i;
        }
    }
    public int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public boolean union(int a, int b){
        int rootX = find(a);
        int rootY = find(b);
        if(rootX == rootY){
            return false;
        }
        
        if(rank[rootX]> rank[rootY]){
            parent[rootY] = rootX;
        }
        else{
            parent[rootX] = rootY;
        }
    return true;
    }
}

public class ConsistentRelations{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        String inp[] = cin.nextLine().split(" ");
        System.out.println(checkRelations(inp));
        cin.close();
    }
    static boolean checkRelations(String inp []){
        List<String> notEq = new ArrayList<>();
        DisjointSet set = new DisjointSet();
        int n = inp.length;
        for(int i  = 0; i < n ; i++){
            if(inp[i].charAt(1)=='='){
                set.union(inp[i].charAt(0)-97,inp[i].charAt(3)-97);
            }
            else{
                notEq.add(inp[i]);
            }
        }
        
        for(String i : notEq){
            int a = i.charAt(0);
            int b=  i.charAt(3);
            int rootX = set.find(a-97);
            int rootY = set.find(b-97);
            if(rootX==rootY){
                return false;
            }
        }
        return true;
    }
}