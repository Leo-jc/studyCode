package com.serain.exercise.leetcode;

import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-02  13:07
 * @Description: TODO
 * @Version: 1.0
 */
public class E3418 {
    public int maximumAmount(int[][] coins) {
        if (coins == null || coins.length == 0 || coins[0].length == 0) {
            return 0;
        }
        
        int m = coins.length;
        int n = coins[0].length;
        int[][][] dp = new int[m + 1][n + 1][3];
        
        for (int[] row : dp[0]) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        Arrays.fill(dp[0][1], 0);
        
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i + 1][0], Integer.MIN_VALUE);
            for (int j = 0; j < n; j++) {
                int coin = coins[i][j];
                
                // 提取公共子表达式，避免重复计算
                int maxPrev0 = Math.max(dp[i][j + 1][0], dp[i + 1][j][0]);
                int maxPrev1 = Math.max(dp[i][j + 1][1], dp[i + 1][j][1]);
                int maxPrev2 = Math.max(dp[i][j + 1][2], dp[i + 1][j][2]);
                
                // 状态 0：始终累加（无论正负）
                dp[i + 1][j + 1][0] = maxPrev0 + coin;
                
                if (coin >= 0) {
                    // 正数：所有状态直接累加
                    dp[i + 1][j + 1][1] = maxPrev1 + coin;
                    dp[i + 1][j + 1][2] = maxPrev2 + coin;
                } else {
                    // 负数：可以选择使用技能跳过（不累加，转移到下一状态）
                    dp[i + 1][j + 1][1] = Math.max(maxPrev1 + coin, maxPrev0);
                    dp[i + 1][j + 1][2] = Math.max(maxPrev2 + coin, maxPrev1);
                }
            }
        }
        
        return Math.max(Math.max(dp[m][n][0], dp[m][n][1]), dp[m][n][2]);
    }
}
