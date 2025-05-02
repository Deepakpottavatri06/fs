/*Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
spectacular dinner consisting of numDishes unique dishes, labeled from 
0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
only be prepared after completing others. You’re given a list called dependecies, 
where each element dependencies[i] = [Xi, Yi] means that you must finish 
preparing dish Yi before starting dish Xi.

For instance, the pair [2, 1] implies that to create dish 2, 
dish 1 must be prepared first.

Return the ordering of dishes that a chef should take to finish all dishes.
	- the result set should follow the given order of conditions.
	- If it is impossible to complete all dishes, return an empty set.


Input Format:
-------------
Line-1: An integer numDishes, number of Dishes.
Line-2: An integer m, number of dependencies.
Next m lines: Two space separated integers, Xi and Yi.

Output Format:
--------------
Return a list of integers, the ordering of dishes that a chef should finish.

Example 1:
------------
Input=
4
3
1 2
3 0
0 1
Output=
[2, 1, 0, 3]


Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.


Example 2:
----------
Input=
2
2
1 0
0 1
Output=
[]

Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
on dish 1. This circular dependency makes it impossible to prepare all dishes.

Constraints:

- 1 <= numDishes <= 2000  
- 0 <= m <= 5000  
- dependencies[i].length == 2  
- 0 <= Xi, Yi < numDishes  
- All the dependency pairs are unique.
 */
import java.util.*;

public class OrderDishes{
    public static void main(String [] args){
        Scanner cin =  new Scanner(System.in);
        int n = cin.nextInt();
        int d = cin.nextInt();
        int dependencies [][] = new int[d][2];
        for(int i = 0; i < d; i++){
            dependencies[i][0] = cin.nextInt();
             dependencies[i][1] = cin.nextInt();
        }
        
        findOrder(n,d,dependencies);
        cin.close();
    }
    static void findOrder(int n, int d, int dependencies [][]){
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < d; i++){
            adjList.get(dependencies[i][1]).add(dependencies[i][0]);
        }
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> st = new Stack<>();
        for(int i = 0;i<n; i++){
            if(!visited.contains(i)){
                dfs(i,visited,st,adjList);
            }
        }
        List<Integer> res =  new ArrayList<>();
        for(int i = 0; i < n; i++){
            res.add(st.pop());
        }
        System.out.println(st);
    }
    static void findOrderBFS(int n, int d, int dependencies [][]){
        List<List<Integer>> adjList = new ArrayList<>();
        Map<Integer,Integer> indegree = new HashMap<>();
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < d; i++){
            adjList.get(dependencies[i][1]).add(dependencies[i][0]);
            indegree.put(dependencies[i][0],indegree.getOrDefault(dependencies[i][0],0)+1);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n ; i++){
            if(!indegree.containsKey(i)){
                q.add(i);
            }
        }
        Set<Integer> visited = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()){
            int top = q.poll();
            visited.add(top);
            res.add(top);
            for(int i:adjList.get(top)){
                if(!visited.contains(i)){
                    indegree.put(i,indegree.get(i)-1);
                    if(indegree.get(i)==0){
                        q.add(i);
                    }
                }
            }
        }
        // System.out.println(adjList);
        

        System.out.println(res);
    }
    static void dfs(int v, Set<Integer> visited,Stack<Integer> st ,List<List<Integer>> adjList){

        visited.add(v);
        for(int i:adjList.get(v)){
            if(!visited.contains(i)){
                dfs(i,visited,st,adjList);
            }
        }
        st.push(v);
    }
}