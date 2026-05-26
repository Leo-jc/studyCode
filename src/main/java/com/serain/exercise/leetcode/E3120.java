package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-26  10:42
 * @Description: LeetCode 第 3120 题
 * @Version: 1.0
 */
public class E3120 {
    public int numberOfSpecialChars(String word) {
        int count = 0;
        int n=word.length();
        boolean[] isSmall = new boolean[26];
        boolean[] isCapital = new boolean[26];
        for(int i=0;i<n;i++){
            char c = word.charAt(i);
            if(c>='a' && c<='z'){
                isSmall[c-'a'] = true;
            }
            if(c>='A' && c<='Z'){
                isCapital[c-'A'] = true;
            }
        }
        for(int i=0;i<26;i++){
            if(isSmall[i] && isCapital[i]){
                count++;
            }
        }
        return count;
    }
}
