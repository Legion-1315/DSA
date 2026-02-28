//Using DFS O(V+E) time O(V) space
class Solution {
  public:
  
    bool dfs(vector<vector<int>> &adj, bool vis[], bool pathVis[], int node) {
        vis[node] = 1;
        pathVis[node] = 1;
        for(auto i : adj[node]) {
            if(!vis[i]) {
                if(dfs(adj, vis, pathVis, i))
                    return true;
            }
            if(pathVis[i]) {
                return true;
            }
        }
        pathVis[node] = false;
        return false;
    }
  
    bool isCyclic(int V, vector<vector<int>> &edges) {
        // code here
        bool vis[V] = {false};
        bool pathVis[V] = {false};
        
        vector<vector<int>>adj(V);
        for(auto i : edges) {
            adj[i[0]].push_back(i[1]);
        }
        
        for(int i=0;i<V;i++) {
            if(!vis[i]) {
                if(dfs(adj, vis, pathVis, i))
                    return true;
            }
        }
        return false;
    }
};

//Using Topo sort Kahn's Algorithm O(V+E) time O(V) space
class Solution {
  public:
    bool isCyclic(int V, vector<vector<int>> &edges) {
        // code here
        int cnt=0;
        vector<vector<int>>adj(V);
        vector<int> inDegree(V,0);
        for(auto i : edges) {
            adj[i[0]].push_back(i[1]);
            inDegree[i[1]]++;
            cnt++;
        }
        
        queue<int>q;
        for(int i=0;i<V;i++) {
            if(inDegree[i] == 0) {
                q.push(i);
            }
        }
        while(!q.empty()) {
            int node = q.front();
            q.pop();
            for(auto i : adj[node]) {
                inDegree[i]--;
                cnt--;
                if(inDegree[i] == 0) {
                    q.push(i);
                }
            }
        }
        return cnt == 0 ? false : true;
    }
};