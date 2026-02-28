//Time Complexity: O(n + m)
class Solution {
  public:
    bool canFinish(int n, vector<vector<int>>& edges) {
        // Code here
        vector<vector<int>>adj(n);
        for(auto i : edges) {
            adj[i[1]].push_back(i[0]);
        }
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
            for(auto i : adj[node]) {
                inDegree[i]--;
                if(inDegree[i] == 0) {
                    q.push(i);
                }
                cnt--;
            }
        }
        return cnt == 0 ? true : false;
    }
};