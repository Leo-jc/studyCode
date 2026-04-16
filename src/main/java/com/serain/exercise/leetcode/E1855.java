package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-19  21:54
 * @Description: TODO
 * @Version: 1.0
 */
public class E1855 {
    public int maxDistance(int[] nums1, int[] nums2) {
        int ans=0;
        for(int i=0;i<nums1.length;i++){
            int left=i;
            int right=nums2.length-1;
            while(left<=right){
                int mid=(left+right)/2;
                if(nums2[mid]>=nums1[i]){
                    left=mid;
                }else{
                    right=mid-1;
                }
            }
            System.out.println( left+ " "+ right);
            if(left<nums2.length){
                int distance=Math.abs(i-left);
                ans=Math.max(ans,distance);
            }
        }
        return ans;
    }
}
