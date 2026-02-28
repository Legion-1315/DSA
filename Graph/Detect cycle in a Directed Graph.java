//using DFS to detect cycle in a directed graph
import java.util.ArrayList;
public class GFG {

    // Utility DFS function to detect cycle in a directed graph
    static boolean isCyclicUtil(ArrayList<ArrayList<Integer>> adj, 
    int u, boolean[] visited, boolean[] recStack) {
        
        // Node already in recursion stack cycle found
        if (recStack[u]) return true;

        // Already processed no need to visit again
        if (visited[u]) return false;

        visited[u] = true;
        recStack[u] = true;

        // Recur for all adjacent nodes
        for (int v : adj.get(u)) {
            if (isCyclicUtil(adj, v, visited, recStack))
                return true;
        }

        // Remove from recursion stack before backtracking
        recStack[u] = false;
        return false;
    }

    // Function to detect cycle in a directed graph
    static boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        // Run DFS from every unvisited node
        for (int i = 0; i < V; i++) {
            if (!visited[i] && isCyclicUtil(adj, i, visited, recStack))
                return true;
        }
        return false;
    }
    
    // Function to add an edge to the adjacency list
    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v); 
    }

    public static void main(String[] args) {
        int V = 4;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add directed edges
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 2, 3);

        System.out.println(isCyclic(adj) ? "true" : "false");
    }
}

//using Kahn's Algorithm (BFS) to detect cycle in a directed graph

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class GFG {

    static boolean isCyclic(ArrayList<ArrayList<Integer>> adj)
    {
        int V = adj.size();
        
        // Array to store in-degree of each vertex
        int[] inDegree = new int[V]; 
        Queue<Integer> q = new LinkedList<>(); 
        
        // Count of visited (processed) nodes
        int visited = 0;           

        // Compute in-degrees of all vertices
        for (int u = 0; u < V; ++u)
        {
            for (int v : adj.get(u))
            {
                inDegree[v]++;
            }
        }

        // Add all vertices with in-degree 0 to the queue
        for (int u = 0; u < V; ++u)
        {
            if (inDegree[u] == 0)
            {
                q.add(u);
            }
        }

        // Perform BFS (Topological Sort)
        while (!q.isEmpty())
        {
            int u = q.poll();
            visited++;

            // Reduce in-degree of neighbors
            for (int v : adj.get(u))
            {
                inDegree[v]--;
                if (inDegree[v] == 0)
                {
                    // Add to queue when in-degree becomes 0
                    q.add(v);
                }
            }
        }

        // If visited nodes != total nodes, a cycle exists
        return visited != V;
    }
    
     // Function to add an edge to the adjacency list
    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v); 
    }

    public static void main(String[] args)
    {
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges
        addEdge(adj, 0, 1);
        addEdge(adj, 1, 2);
        addEdge(adj, 2, 0);
        addEdge(adj, 2, 3);

        System.out.println(isCyclic(adj) ? "true" : "false");
    }
}
