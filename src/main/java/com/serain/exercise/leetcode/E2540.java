package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-19  19:19
 * @Description: TODO
 * @Version: 1.0
 */
public class E2540 {
    public int getCommon(int[] nums1, int[] nums2) {
        int pos1 = 0;
        int pos2 = 0;
        while (pos1 < nums1.length && pos2 < nums2.length) {
            if (nums1[pos1] == nums2[pos2]) {
                return nums1[pos1];
            } else if (nums1[pos1] < nums2[pos2]) {
                pos1++;
            } else {
                pos2++;
            }
        }
        return -1;
    }
}
