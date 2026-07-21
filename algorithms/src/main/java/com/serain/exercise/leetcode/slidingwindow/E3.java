package com.serain.exercise.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.slidingwindow
 * @Author: Serain
 * @CreateTime: 2026-03-26  15:30
 * @Description: LeetCode 第 3 题
 * @Version: 1.0
 */
public class E3 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        int left=-1;
        for (int i = 0; i < n; i++) {
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), i);
                ans = Math.max(ans, i - left);
            }
            else{
                left = Math.max(left, map.get(s.charAt(i)));
                map.put(s.charAt(i), i);
                ans = Math.max(ans, i - left);
            }
        }
        return ans;
    }
}
