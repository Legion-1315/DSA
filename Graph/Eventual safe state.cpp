//Time Complexity: O(V + E)
// Auxiliary Space: O(V)
class Solution {
  public:
  
    vector<int> safeNodes(int V, vector<vector<int>>& edges) {
        queue<int>q;
        vector<int> inDegree(V,0);
        vector<vector<int>>adj(V);
        for(auto i : edges) {
            adj[i[1]].push_back(i[0]);
            inDegree[i[0]]++;
        }
        for(int i=0;i<V;i++) {
            if(inDegree[i] == 0) {
                q.push(i);
            }
        }
        vector<int>ans;
        while(!q.empty()) {
            int node = q.front();
            q.pop();
            ans.push_back(node);
            for(auto i : adj[node]) {
                inDegree[i]--;
                if(inDegree[i] == 0) {
                    q.push(i);
                }
            }
        }
        return ans;
    }
};