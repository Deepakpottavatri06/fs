import java.util.*;

public class TopoSortDFS {
    public static void dfs(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack, adj);
            }
        }

        stack.push(node);  // Push after visiting all descendants
    }

    public static List<Integer> topologicalSort(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack, adj);
            }
        }

        List<Integer> topoOrder = new ArrayList<>();
        while (!stack.isEmpty()) {
            topoOrder.add(stack.pop());
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
        System.out.println("Topological Sort (DFS): " + result);
    }
}
