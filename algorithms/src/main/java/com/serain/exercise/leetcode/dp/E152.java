package com.serain.exercise.leetcode.dp;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.dp
 * @Author: Serain
 * @CreateTime: 2026-03-29  15:04
 * @Description: LeetCode 第 152 题
 * @Version: 1.0
 */
public class E152 {
    public int maxProduct(int[] nums) {
        int[] maxNum=new int[nums.length];
        maxNum[0]=nums[0];
        int[] minNum=new int[nums.length];
        minNum[0]=nums[0];
        int ans=nums[0];
        for (int i=1; i < nums.length; i++) {
            maxNum[i]=Math.max(nums[i], Math.max(maxNum[i-1]*nums[i], minNum[i-1]*nums[i]));
            minNum[i]=Math.min(nums[i], Math.min(maxNum[i-1]*nums[i], minNum[i-1]*nums[i]));
            ans=Math.max(ans, maxNum[i]);
        }
        return ans;
    }
}
