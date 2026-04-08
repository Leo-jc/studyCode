package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-10  12:52
 * @Description: TODO
 * @Version: 1.0
 */
public class E3740 {
    public int minimumDistance(int[] nums) {
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]!=nums[j]) continue;
                for(int k=j+1;k<nums.length;k++){
                    if(nums[j]!=nums[k]) continue;
                    ans=Math.min(ans,2*(k-i));
                }
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
}
