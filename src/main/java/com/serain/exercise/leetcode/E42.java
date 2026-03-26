package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  15:14
 * @Description: TODO
 * @Version: 1.0
 */
public class E42 {
    public int trap(int[] height) {
        int n=height.length;
        int[] leftMax = new int[n];
        leftMax[0]=height[0];
        for(int i=1;i<n;i++){
            leftMax[i]=Math.max(leftMax[i-1],height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n-1]=height[n-1];
        for(int i=n-2;i>=0;i--){
            rightMax[i]=Math.max(rightMax[i+1],height[i]);
        }
        int res=0;
        for(int i=0;i<n;i++){
            res+=Math.min(leftMax[i],rightMax[i])-height[i];
        }
        return res;
    }
}
