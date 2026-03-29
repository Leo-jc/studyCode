package com.serain.exercise.leetcode;

import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  14:28
 * @Description: TODO
 * @Version: 1.0
 */
public class E279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        Arrays.fill(dp, Integer.MAX_VALUE);
        int sqrtNum=(int)Math.sqrt(n);
        for(int i=1;i<=n;i++){
            for(int j=1;j<=sqrtNum;j++){
                if(i-j*j>=0){
                    dp[i]=Math.min(dp[i],dp[i-j*j]+1);
                }
            }
        }
        return dp[n];
    }
}
