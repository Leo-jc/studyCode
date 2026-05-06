package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-07  10:24
 * @Description: TODO
 * @Version: 1.0
 */
public class E3660 {
    public int[] maxValue(int[] nums) {
        int n=nums.length;
        int[] maxNums=new int[n];
        int[] minNums=new int[n];
        maxNums[0]=0;
        for (int i = 1; i < n; i++) {
            maxNums[i]=i;
            if(nums[i]<nums[maxNums[i-1]]){
                maxNums[i]=maxNums[i-1];
            }
        }
        minNums[n-1]=n-1;
        for (int i = n-2; i >= 0; i--) {
            minNums[i]=i;
            if(nums[i]>nums[minNums[i+1]]){
                minNums[i]=minNums[i+1];
            }
        }
        int[] ans=new int[n];
        ans[n-1]=nums[maxNums[n-1]];
        for(int i=n-2;i>=0;i--){
            if(nums[maxNums[i]]<=nums[minNums[i+1]]){
                ans[i]=nums[maxNums[i]];
            }else{
                ans[i]=ans[i+1];
            }
        }
        return ans;
    }
}
