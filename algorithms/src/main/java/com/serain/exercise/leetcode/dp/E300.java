package com.serain.exercise.leetcode.dp;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.dp
 * @Author: Serain
 * @CreateTime: 2026-03-29  15:00
 * @Description: LeetCode 第 300 题
 * @Version: 1.0
 */
public class E300 {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxLen = dp[0];
        for (int i = 1; i < nums.length; i++) {
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
