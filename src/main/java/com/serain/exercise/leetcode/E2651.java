package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-23  10:55
 * @Description: TODO
 * @Version: 1.0
 */
public class E2651 {
    public long[] distance(int[] nums) {
        int n = nums.length;
        var groups = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; ++i) // 相同元素分到同一组，记录下标
            groups.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);

        var ans = new long[n];
        var s = new long[n + 1];
        for (var a : groups.values()) {
            int m = a.size();
            for (int i = 0; i < m; ++i)
                s[i + 1] = s[i] + a.get(i); // 前缀和
            for (int i = 0; i < m; ++i) {
                int target = a.get(i);
                long left = (long) target * i - s[i]; // 蓝色面积
                long right = s[m] - s[i] - (long) target * (m - i); // 绿色面积
                ans[target] = left + right;
            }
        }
        return ans;
    }
}
