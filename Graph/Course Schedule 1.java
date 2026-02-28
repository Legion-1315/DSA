//Time Complexity: O(n + m)
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

class GFG {
    static boolean canFinish(int n, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            int dest = pre[0];
            int src = pre[1];
            graph.get(src).add(dest);
            inDegree[dest]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // Push all the nodes with no dependencies (indegree = 0)
        for (int i = 0; i < n; i++)
            if (inDegree[i] == 0)
                q.add(i);

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int child : graph.get(node)) {
                inDegree[child]--;

                // Push the neighboring node if 
                // we have covered all its dependencies (indegree = 0)
                if (inDegree[child] == 0)
                    q.add(child);
            }
        }

        // Check if there is a node whose indegree is not zero
        for (int i = 0; i < n; i++)
            if (inDegree[i] != 0)
                return false;

        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] prerequisites = { {2, 0}, {2, 1}, {3, 2} };

        System.out.println(canFinish(n, prerequisites) ? "true" : "false");
    }
}