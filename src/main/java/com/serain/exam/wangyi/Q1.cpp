#include <iostream>
#include <vector>
using namespace std;

int dir[4][2] = {{0,-1}, {0,1}, {-1,0}, {1,0}};

void getAns(vector<vector<int>>& ans, const vector<vector<char>>& map, int n, int m) {
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(map[i][j] == '.'){
                continue;
            }
            if(map[i][j] == '#' || map[i][j] == '\\' || map[i][j] == '/'){
                ans[i][j] = -1;
            }
            int d = 0;
            if(map[i][j] == 'L'){
                d = 0;
            }
            if(map[i][j] == 'R'){
                d = 1;
            }
            if(map[i][j] == 'U'){
                d = 2;
            }
            if(map[i][j] == 'D'){
                d = 3;
            }
            int x = i + dir[d][0];
            int y = j + dir[d][1];
            while(x >= 0 && x < n && y >= 0 && y < m){
                if(map[x][y] == '#' || map[x][y] == 'L' || map[x][y] == 'R' || map[x][y] == 'U' || map[x][y] == 'D') break;
                if(map[x][y] == '.'){
                    ans[x][y]++;
                }
                else if(map[x][y] == '\\'){
                    d = (d + 2) % 4;
                }
                else if(map[x][y] == '/'){
                    d = 3 - d;
                }
                x += dir[d][0];
                y += dir[d][1];
            }
        }
    }
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cout << ans[i][j] << " ";
        }
        cout << endl;
    }
}

int main() {
    int n, m;
    cin >> n >> m;
    vector<vector<char>> map(n, vector<char>(m));
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> map[i][j];
        }
    }
    vector<vector<int>> ans(n, vector<int>(m, 0));
    getAns(ans, map, n, m);
    return 0;
}
// 64 位输出请用 printf("%lld")
