/*Budget Padmanabham planned to visit the tourist places. There are N tourist 
places in the city. The tourist places are numbered from 1 to N.

You are given the routes[] to travel between the tourist places in the city.
where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
Your task is to help Budget Padmanabham to find the cheapest route plan, to 
visit all the tourist places in the city. If you are not able to find such plan, 
print -1.
 
Input Format:
-------------
Line-1: Two space separated integers N and R,number of places and routes.
Next R lines: Three space separated integers, start, end and price.
  
Output Format:
--------------
Print an integer, minimum cost to visit all the tourist places.
 
 
Sample Input-1:
---------------
4 5
1 2 3
1 3 5
2 3 4
3 4 1
2 4 5
 
Sample Output-1:
----------------
8
 
Explanation:
------------
The cheapest route plan is as follows:
1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
Sample Input-2:
---------------
4 3
1 2 3
1 3 5
2 3 4
 
Sample Output-2:
----------------
-1
 */
import java.util.*;

class DisjointSet{
    int [] parent;
    DisjointSet(int n){
        parent = new int[n+1];
        for(int i = 0; i < n+1; i++ ){
            parent[i] = i;
        }
    }
    public int find(int x){
        if(parent[x]!=x){
            return find(parent[x]);
        }
        return parent[x];
    }
    public boolean union(int a, int b){
        int rootX = find(a);
        int rootY = find(b);
        
        if(rootX==rootY){
            return false;
        }
        
        if(rootX<rootY){
            parent[rootY] = rootX;
        }
        else{
            parent[rootX] = rootY;
        }
        return true;
    }
}

public class MincostTree{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int edges = cin.nextInt();
        int arr[][] = new int[edges][3];
        for(int i = 0; i < edges; i++){
            arr[i][0] = cin.nextInt();
            arr[i][1] = cin.nextInt();
            arr[i][2] = cin.nextInt();
        }
        System.out.println(findMin(arr,n,edges));
        cin.close();
    }
    static int findMin(int arr[][], int n , int edges){
        Arrays.sort(arr,(a,b)->(a[2]-b[2]));
        DisjointSet set = new DisjointSet(n);
        int cost = 0;
        int edgesInTree = 0;
        for(int i = 0; i< edges; i++){
            if(set.union(arr[i][0],arr[i][1])){
                edgesInTree++;
                cost+=arr[i][2];
            }
        }
        // Set<Integer> set1 = new HashSet<>();
        // for(int i = 1; i < n+1; i++){
        //     set1.add(set.find(i));
        // }
        // return set1.size()==1? cost:-1;
        return edgesInTree == n-1? cost : -1;
        
    }
}