package com.serain.exercise.leetcode.array;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.array
 * @Author: Serain
 * @CreateTime: 2026-03-29  14:00
 * @Description: LeetCode 第 169 题
 * @Version: 1.0
 */
public class E169 {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
