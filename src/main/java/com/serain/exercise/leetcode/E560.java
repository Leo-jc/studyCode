package com.serain.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  15:43
 * @Description: TODO
 * @Version: 1.0
 */
public class E560 {
    public int subarraySum(int[] nums, int k) {
        int count=0;
        int sum=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
