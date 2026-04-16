package com.serain.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-17  10:34
 * @Description: TODO
 * @Version: 1.0
 */
public class E3761 {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        int ans=Integer.MAX_VALUE;
        for (int i=nums.length-1;i>=0;i--) {
            int reverse = Integer.parseInt(reverse(String.valueOf(nums[i])));
            if (map.containsKey(reverse)) {
                ans = Math.min(ans, map.get(reverse)-i);
            }
            map.put(nums[i], i);
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    private String reverse(String s) {
        StringBuilder sb=new StringBuilder();
        for (int i=s.length()-1;i>=0;i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
