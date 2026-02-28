//Time Complexity: O(n + m)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

 class GFG {

     static ArrayList<Integer> findOrder(int n, int[][] prerequisites) {

        // Initialize adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[n];

        // Build graph
        for (int[] pre : prerequisites) {
            int dest = pre[0];
            int src = pre[1];
            adj.get(src).add(dest);
            inDegree[dest]++;
        }

        // Queue for BFS
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        ArrayList<Integer> order = new ArrayList<>();

        // Topological Sort using BFS
        while (!q.isEmpty()) {
            int current = q.poll();
            order.add(current);

            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        // Return result only if all courses can be completed
        if (order.size() == n) {
            return order;
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] prerequisites = {  {2, 0}, {2, 1}, {3, 2}  };

        ArrayList<Integer> result = findOrder(n, prerequisites);

        for (int course : result) {
            System.out.print(course + " ");
        }
        System.out.println();
    }
}