package com.serain.exercise.leetcode;

import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  14:33
 * @Description: TODO
 * @Version: 1.0
 */
public class E322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        for (int coin : coins) {
            if (coin <= amount) dp[coin] = 1;
            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[amount]==Integer.MAX_VALUE/2?-1:dp[amount];
    }
}
