package com.serain.exam.jingdong;

import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.jingdong
 * @Author: Serain
 * @CreateTime: 2026-04-14  15:17
 * @Description: TODO
 * @Version: 1.0
 */
public class test {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(getAns(n));
        System.out.println(getAns2(n));
    }

    private static int getAns2(int n) {
        int[] dp=new int[n+1];
        dp[1]=1;dp[0]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    private static int getAns(int n) {
        if(n<0) return 0;
        int now=1;
        int pre=1;
        for(int i=2;i<=n;i++){
            int temp=now;
            now=now+pre;
            pre=temp;
        }
        return now;
    }
}
