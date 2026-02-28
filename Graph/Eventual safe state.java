//Driver Code Starts
import java.util.*;

public class Main {

//Driver Code Ends
    static ArrayList<Integer> safeNodes(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();

        ArrayList<ArrayList<Integer>> revAdj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            revAdj.add(new ArrayList<>());

        int[] indegree = new int[V];

        // Build reversed adjacency list 
        // and compute indegree for each node
        for (int i = 0; i < V; i++) {
            for (int nextNode : adj.get(i)) {
                revAdj.get(nextNode).add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        // Push all terminal nodes 
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Kahn's Algo on reversed graph
        while (!q.isEmpty()) {
            int curNode = q.poll();
            ans.add(curNode);

            for (int prevNode : revAdj.get(curNode)) {
                indegree[prevNode]--;
                if (indegree[prevNode] == 0) {
                    q.add(prevNode);
                }
            }
        }

        // Return safe nodes 
        return ans;
    }
//Driver Code Starts

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
    }

    public static void main(String[] args) {
        int n = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        // creating adjacency list
        addEdge(adj, 1, 0);
        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);

        ArrayList<Integer> res = safeNodes(adj);
        Collections.sort(res);

        for (int vertex : res)
            System.out.print(vertex + " ");
        System.out.println();
    }
}

//Driver Code Ends