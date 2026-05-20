package com.serain.exercise.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-21  21:57
 * @Description: 找出两个数组中所有数对的最长公共前缀长度
 * @Version: 1.0
 */
public class E3043 {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<String> prefixes = new HashSet<>();
        
        for (int num : arr1) {
            String str = String.valueOf(num);
            for (int i = 1; i <= str.length(); i++) {
                prefixes.add(str.substring(0, i));
            }
        }
        
        int maxLength = 0;
        for (int num : arr2) {
            String str = String.valueOf(num);
            for (int i = 1; i <= str.length(); i++) {
                String prefix = str.substring(0, i);
                if (prefixes.contains(prefix)) {
                    maxLength = Math.max(maxLength, prefix.length());
                }
            }
        }
        
        return maxLength;
    }
}
