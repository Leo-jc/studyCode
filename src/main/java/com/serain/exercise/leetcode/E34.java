package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-22  20:49
 * @Description: TODO
 * @Version: 1.0
 */
public class E34 {
    public int[] searchRange(int[] nums, int target) {
        int start=findStartPos(nums,target);
        int end=findEndPos(nums,target);
        return new int[]{start,end};
    }

    public int findStartPos(int[] nums, int target){
        int start=0;
        int end=nums.length-1;
        while(start<=end){
            int mid=(start+end)/2;
            if(nums[mid]==target){
                if(mid==0||nums[mid-1]!=target){
                    return mid;
                }else{
                    end=mid-1;
                }
            }else if(nums[mid]>target){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return -1;
    }
    public int findEndPos(int[] nums, int target){
        int start=0;
        int end=nums.length-1;
        while(start<=end){
            int mid=(start+end)/2;
            if(nums[mid]==target){
                if(mid==nums.length-1||nums[mid+1]!=target){
                    return mid;
                }else{
                    start=mid+1;
                }
            }else if(nums[mid]>target){
                end=mid-1;
            } else{
                start=mid+1;
            }
        }
        return -1;
    }
}
