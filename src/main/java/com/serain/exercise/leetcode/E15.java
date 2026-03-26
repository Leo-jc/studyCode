package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  15:01
 * @Description: TODO
 * @Version: 1.0
 */
public class E15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int k=nums.length-1;
            for (int j = i+1; j < nums.length; j++){
                if (j > i+1 && nums[j] == nums[j-1]) continue;
                while (j < k && nums[i] + nums[j] + nums[k] > 0) k--;
                if (j == k) break;
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                }
            }
        }
        return res;
    }
}
