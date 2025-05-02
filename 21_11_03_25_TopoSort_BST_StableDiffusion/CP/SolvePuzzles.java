/*Imagine you're hosting a mystery treasure hunt with n puzzles numbered from 1 to n. 
Some puzzles have hidden clues that can only be uncovered after solving certain 
earlier puzzles. You’re provided with m number of dependencies, where each dependency
is given as [earlierPuzzle, laterPuzzle]—meaning you must solve the puzzle 
earlierPuzzle before attempting laterPuzzle. Additionally, you can work on at most k
puzzles in a single day, but only if you’ve already solved all the prerequisite 
puzzles on previous days.

Determine the minimum number of days required to solve all the puzzles. It is 
guaranteed that the dependencies allow every puzzle to eventually be solved.


Input format:
Line 1: 3 space separated integers n m & k
Line 2: m lines of dependencies

Output format:
An integer, minimum number of days.

Example 1:
input=
4 3 2
2 1
3 1
1 4
output=
3

Explanation:  
- On Day 1, you solve puzzles 2 and 3.  
- On Day 2, having unlocked the clue, you solve puzzle 1.  
- On Day 3, you solve puzzle 4.

Example 2:
input=
5 4 2
2 1
3 1
4 1
1 5
output=
4

Explanation:  
- On Day 1, you can solve puzzles 2 and 3 (you can’t solve more than two in a day).  
- On Day 2, you solve puzzle 4.  
- On Day 3, you solve puzzle 1.  
- On Day 4, you finally solve puzzle 5.

Constraints:
• 1 <= n <= 15  
• 1 <= k <= n  
• 0 <= m <= n * (n-1) / 2  
• Each dependency has exactly two elements  
• 1 <= earlierPuzzle, laterPuzzle <= n  
• earlierPuzzle != laterPuzzle  
• All dependency pairs are unique  
• The dependency graph forms a directed acyclic graph
 */
import java.util.*;

public class SolvePuzzles{
    public static void main(String [] args){
        Scanner cin =  new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt();
        int k = cin.nextInt();
        int dep[][] = new int[m][2];
        for(int i =0; i < m ; i++){
            dep[i][0]=cin.nextInt();
            dep[i][1]=cin.nextInt();
        }
        System.out.println(minDays(n,m,k,dep));
        cin.close();
    }
    static int minDays(int n , int m , int k , int dep[][]){
        List<List<Integer>> adjList = new ArrayList<>();
        Map<Integer,Integer> indegree = new HashMap<>();
        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            adjList.get(dep[i][0]).add(dep[i][1]);
            indegree.put(dep[i][1],indegree.getOrDefault(dep[i][1],0)+1);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer[]> q = new ArrayDeque<>();
        int count = 0;
        for(int i=1;i<=n;i++){
            if(!indegree.containsKey(i)){
                q.add(new Integer[]{i,count/k});
                count++; 
            }
        }

        
        // int mindays = 0;
        // int solved = 0;
        // while(!q.isEmpty()){
        //         Integer top []= q.poll();
        //         visited.add(top[0]);
        //         solved++;

        //         if(solved==k || q.isEmpty()){
        //             solved =0 ;
        //             mindays++;
        //         }
        //         for(int j : adjList.get(top[0])){
        //             if(!visited.contains(j)){
        //                 indegree.put(j,indegree.get(j)-1);
        //                 if(indegree.get(j)==0){
        //                     q.add(new Integer[]{j,top[1]+1});
        //                 }
        //             }
        //         }
                
            
        // }

        List<Integer> output = new ArrayList<>();
        int mindays = 0;
        while(!q.isEmpty()){
            int levelPeek = q.peek()[1];
            int numOfOps = Math.min(q.size(),k);
            for(int i = 0; i<numOfOps; i++){
                Integer top []= q.peek();
                if(levelPeek!=top[1]) {
                    break;
                }
                q.poll();
                visited.add(top[0]);
                output.add(top[0]);
                for(int j : adjList.get(top[0])){
                    if(!visited.contains(j)){
                        indegree.put(j,indegree.get(j)-1);
                        if(indegree.get(j)==0){
                            q.add(new Integer[]{j,top[1]+1});
                        }
                    }
                }
                
            }
            System.out.println(output);
            output.clear();
            mindays++;
        }
        return  mindays;
    }
    
}

/*      int mindays = 0;
        while(!q.isEmpty()){
            int levelPeek = q.peek()[1];
            int numOfOps = Math.min(q.size(),k);
            for(int i = 0; i<numOfOps; i++){
                Integer top []= q.peek();
                if(levelPeek!=top[1]) break;
                q.poll();
                visited.add(top[0]);
                for(int j : adjList.get(top[0])){
                    if(!visited.contains(j)){
                        indegree.put(j,indegree.get(j)-1);
                        if(indegree.get(j)==0){
                            q.add(new Integer[]{j,top[1]+1});
                        }
                    }
                }
                
            }
            mindays++;
        } */