package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  14:14
 * @Description: TODO
 * @Version: 1.0
 */
public class E2573 {
    public String findTheString(int[][] lcp) {
        char[] ans = new char[lcp.length];
        int i = 0;
        for(char c='a';c<='z';c++){
            for(int j=0;j<lcp.length;j++){
                if(lcp[i][j]>0){
                    ans[j]=c;
                }
            }
            while(i<lcp.length&&ans[i]>0){
                i++;
            }
            if(i==lcp.length){
                break;
            }
        }
        if(i<lcp.length) return "";
        int[][] dp=new int[lcp.length][lcp.length];
        for(i=lcp.length-1;i>=0;i--){
            for(int j=lcp.length-1;j>=0;j--){
                dp[i][j]=ans[i]==ans[j]?(i==lcp.length-1||j==lcp.length-1?1: dp[i+1][j+1]+1):0;
                if(dp[i][j]!=lcp[i][j]){
                    return "";
                }
            }
        }
        return new String(ans);
    }
}
