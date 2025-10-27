import java.util.*;

public class Hspot{
    public static void main (String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            int n = cin.nextInt();
            int r = cin.nextInt();
            int d = cin.nextInt();
            List<List<int[]>> map = new ArrayList<>();
            for(int i = 0; i < n; i++){
                map.add(new ArrayList<>());
            }
            for(int i = 0; i < r; i++){
                int from = cin.nextInt();
                int to = cin.nextInt();
                int dist = cin.nextInt();
                map.get(from).add(new int[]{to,dist});
                map.get(to).add(new int[]{from,dist});
            }
            System.out.println(find(map,d,n));
        }
    }
    static int find(List<List<int[]>> map , int d, int n){
        int res = Integer.MAX_VALUE;
        int best = 0;
        for(int i = 0; i < n; i++){
            // Set<Integer> set = new HashSet<>();
            // dfs(set,map,i,i,d);
            // if(res>set.size()){
            //     res = set.size();
            //     best = i;
            // }
            // if(res==set.size()){
            //     best = Math.max(best,i);
            // }
            int val = modifiedDijkstra(map,i,d); 
            if(res>val){
                res = val;
                best = i;
            }
            else if(res==val){
                best = Math.max(best,i);
            }
        }
        
        return best;
    
    }
    static int modifiedDijkstra(List<List<int[]>> map , int p, int d){
        int[] dist = new int[map.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[p] = 0;  
    
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.add(new int[]{p, 0});
    
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int u = node[0];
            int currDist = node[1];
    
            if(currDist > dist[u]) continue; 
    
            for(int[] nbr : map.get(u)){
                int v = nbr[0];
                int w = nbr[1];
                int newD = currDist + w;
    
                if(newD < dist[v] && newD <= d){
                    dist[v] = newD;
                    pq.add(new int[]{v, newD});
                }
            }
        }
    
        int count = 0;
        for(int i = 0; i < dist.length; i++){
            if(i != p && dist[i] <= d){
                count++;
            }
        }
        return count;
    }
    static void dfs(Set<Integer> set,List<List<int[]>> map , int curr, int p, int d){
        if(d==0) return;
        for(int node[]: map.get(curr)){
            if(node[1]<=d && node[0]!=p){
                set.add(node[0]);
                dfs(set,map,node[0],p,d-node[1]);
            }
        }
    }
    
}