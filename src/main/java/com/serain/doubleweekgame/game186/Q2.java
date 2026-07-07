package com.serain.doubleweekgame.game186;

public class Q2 {
    public int maxValidPairSum(int[] nums, int k) {
        int[] preMax=new int[nums.length+1];
        for(int i=1;i<=nums.length;i++){
            preMax[i]=Math.max(preMax[i-1],nums[i-1]);
        }
        int[] postMax=new int[nums.length+1];
        for(int i=nums.length-1;i>=1;i--){
            postMax[i]=Math.max(postMax[i+1],nums[i+1]);
        }
        int ans=0;
        for(int i=1;i<=nums.length;i++){
            if(i+k>nums.length){
                break;
            }
            ans=Math.max(ans,preMax[i]+postMax[i+k]);
        }
        return ans;
    }
}
