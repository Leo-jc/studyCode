package com.serain.study.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.study.exam
 * @Author: Serain
 * @CreateTime: 2026-03-15  15:30
 * @Description: TODO
 * @Version: 1.0
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int C = in.nextInt();
        int n = in.nextInt();
        long[] d = new long[n+2];
        d[n+1] = L;
        long[] p = new long[n+2];
        p[n+1] = 0;
        for(int i=0;i<n;i++){
            d[i+1]=in.nextLong();
            p[i+1]=in.nextLong();
        }
        for(int i =1 ; i<=n+1; i++){
            if(d[i]-d[i-1]>C){
                System.out.println(-1);
                return;
            }
        }
        int[] dp = new int[n+2];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[1] = 0;
        for(int i=2;i<=n+1;i++){
            int cost=0;
            for(int j=i-1;j>=1;j--){
                cost+=(d[j+1]-d[j])*p[j];
                dp[i]=Math.min(dp[i],dp[j]+cost);
            }
        }
        System.out.println(dp[n+1]);
    }
}
