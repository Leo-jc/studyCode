package com.serain.exercise.leetcode.dp;

/**
 * @BelongsProject: StudyCode
 * @BelongsPackage: com.serain.exercise.leetcode.dp
 * @Author: Serain
 * @CreateTime: 2026-09-21  14:02
 * @Description: TODO
 * @Version: 1.0
 */
public class E3542 {
    public long[] resultArray(int[] nums, int k) {
        long[] result=new long[k];
        long[][] dp=new long[nums.length+1][k];
        dp[0][0]=0;
        for(int i=0;i<nums.length;i++){
            int value=nums[i]%k;
            dp[i+1][value]=1;
            for(int j=0;j<k;j++){
                dp[i+1][(value*j)%k]+=dp[i][j];
            }
            for(int j=0;j<k;j++){
                result[j]+=dp[i+1][j];
            }
        }
        return result;
    }
}
