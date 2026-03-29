package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  14:23
 * @Description: TODO
 * @Version: 1.0
 */
public class E198 {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                dp[i] = Math.max(nums[i], dp[i - 1]);
            } else {
                dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
            }
        }
        return dp[nums.length - 1];
    }
}
