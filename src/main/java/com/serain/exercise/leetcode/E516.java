package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-18  19:54
 * @Description: TODO
 * @Version: 1.0
 */
public class E516 {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()+1][s.length()+1];
        int max = 0;
        for(int i=1;i<=s.length();i++){
            dp[i][i] = 1;
            for(int j=i-1;j>0;j--){
                if(s.charAt(i-1) == s.charAt(j-1)){
                    dp[j][i] = dp[j+1][i-1] + 2;
                }else{
                    dp[j][i] = Math.max(dp[j+1][i],dp[j][i-1]);
                }
            }
        }
        return dp[1][s.length()];
    }
}
