package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-23  19:50
 * @Description: TODO
 * @Version: 1.0
 */
public class E1752 {
    public boolean check(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                count++;
            }
        }
        return count==0||(count==1&&nums[0]>=nums[nums.length-1]);
    }
}
