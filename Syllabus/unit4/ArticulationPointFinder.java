import java.util.*;

public class ArticulationPointFinder {
    private int timer = 1;
    private int[] tin, low;
    private boolean[] visited;
    private Set<Integer> articulationPoints = new HashSet<>();

    public Set<Integer> findArticulationPoints(int n, List<List<Integer>> graph) {
        tin = new int[n];
        low = new int[n];
        visited = new boolean[n];

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                dfs(v, -1, graph);
            }
        }

        return articulationPoints;
    }

    private void dfs(int v, int parent, List<List<Integer>> graph) {
        visited[v] = true;
        tin[v] = low[v] = timer++;
        int children = 0;

        for (int to : graph.get(v)) {
            if (to == parent) continue;

            if (visited[to]) {
                // back edge
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v, graph);
                low[v] = Math.min(low[v], low[to]);

                // check for articulation point
                if (parent != -1 && low[to] >= tin[v]) {
                    articulationPoints.add(v);
                }

                children++;
            }
        }

        // special case for root
        if (parent == -1 && children > 1) {
            articulationPoints.add(v);
        }
    }

    // Helper to build the graph
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
        int n = 5;
        int[][] edges = {
            {0, 1},
            {1, 2},
            {1, 3},
            {3, 4}
        };

        ArticulationPointFinder finder = new ArticulationPointFinder();
        List<List<Integer>> graph = buildGraph(n, edges);
        Set<Integer> points = finder.findArticulationPoints(n, graph);

        System.out.println("Articulation Points:");
        for (int v : points) {
            System.out.println(v);
        }
    }
}
