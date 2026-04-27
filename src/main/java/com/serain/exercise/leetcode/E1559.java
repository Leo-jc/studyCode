package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Serain
 * @CreateTime: 2026-04-26  12:58
 * @Description: TODO
 * @Version: 1.0
 */
public class E1559 {
        public static  final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        public boolean containsCycle(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && dfs(grid, i, j,-1,-1, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }
        private boolean dfs(char[][] grid, int i, int j,int preI, int preJ, boolean[][] visited) {
            visited[i][j] = true;
            for(int k=0;k<4;k++){
                int nx = i + dirs[k][0];
                int ny = j + dirs[k][1];
                if(nx==preI && ny==preJ) continue;
                if(nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length) continue;
                if(grid[nx][ny] != grid[i][j]) continue;
                if(visited[nx][ny]||dfs(grid, nx, ny, i, j, visited)) return true;
            }
            return false;
        }
}
