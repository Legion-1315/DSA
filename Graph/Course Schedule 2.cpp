//Time Complexity: O(n + m)
class Solution {
  public:
  
    vector<int> findOrder(int n, vector<vector<int>> &edges) {
        // code here
        vector<vector<int>>adj(n);
        for(auto i : edges) {
            adj[i[1]].push_back(i[0]);
        }
        vector<int>ans;
        int cnt = 0;
        int inDegree[n] {0};
        for(auto i : adj) {
            for(auto j : i) {
                inDegree[j]++;
                cnt++;
            }
        }
        queue<int>q;
        for(int i=0;i<n;i++) {
            if(inDegree[i] == 0) {
                q.push(i);
            }
        }
        while(!q.empty()) {
            int node = q.front();
            q.pop();
            ans.push_back(node);
            for(auto i : adj[node]) {
                inDegree[i]--;
                if(inDegree[i] == 0) {
                    q.push(i);
                }
                cnt--;
            }
        }
        if (cnt == 0)
            return ans;
        return {}; 
    }
};