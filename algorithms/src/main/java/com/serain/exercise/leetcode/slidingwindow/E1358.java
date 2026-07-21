package com.serain.exercise.leetcode.slidingwindow;

/**
 * LeetCode 1358 - 包含所有三种字符的子字符串数目
 * <p>
 * 给定只含 'a','b','c' 的字符串，求包含至少一个 a、一个 b、一个 c 的子串数量。
 * <p>
 * 核心思路：以每个位置 i 为右端点，找到最小的左边界 j，
 * 使得 [j, i] 包含全部三种字符，则 ans += j + 1（所有 j' ∈ [0, j] 都满足）。
 * <p>
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
public class E1358 {

    public int numberOfSubstrings(String s) {
        int lastA = -1, lastB = -1, lastC = -1;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                lastA = i;
            } else if (c == 'b') {
                lastB = i;
            } else {
                lastC = i;
            }
            // 当三种字符都至少出现一次时，以 min(lastA,lastB,lastC) 为最小左边界
            if (lastA >= 0 && lastB >= 0 && lastC >= 0) {
                ans += Math.min(lastA, Math.min(lastB, lastC)) + 1;
            }
        }
        return ans;
    }
}
