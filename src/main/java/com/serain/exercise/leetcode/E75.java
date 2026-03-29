package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  14:04
 * @Description: TODO
 * @Version: 1.0
 */
public class E75 {
    public void sortColors(int[] nums) {
        int P0 = 0;
        int P1 = 0;
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            nums[i]=2;
            if(num<=1){
                nums[P1++] = num;
            }
            if(num==0){
                nums[P0++] = num;
            }
        }
    }
}
