/**
 * @author Serain
 * @date 2026-07-17
 * @description GCD 配对查询
 * 给你一个长度为 n 的整数数组 nums 和一个整数数组 queries。
 * gcdPairs 表示数组 nums 中所有满足 0 <= i < j < n 的数对 (nums[i], nums[j]) 的最大公约数 升序 排列构成的数组。
 * 对于每个查询 queries[i]，你需要找到 gcdPairs 中下标为 queries[i] 的元素。
 * 请你返回一个整数数组 answer，其中 answer[i] 是 gcdPairs[queries[i]] 的值。
 * gcd(a, b) 表示 a 和 b 的最大公约数。
 * 示例：
 * 输入：nums = [2,3,4], queries = [0,2,2]
 * 输出：[1,2,2]
 */
package com.serain.exercise.leetcode.math;

public class E3312 {
    /**
     * GCD 配对查询
     * 思路：
     * 1. 计算 nums 的最大值 mx
     * 2. 统计 1~mx 中每个 i 作为约数的元素个数 cnt[i]（用倍数累加）
     * 3. 利用倍数公式（容斥）从大到小计算 gcd 恰好为 g 的数对数 f[g]：
     *    f[g] = C(cnt[g], 2) - sum(f[k*g])，k >= 2
     * 4. 构造前缀和 prefix[g] = sum(f[1..g])
     * 5. 对每个 query 利用二分查找最小的 g 使得 prefix[g] > queries[i]
     *
     * @param nums 输入数组
     * @param queries 查询下标数组
     * @return 每个查询对应的 gcdPairs 元素
     */
    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        // 1. 找最大值
        int mx = 0;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }

        // 2. 频率数组 + 倍数累加统计 cnt[i]：nums 中能被 i 整除的元素个数
        int[] freq = new int[mx + 1];
        for (int num : nums) {
            freq[num]++;
        }
        int[] cnt = new int[mx + 1];
        for (int i = 1; i <= mx; i++) {
            for (int j = i; j <= mx; j += i) {
                cnt[i] += freq[j];
            }
        }

        // 3. 倍数公式（容斥）计算 gcd 恰好为 g 的数对数
        long[] f = new long[mx + 1];
        for (int g = mx; g >= 1; g--) {
            long c = cnt[g];
            // 先统计 g 整除的所有数对：选出 2 个都能被 g 整除的元素
            f[g] = c * (c - 1) / 2;
            // 减去 gcd 恰好为 g 的倍数的部分（已经计算过）
            for (int k = 2 * g; k <= mx; k += g) {
                f[g] -= f[k];
            }
        }

        // 4. 构造前缀和
        long[] prefix = new long[mx + 1];
        for (int i = 1; i <= mx; i++) {
            prefix[i] = prefix[i - 1] + f[i];
        }

        // 5. 对每个 query 二分查找答案：找最小的 g 使得 prefix[g] > queries[i]
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            int lo = 1, hi = mx;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (prefix[mid] > target) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            answer[i] = lo;
        }
        return answer;
    }
}
