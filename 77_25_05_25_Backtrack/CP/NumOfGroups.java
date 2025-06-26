/*BCCI wants to select the group of bowlers for an upcoming test-series, 
you want to choose the group with highest number of wickets, which is 
the sum of wickets taken by all the bowlers in that group.

However, the bowler group is not allowed to have any disputes. A dispute
exists if a younger bowler has strictly highest wickets than an older bowler. 
A dispute does not occur between bowlers of the same age.

You are given information of N bowlers as two lists, wickets and ages, 
where each wickets[i] and ages[i] represents the wickets and age of 
the i-th bowler, respectively, return the highest number of wickets 
of all possible bowler groups.


Input Format:
-------------
Line-1: An integer N, number of bowlers.
Line-2: N space separated integers, wickets[].
Line-3: N space separated integers, ages[].

Output Format:
--------------
An integer, highest number of wickets of all possible bowler groups.


Sample Input-1:
---------------
4
5 3 5 6
3 5 4 2

Sample Output-1:
----------------
10

Explanation:
------------
It is best to choose 2 bowlers of age 3 and 4. 


Sample Input-2:
---------------
5
5 3 5 6 3
2 5 4 2 1

Sample Output-2:
----------------
14

Explanation:
------------
It is best to choose 3 bowlers of age 1 and 2. 
Notice that you are allowed to choose multiple bowlers of the same age.

Sample Input-3:
---------------
5
1 3 5 10 15
1 2 3 4 5

Sample Output-3:
----------------
34

Explanation:
------------
You can choose all the bowlers.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NumOfGroups{
    public static void main (String[] args) {
        Scanner cin = new Scanner(System.in);
        List<int[]> arr = new ArrayList<>();
        int n = cin.nextInt();
        int array[][] = new int[n][n];
        for(int i = 0; i < n ; i++){
            array[0][i] = cin.nextInt();
        }
        for(int i = 0; i < n ; i++){
            array[1][i] = cin.nextInt();
        }
        for(int i = 0; i < n; i++ ){
            arr.add(new int[]{array[0][i],array[1][i]});
        }
        System.out.println(find(arr,n));
        cin.close();
    }
    static int find(List<int[]> arr, int n){
        Collections.sort(arr,(a,b)->(a[1]==b[1])? a[0]-b[0]:a[1]-b[1]);
        
        Map<String,Integer> map = new HashMap<>();
        return dfs(arr,0,-1,map);
    }
    static int dfs(List<int[]> arr, int ind, int prev,Map<String,Integer> map){
        if(ind>=arr.size()){
            return 0;
        }
        String key = ind+","+prev;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int opt1 = dfs(arr,ind+1,prev,map);
        int opt2 = 0;
        
        if(prev==-1 || ((arr.get(ind)[0]>=arr.get(prev)[0]))){
            opt2 = arr.get(ind)[0] + dfs(arr,ind+1,ind,map);
        }
        map.put(key,Math.max(opt1,opt2));
        return Math.max(opt1,opt2);
    }
}