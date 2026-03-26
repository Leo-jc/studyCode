package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  22:03
 * @Description: TODO
 * @Version: 1.0
 */
public class E189 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
        reverse(nums, 0, n-1);
    }

    public void reverse(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
