package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  14:39
 * @Description: TODO
 * @Version: 1.0
 */
public class E283 {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int slow = 0;
        for (int fast = 0; fast < n; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        while (slow < n) {
            nums[slow] = 0;
            slow++;
        }
    }
}
