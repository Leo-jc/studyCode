package com.serain.exercise.leetcode;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-11  16:24
 * @Description: TODO
 * @Version: 1.0
 */
public class E3741 {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], new ArrayList<>()));
            map.get(nums[i]).add(i);
        }
        int ans = Integer.MAX_VALUE;
        for(var entity: map.entrySet()){
            if(entity.getValue().size()<3) continue;
            entity.getValue().sort((a,b)->a-b);
            for(int i = 0; i < entity.getValue().size()-2; i++){
                ans=Math.min(ans,2*(entity.getValue().get(i+2)-entity.getValue().get(i)));
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
}
