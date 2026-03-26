package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  15:38
 * @Description: TODO
 * @Version: 1.0
 */
public class E438 {
    public List<Integer> findAnagrams(String s, String p) {
        int[] pCount = new int[26];
        for(char c : p.toCharArray()){
            pCount[c-'a']++;
        }
        int[] sCount = new int[26];
        int left = 0;
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            sCount[s.charAt(i)-'a']++;
            while(i-left+1 > p.length()){
                sCount[s.charAt(left)-'a']--;
                left++;
            }
            if(i-left+1 == p.length()){
                if(Arrays.equals(pCount,sCount)){
                    res.add(left);
                }
            }
        }
        return res;
    }
}
