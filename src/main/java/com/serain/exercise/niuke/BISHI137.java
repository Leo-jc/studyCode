package com.serain.exercise.niuke;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.niuke
 * @Author: Serain
 * @CreateTime: 2026-03-16  21:22
 * @Description: TODO
 * @Version: 1.0
 */
public class BISHI137 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] w = new int[n];
            int[] v = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt();
                v[i] = sc.nextInt();
            }
            int[] dp=new int[m+1];
            Arrays.fill(dp,0);
            int ans = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<=m-w[i];j++){
                    dp[j+w[i]]=Math.max(dp[j+w[i]],dp[j]+v[i]);
                    ans=Math.max(ans,dp[j+w[i]]);
                }
            }
            System.out.println(ans);
        }
    }
}
