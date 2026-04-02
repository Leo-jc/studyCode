package com.serain.exercise.niuke;

import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.niuke
 * @Author: Serain
 * @CreateTime: 2026-04-08  16:06
 * @Description: TODO
 * @Version: 1.0
 */
public class BISHI90 {
    public static int[][][] f=new int[101][101][101];
    public static final int MOD=1000000007;
    public static int dp(int a,int b,int c){
        if(a<=0||b<=0||c<=0) return 1;
        if(f[a][b][c]!=0) return f[a][b][c];
        if(a<b&&b<c) return f[a][b][c]=(dp(a,b,c-1)+dp(a,b-1,c-1)-dp(a,b-1,c)+MOD)%MOD;
        return f[a][b][c]=(((dp(a-1,b,c)+dp(a-1,b-1,c))%MOD+dp(a-1,b,c-1))%MOD-dp(a-1,b-1,c-1)+MOD)%MOD;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            System.out.println(dp(a,b,c));
        }
    }
}
