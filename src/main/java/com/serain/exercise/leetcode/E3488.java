package com.serain.exercise.leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-16  21:43
 * @Description: TODO
 * @Version: 1.0
 */
public class E3488 {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] count = new int[nums.length];
        Arrays.fill(count, Integer.MAX_VALUE);
        for (int i = 0; i < nums.length*2-1; i++) {
            if(!map.containsKey(nums[i% nums.length])) map.put(nums[i%nums.length], i%nums.length);
            int length=(i% nums.length-map.get(nums[i])+nums.length)%nums.length;
            count[i%nums.length]=Math.min(count[i%nums.length], length);
            count[map.get(nums[i])]=Math.min(count[map.get(nums[i])], length);
            map.put(nums[i], i%nums.length);
        }
        List<Integer> ans = new ArrayList<>();
        for (int query : queries) {
            ans.add(count[query]);
        }
        return ans;
    }
}
