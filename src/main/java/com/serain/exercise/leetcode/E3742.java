package com.serain.exercise.leetcode;

public class E3742 {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j][c] 表示到达位置 (i,j) 且花费为 c 时的最大分数
        // 最大花费不会超过 m*n（每步最多花费1），但实际限制是 k
        int maxCost = Math.min(k, m + n);
        int[][][] dp = new int[m][n][maxCost + 1];
        
        // 初始化为 -1 表示不可达
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= maxCost; c++) {
                    dp[i][j][c] = -1;
                }
            }
        }
        
        // 初始化起点
        int startCost = grid[0][0] == 0 ? 0 : 1;
        int startScore = grid[0][0];
        if (startCost <= k) {
            dp[0][0][startCost] = startScore;
        } else {
            return -1;
        }
        
        // 动态规划
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= maxCost; c++) {
                    if (dp[i][j][c] == -1) continue;
                    
                    int currentScore = dp[i][j][c];
                    
                    // 向右移动
                    if (j + 1 < n) {
                        int nextCost = c + (grid[i][j + 1] == 0 ? 0 : 1);
                        int nextScore = currentScore + grid[i][j + 1];
                        if (nextCost <= k && nextScore > dp[i][j + 1][nextCost]) {
                            dp[i][j + 1][nextCost] = nextScore;
                        }
                    }
                    
                    // 向下移动
                    if (i + 1 < m) {
                        int nextCost = c + (grid[i + 1][j] == 0 ? 0 : 1);
                        int nextScore = currentScore + grid[i + 1][j];
                        if (nextCost <= k && nextScore > dp[i + 1][j][nextCost]) {
                            dp[i + 1][j][nextCost] = nextScore;
                        }
                    }
                }
            }
        }
        
        // 找出终点的最大分数
        int maxScore = -1;
        for (int c = 0; c <= maxCost; c++) {
            maxScore = Math.max(maxScore, dp[m - 1][n - 1][c]);
        }
        
        return maxScore;
    }
}
