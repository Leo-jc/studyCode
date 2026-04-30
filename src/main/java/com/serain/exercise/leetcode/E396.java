package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-01  20:34
 * @Description: TODO
 * @Version: 1.0
 */
public class E396 {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int f = 0;
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
        }
        int res = f;
        for (int i = n - 1; i >= 1; i--) {
            f = f + sum - n * nums[i];
            res = Math.max(res, f);
        }
        return res;
    }
}
