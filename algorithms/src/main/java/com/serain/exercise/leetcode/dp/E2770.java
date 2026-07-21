package com.serain.exercise.leetcode.dp;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.dp
 * @Author: Serain
 * @CreateTime: 2026-05-10  15:21
 * @Description: LeetCode 第 2770 题
 * @Version: 1.0
 */
public class E2770 {
    public int maximumJumps(int[] nums, int target) {
        int[] dp=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            dp[i]=Integer.MIN_VALUE;
        }
        dp[0]=0;
        for(int i=1;i<nums.length;i++){
            for(int j=i-1;j>=0;j--){
                if(Math.abs(nums[i]-nums[j])<=target&&dp[j]!=Integer.MIN_VALUE){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
        return dp[nums.length-1]==Integer.MIN_VALUE?-1:dp[nums.length-1];
    }
}
