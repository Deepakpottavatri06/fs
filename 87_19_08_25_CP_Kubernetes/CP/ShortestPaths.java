import java.util.*;

public class ShortestPaths {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int c = cin.nextInt();
        int a = cin.nextInt();
        
        Map<Integer, List<Integer>> concrete = new HashMap<>();
        Map<Integer, List<Integer>> asphalt = new HashMap<>();
        
        for (int i = 0; i < c; i++) {
            int u = cin.nextInt();
            int v = cin.nextInt();
            concrete.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        for (int i = 0; i < a; i++) {
            int u = cin.nextInt();
            int v = cin.nextInt();
            asphalt.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        
        int[] result = bfs(n, concrete, asphalt);
        for (int x : result) System.out.print(x + " ");
        cin.close();
    }
    
    static int[] bfs(int n, Map<Integer, List<Integer>> concrete, Map<Integer, List<Integer>> asphalt) {
        int[][] dist = new int[n][2]; // [city][lastEdgeType]
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        
        Queue<int[]> q = new LinkedList<>();
        
        // Start from city-0 with both road types as "free start"
        dist[0][0] = 0; 
        dist[0][1] = 0;
        q.offer(new int[]{0, 0}); // last was concrete
        q.offer(new int[]{0, 1}); // last was asphalt
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0], type = curr[1];
            int nextType = 1 - type; // alternate road
            
            List<Integer> neighbors = (nextType == 0) ? 
                concrete.getOrDefault(node, new ArrayList<>()) : 
                asphalt.getOrDefault(node, new ArrayList<>());
            
            for (int nei : neighbors) {
                if (dist[nei][nextType] > dist[node][type] + 1) {
                    dist[nei][nextType] = dist[node][type] + 1;
                    q.offer(new int[]{nei, nextType});
                }
            }
        }
        
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int d = Math.min(dist[i][0], dist[i][1]);
            ans[i] = (d == Integer.MAX_VALUE) ? -1 : d;
        }
        return ans;
    }
}