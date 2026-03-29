package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  15:29
 * @Description: TODO
 * @Version: 1.0
 */
public class E55 {
    public boolean canJump(int[] nums) {
        int maxLength=0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxLength) {
                maxLength = Math.max(maxLength, i + nums[i]);
                if (maxLength >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
