/*There are N cities, and M routes[], each route is a path between two cities.
routes[i] = [city1, city2], there is a travel route between city1 and city2.
Each city is numbered from 0 to N-1.
 
There are one or more Regions formed among N cities. 
A Region is formed in such way that you can travel between any two cities 
in the region that are connected directly and indirectly.
 
Your task is to findout the number of regions formed between N cities. 
 
Input Format:
-------------
Line-1: Two space separated integers N and M, number of cities and routes
Next M lines: Two space separated integers city1, city2.
 
Output Format:
--------------
Print an integer, number of regions formed.
 
 
Sample Input-1:
---------------
5 4
0 1
0 2
1 2
3 4
 
Sample Output-1:
----------------
2
 
 
Sample Input-2:
---------------
5 6
0 1
0 2
2 3
1 2
1 4
2 4
 
Sample Output-2:
----------------
1
 
Note: Look HINT for explanation.
 */

 import java.util.*;

class DisjointSet{
    int parent [];
    int rank [];
    DisjointSet(int n){
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n ; i++){
            parent[i] = i;
        }
    }
    
    public int find(int ele){
        if(parent[ele]!=ele){
            parent[ele] = find(parent[ele]);
        }
        return parent[ele];
    }
    public boolean union(int a, int b){
        int rootX = find(a);
        int rootY = find(b);
        
        if(rootX==rootY){
            return false;
        }
        
        if(rank[rootX]>rank[rootY]){
            parent[rootY] = rootX;
        }
        else if(rank[rootX]<rank[rootY]){
            parent[rootX] = rootY;
        }
        else{
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

public class CityRegions{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt();
        int arr[][] = new int[m][2];
        for(int i = 0; i < m ; i++){
            arr[i][0] = cin.nextInt();
            arr[i][1] = cin.nextInt();
        }
        System.out.println(findRegions(n,m,arr));
        cin.close();
    }
    static int findRegions(int n , int m , int arr[][]){
        DisjointSet regions = new DisjointSet(n);
        for(int i = 0; i < m ; i++){
            regions.union(arr[i][0],arr[i][1]);
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n ; i++ ){
            set.add(regions.find(i));
        }
        return set.size();
    }
}