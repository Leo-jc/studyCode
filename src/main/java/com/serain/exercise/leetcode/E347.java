package com.serain.exercise.leetcode;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  16:12
 * @Description: TODO
 * @Version: 1.0
 */
public class E347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        TreeMap<Integer, List<Integer>> map2 = new TreeMap<>((o1, o2) -> o2-o1);
        for(var entry : map.entrySet()){
            List<Integer> list = map2.getOrDefault(entry.getValue(), new ArrayList<>());
            list.add(entry.getKey());
            map2.put(entry.getValue(),list);
        }
        int[] ans = new int[k];
        int pos = 0;
        for(var entry : map2.entrySet()){
            for(int num : entry.getValue()){
                ans[pos++] = num;
                k--;
                if(k==0){
                    return ans;
                }
            }
        }
        return null;
    }
}
