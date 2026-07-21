package com.serain.exercise.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3020 - 子集中元素的最大数量
 * <p>
 * 从正整数数组中选子集，排列成回文模式 [x, x², x⁴, ..., x^{k/2}, x^k, x^{k/2}, ..., x⁴, x², x]，
 * 其中 k 是 2 的非负幂次，求子集的最大元素数量。
 * <p>
 * 时间复杂度 O(n log log M)，空间复杂度 O(n)
 */
public class E3020 {

    public int maximumLength(int[] nums) {
        // 统计每个数字的频次（用Long防止平方运算溢出）
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.merge((long) num, 1, Integer::sum);
        }

        // 特殊处理1：1²=1会无限循环，可以取任意奇数个1
        Integer ones = freq.remove(1L);
        int ans = (ones == null) ? 0 : ones - (ones % 2 ^ 1);

        // 枚举每个基数
        for (long x : freq.keySet()) {
            int len = 0;
            long cur = x;

            // 只要当前数字出现>=2次，就可以平方继续扩展
            while (freq.getOrDefault(cur, 0) > 1) {
                cur = cur * cur;   // 平方
                len += 2;          // 左右各加一个元素
            }

            // 最终元素存在则+1（作为中间元素）
            // 不存在则-1（撤销最后一次加的一对）
            len += freq.getOrDefault(cur, -1);

            ans = Math.max(ans, len);
        }

        return ans;
    }
}
