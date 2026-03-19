package com.serain.exercise.leetcode;

/**
*@BelongsProject: studyCode
*@BelongsPackage: com.serain.exercise.leetcode
*@Author: Serain
*@CreateTime: 2026-03-19  17:05
*@Description: TODO
*@Version: 1.0
*/
public class E2134 {
    /**
     * 计算最小交换次数
     * 
     * @param nums 输入的整数数组
     * @return 返回最小交换次数
     */
    public int minSwaps(int[] nums) {
        int numOne=0;
        for (int num : nums) {
            if (num == 1) {
                numOne++;
            }
        }
        int minSwapNum=0;
        for(int i=0;i<numOne;i++){
            if(nums[i]==0) minSwapNum++;
        }
        int ans=minSwapNum;
        for(int i=0,j=numOne;i<nums.length;i++,j=(j+1)%nums.length){
            if(nums[i]==0) minSwapNum--;
            if(nums[j]==0) minSwapNum++;
            ans=Math.min(ans,minSwapNum);
        }
        return ans;
    }
}
