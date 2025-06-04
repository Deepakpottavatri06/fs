import java.util.*;

public class ParallelCourses {
    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[n + 1];

        // Build graph and in-degree
        for (int[] rel : relations) {
            int u = rel[0], v = rel[1];
            adj.get(u).add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        // Start with all courses that have no prerequisites
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int semester = 0;
        int coursesTaken = 0;

        // Level-order BFS (each level = one semester)
        while (!queue.isEmpty()) {
            int size = queue.size(); // number of courses we can take in parallel this semester
            semester++;
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                coursesTaken++;

                for (int neighbor : adj.get(course)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return coursesTaken == n ? semester : -1;
    }

    public static void main(String[] args) {
        ParallelCourses obj = new ParallelCourses();

        int n = 4;
        int[][] prerequisites = {
            {2, 1},
            {3, 1},
            {4, 2},
            {4, 3}
        };

        int semesters = obj.minimumSemesters(n, prerequisites);
        System.out.println("Minimum semesters needed: " + semesters);  // Output: 3
    }
}
