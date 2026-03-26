package com.serain.exercise.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  14:35
 * @Description: TODO
 * @Version: 1.0
 */
public class E128 {
    public int longestConsecutive(int[] nums) {
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for(int num : set){
            if(set.contains(num-1)) continue;
            int curNum = num;
            int curLen = 1;
            while(set.contains(curNum+1)){
                curNum++;
                curLen++;
            }
            ans = Math.max(ans, curLen);
        }
        return ans;
    }
}
