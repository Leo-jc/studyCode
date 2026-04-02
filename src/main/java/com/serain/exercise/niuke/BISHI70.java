package com.serain.exercise.niuke;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.niuke
 * @Author: Serain
 * @CreateTime: 2026-04-08  15:25
 * @Description: TODO
 * @Version: 1.0
 */
public class BISHI70 {

    public static final int MOD = 1000000007;
    public static final int MAX_NUM=500001;
    public static int[] f = new int[MAX_NUM];
    public static int[] inversef = new int[MAX_NUM];

    static{
        f[0]=1;
        for(int i=1;i<MAX_NUM;i++){
            f[i] = (f[i-1]%MOD * i)%MOD;
        }
        int b=MOD-2;
        int temNum=f[MAX_NUM-1];
        inversef[MAX_NUM-1]=1;
        while(b>0){
            if(b%2==1) inversef[MAX_NUM-1] = (inversef[MAX_NUM-1]%MOD * temNum)%MOD;
            b/=2;
            temNum = (temNum%MOD * temNum)%MOD;
        }
        for(int i=MAX_NUM-2;i>=0;i--){
            inversef[i] = (inversef[i+1]%MOD * (i+1))%MOD;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.println(getAns(m,n));
        }
    }

    private static int getAns(int m, int n) {
        if(m<n){
            return 0;
        }
        return (f[m]%MOD * inversef[n]%MOD * inversef[m-n]%MOD)%MOD;
    }
}
