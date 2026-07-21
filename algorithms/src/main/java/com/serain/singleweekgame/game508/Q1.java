package com.serain.singleweekgame.game508;

import java.util.Arrays;

public class Q1 {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        int pos=nums.length-1;
        long sum=0;
        while (k-- > 0) {
            if(mul>0){
                sum+=nums[pos]*mul;
                mul--;
                pos--;
            }else{
                sum+=nums[pos];
                pos--;
            }
        }
        return sum;
    }
}
