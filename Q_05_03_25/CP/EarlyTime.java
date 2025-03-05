/*There are N people in a private party. Initially all are strangers to each other,
and the people are identified by unique ID from 0 to N-1.

In the party, whenever two persons (person-A and person-B) become friends, they 
took a photo. Each of the photo has some information, photos[i]=[T-i, P-j,P-k],
here T-i indicates time of the photo taken, P-j person with ID 'j', and 
P-k indicates person with ID 'k'.

Friendship is symmetric[i.e., If P-j is friend of P-k, then P-k is a friend of P-j].
Additionally, if person-A is "a friend of person-B OR a friend of someone who is 
friend of person-B", then person-A is friend of person-B.

You are given L photos information, Your task is to find the earliest time 
for which every person became friend with every other person in the party.
If there is no such earliest time, return -1.


Input Format:
-------------
Line-1: Two space separated integers, N and L.
Next L lines: Three space separated integers, log[i], 0<=i<L.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
6 8
5 0 1
7 3 4
12 2 3
21 1 5
34 2 4
37 0 3
42 1 2
93 4 5

Sample Output-1:
----------------
37


Sample Input-2:
---------------
7 6
2 0 3
5 1 5
8 2 5
7 3 6
9 4 6
6 4 5

Sample Output-2:
----------------
9
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

public class EarlyTime{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int e = cin.nextInt();
        int arr[][] = new int[e][3];
        for(int i = 0; i < e ; i++){
            arr[i][0] = cin.nextInt();
            arr[i][1] = cin.nextInt();
            arr[i][2] = cin.nextInt();
        }
        System.out.println(findEarlyTime(n,e,arr));
        cin.close();
    }
    static int findEarlyTime(int n, int e, int[][] arr){
        Arrays.sort(arr, (a,b)->a[0]-b[0]);
        int earlyTime = 0;
        DisjointSet dsu = new DisjointSet(n);
        for(int i = 0; i < e ; i++){
            boolean t = dsu.union(arr[i][1],arr[i][2]);
            if(t){
                earlyTime = arr[i][0];
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i< n ; i++){
            set.add(dsu.find(i));
        }
        return (set.size()==1)?earlyTime:-1;
        
    }
}

