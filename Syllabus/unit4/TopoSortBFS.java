import java.util.*;

public class TopoSortBFS {
    public static List<Integer> topologicalSort(int V, List<List<Integer>> adj) {
        int[] inDegree = new int[V];             // Step 1: Track in-degrees
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                inDegree[v]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();  // Step 2: Start with nodes of in-degree 0
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();

        while (!queue.isEmpty()) {              // Step 3: BFS processing
            int u = queue.poll();
            topoOrder.add(u);

            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        if (topoOrder.size() != V) {
            throw new RuntimeException("Graph has a cycle, topological sort not possible");
        }

        return topoOrder;
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Example DAG:
        // 5 → 2, 5 → 0, 4 → 0, 4 → 1, 2 → 3, 3 → 1
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        List<Integer> result = topologicalSort(V, adj);
        System.out.println("Topological Sort (BFS): " + result);
    }
}
