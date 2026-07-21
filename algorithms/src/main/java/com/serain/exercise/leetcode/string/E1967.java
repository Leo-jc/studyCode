package com.serain.exercise.leetcode.string;

/**
 * LeetCode 1967 - 作为子字符串出现在单词中的字符串数目
 * <p>
 * 给你一个字符串数组 patterns 和一个字符串 word，统计 patterns 中有多少个字符串是 word 的子字符串。
 * 返回字符串数目。子字符串是字符串中的一个连续字符序列。
 * <p>
 * 时间复杂度 O(n * m)，空间复杂度 O(1)
 * （n = patterns.length, m = word.length()）
 */
public class E1967 {

    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for (String p : patterns) {
            if (word.contains(p)) {
                count++;
            }
        }
        return count;
    }
}
