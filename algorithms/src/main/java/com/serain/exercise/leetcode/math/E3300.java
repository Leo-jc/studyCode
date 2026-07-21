package com.serain.exercise.leetcode.math;

public class E3300 {
    public int minElement(int[] nums) {
        int min=Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int tempAns=0;
            while(nums[i]>0){
                tempAns+=nums[i]%10;
                nums[i]/=10;
            }
            min=Math.min(min,tempAns);

        }
        return min;
    }
}
