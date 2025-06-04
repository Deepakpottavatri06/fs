import java.util.*;

public class BridgeFinder{
    private int timer = 1;
    private int[] tin, low;
    private boolean[] visited;
    private List<List<Integer>> bridges = new ArrayList<>();

    public List<List<Integer>> findBridges(int n, List<List<Integer>> graph) {
        tin = new int[n];
        low = new int[n];
        visited = new boolean[n];

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                dfs(v, -1, graph);
            }
        }

        return bridges;
    }

    private void dfs(int v, int parent, List<List<Integer>> graph) {
        visited[v] = true;
        tin[v] = low[v] = timer++;

        for (int to : graph.get(v)) {
            if (to == parent) continue;

            if (visited[to]) {
                // Back edge
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v, graph);
                low[v] = Math.min(low[v], low[to]);

                // Check bridge condition
                if (low[to] > tin[v]) {
                    bridges.add(Arrays.asList(v, to));
                }
            }
        }
    }

    // Utility function to create the adjacency list
    public static List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return graph;
    }

    // Sample usage
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
            {0, 1},
            {0, 2},
            {2, 3}
        };

        BridgeFinder bf = new BridgeFinder();
        List<List<Integer>> graph = buildGraph(n, edges);
        List<List<Integer>> bridges = bf.findBridges(n, graph);

        System.out.println("Bridges:");
        for (List<Integer> bridge : bridges) {
            System.out.println(bridge.get(0) + " - " + bridge.get(1));
        }
    }
}
