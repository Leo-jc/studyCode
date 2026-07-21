package com.serain.doubleweekgame.game186;

public class Q1 {
    public boolean isMiddleElementUnique(int[] nums) {
        int mid  = nums[nums.length / 2];
        for(int i=0;i<nums.length;i++){
            if(i == nums.length / 2){
                continue;
            }
            if(nums[i] == mid){
                return false;
            }
        }
        return true;
    }
}
