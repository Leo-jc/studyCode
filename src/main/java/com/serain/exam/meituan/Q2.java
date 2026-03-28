package com.serain.exam.meituan;

import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.meituan
 * @Author: Serain
 * @CreateTime: 2026-03-28  09:46
 * @Description: TODO
 * @Version: 1.0
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            int[][] dp = new int[n+1][2];
            for(int i=0;i<n;i++){
                dp[i+1][1]=Math.max(dp[i][1],dp[i][0]+a[i]);
                dp[i+1][0]=Math.max(dp[i][0],dp[i][1]-a[i]);
            }
            System.out.println(Math.max(dp[n][0],dp[n][1]));
        }
    }
}
