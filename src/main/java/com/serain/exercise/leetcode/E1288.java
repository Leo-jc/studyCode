

package com.serain.exercise.leetcode;

import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-07-06  19:44
 * @Description: LeetCode 1288. 删除被覆盖区间
 *               对区间按起点升序排列，起点相同时按终点降序排列，
 *               然后遍历，统计未被覆盖的区间数量。
 * @Version: 1.0
 */
public class E1288 {
    public int removeCoveredIntervals(int[][] intervals) {
        // 二维数组排序：起点升序，起点相同时终点降序
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        int count = 0;          // 未被覆盖的区间数
        int maxEnd = 0;         // 遍历过程中遇到的最大终点

        for (int[] interval : intervals) {
            int end = interval[1];
            // 当前区间终点 > 之前最大终点，说明未被覆盖
            if (end > maxEnd) {
                count++;
                maxEnd = end;
            }
        }
        return count;
    }
}
