package com.serain.exercise.leetcode;

public class E1004 {
    public int longestOnes(int[] nums, int k) {
        int ans=k;
        int cur=0;
        int left=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right]==1){
                ans=Math.max(ans,right-left);
                System.out.println("ans:"+ans+" left:"+left+" right:"+right);
            }
            if(nums[right]==0){
                if(cur<k){
                    ans=Math.max(ans,right-left+1);
                    cur++;
                }else{
                    while(nums[left]==1){
                        left++;
                    }
                    left++;
                    cur--;
                    ans=Math.max(ans,right-left+1);
                }
            }
        }
        return ans;
    }
}
