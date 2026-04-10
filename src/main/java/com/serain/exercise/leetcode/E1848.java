package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-13  12:04
 * @Description: TODO
 * @Version: 1.0
 */
public class E1848 {
    public int getMinDistance(int[] nums, int target, int start) {
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                ans=Math.min(ans,Math.abs(i-start));
            }
        }
        return ans;
    }
}
