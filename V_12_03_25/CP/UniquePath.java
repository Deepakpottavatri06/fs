/*Imagine you are designing a treasure hunt that takes participants through 
n unique landmarks, each identified by a distinct number from 1 to n. 
The official treasure map provides an itinerary—called the route—which is a 
complete ordering (a permutation) of these landmarks. Along the way, m clues 
are given in the form of several smaller landmark sequences. Each clue is a pair
of landmarks that appear in the route in the same order, though not necessarily 
consecutively.

Your challenge is to determine whether the provided route is both the shortest 
possible itinerary that incorporates all the clues and the only such itinerary. 
The shortest itinerary is defined as one that contains every clue as an ordered 
subsequence while including no extra landmarks beyond what is necessary. Although
there might be multiple itineraries that include all clues, the route must be 
unique and minimal for your treasure hunt to be considered well-defined.

Display valid if the official route is the only shortest itinerary that fits all 
the clues, or invalid otherwise.


Input format:
-------------
Line 1: n m, space seperated 2 integers
Line 2: n space seperated integers, representing official route
Line 3: m lines of clues, each clue has 2 space separated integers

Output format:
---------------
official route is valid or official route is invalid.


Example 1:  
input=
3 2
1 3 2
1 2
1 3
output=
[1, 3, 2] is invalid

Explanation: 
- There are two possible itineries: [1,2,3] and [1,3,2].
- The clue [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
- The clue [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
- Since there are multiple valid shortest itineraries, the official route is not unique.

Example 2: 
input=
3 1
1 2 3
1 2
output=
[1, 2, 3] is invalid

Explanation: 
- The shortest possible itinerary is [1,2].
- The clue [1,2] is a subsequence of it: [1,2].
- Since official route is not the shortest itinerary, we return false.

Example 3:
input=
3 3
1 2 3
1 2
1 3
2 3
Output=
[1, 2, 3] is valid
  
Explanation: 
- The only possible shortest itinerary that contains all the clues is [1, 2, 3]. 
- No alternative minimal route exists.  */
import java.util.*;

public class UniquePath{
    public static void main(String [] args){
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt();
        int deps [][] = new int[m][2];
        
        int ogPath [] = new int[n];
        for(int i = 0; i < ogPath.length; i++){
            ogPath[i] = cin.nextInt();
        }
        for(int i = 0; i < m ; i++){
            deps[i][0] = cin.nextInt();
            deps[i][1] = cin.nextInt();
        }
        System.out.println(findValidPath(n,m,ogPath,deps));
        cin.close();
    }
    static String findValidPath(int n , int m , int ogPath [],int deps[][] ){
        List<List<Integer>> adjList =  new ArrayList<>();
        for(int i = 0; i < n+1; i++){
            adjList.add(new ArrayList<>());
        }
        Map<Integer,Integer> indegree = new HashMap<>();
        for(int i = 0; i < m ; i++){
            adjList.get(deps[i][0]).add(deps[i][1]);
            indegree.put(deps[i][1],indegree.getOrDefault(deps[i][1],0)+1);
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n ; i++){
            if(!indegree.containsKey(i)){
                q.add(i);
            }
        }
        
        // if(q.size()>1){
        //     return Arrays.toString(ogPath) +" is invalid";
        // }
        Set<Integer> visited = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()){
            
            int top = q.poll();
            if(q.size()!=0){
                return Arrays.toString(ogPath) +" is invalid";
            }
            visited.add(top);
            res.add(top);
            for(int i : adjList.get(top)){
                if(!visited.contains(i)){
                    indegree.put(i,indegree.get(i)-1);
                    if(indegree.get(i)==0){
                        q.add(i);
                    }
                }
            }
        }

        boolean flag = Arrays.toString(ogPath).equals(res.toString());
        // for(int i = 0; i < n ; i++){
        //     if(ogPath[i]!=res.get(i)){
        //         flag = false;
        //         break;
        //     }
        // }
        
        return (flag)? Arrays.toString(ogPath) +" is valid" : Arrays.toString(ogPath) +" is invalid";
        
    }
}