/*There are N computers in a network, all the computers are connected as tree 
structure. And one new connection is added in the Network. The computers in 
the network are identified with their IDs, the IDs are numbered between 1 to N.

The connections in the network is given as coonection[i] = [comp-A, comp-B], 
there is a connection between comp-A and comp-B.

Your task is to remove a connection in the network and print it, so that 
all the computers are connected as tree structure. If there are multiple 
options to remove, remove the connection that occurs last in the input.


Input Format:
-------------
Line-1: Two space separated integers N, number of computers.
Next N lines: Two space separated integers, comp-A & comp-B.

Output Format:
--------------
Print the connection which is removed.


Sample Input-1:
---------------
6
1 2
3 4
3 6
4 5
5 6
2 3

Sample Output-1:
---------------
5 6


Sample Input-2:
---------------
4
1 2
2 3
3 4
2 4

Sample Output-2:
---------------
2 4
 */
import java.util.*;

class DSU{
    Map<Integer,Integer> parent;
    int redundant [];
    DSU(int arr[][]){
        redundant = new int[2];
        parent = new HashMap<>();
        for(int i [] : arr){
            parent.put(i[0],i[0]);
            parent.put(i[1],i[1]);
        }
    }
    public int find(int ele){
        if(parent.get(ele)!=ele){
            parent.put(ele,find(parent.get(ele)));
        }
        return parent.get(ele);
    }
    public boolean union(int a , int b){
        int rootX = find(a);
        int rootY = find(b);
        
        if(rootX==rootY){
            redundant[0] = a;
            redundant[1] = b;
            return false;
        }
        
        if(rootX<rootY){
            parent.put(rootY,parent.get(rootX));
        }
        else {
            parent.put(rootX,parent.get(rootY));
        }
        return true;
    }
}

public class MakeTree{
    public static void main(String [] args){
        Scanner cin  =  new Scanner(System.in);
        int edges = cin.nextInt();
        int arr[][] = new int[edges][2];
        for(int i = 0; i < edges; i++){
            arr[i][0] = cin.nextInt();
            arr[i][1] = cin.nextInt();
        }
        System.out.println(findRedundant(edges,arr));
        cin.close();
    }
    static String findRedundant(int edges, int arr[][]){
        DSU set = new DSU(arr);
        for(int i = 0; i < edges ;  i++){
            set.union(arr[i][0],arr[i][1]);
        }
        int pair [] = set.redundant;
        return pair[0]+" "+pair[1];
    }
}