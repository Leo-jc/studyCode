#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
    int n, m;
    cin >> n >> m;
    vector<int> w(n + 1);
    for(int i = 1; i <= n; i++){
        cin >> w[i];
    }
    vector<int> inDegree(n + 1, 0);
    vector<vector<int>> graph(n + 1);
    for(int i = 0; i < m; i++){
        int u, v;
        cin >> u >> v;
        graph[v].push_back(u);
        inDegree[u]++;
    }
    int ans = 0;
    queue<vector<int>> q;
    for(int i = 1; i <= n; i++){
        if(inDegree[i] == 0){
            q.push(vector<int>{i, w[i]});
        }
    }
    while(!q.empty()){
        vector<int> first = q.front();
        q.pop();
        if(q.empty()){
            ans += first[1];
            for(int i = 0; i < graph[first[0]].size(); i++){
                int nxt = graph[first[0]][i];
                inDegree[nxt]--;
                if(inDegree[nxt] == 0){
                    q.push(vector<int>{nxt, w[nxt]});
                }
            }
        } else {
            vector<int> second = q.front();
            q.pop();
            if(first[1] > second[1]){
                swap(first, second);
            }
            ans += first[1];
            second[1] -= first[1];
            q.push(second);
            for(int i = 0; i < graph[first[0]].size(); i++){
                int nxt = graph[first[0]][i];
                inDegree[nxt]--;
                if(inDegree[nxt] == 0){
                    q.push(vector<int>{nxt, w[nxt]});
                }
            }
        }
    }

    for(int i = 1; i <= n; i++){
        if(inDegree[i] > 0){
            cout << -1 << endl;
            return 0;
        }
    }
    cout << ans << endl;

    return 0;
}
// 64 位输出请用 printf("%lld")
