package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  21:44
 * @Description: TODO
 * @Version: 1.0
 */
public class E53 {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = nums[0];
        for (int num : nums) {
            sum += num;
            max = Math.max(max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
