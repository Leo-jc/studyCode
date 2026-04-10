package com.serain.exam.xiechen;

import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.xiechen
 * @Author: Serain
 * @CreateTime: 2026-04-12  09:57
 * @Description: TODO
 * @Version: 1.0
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        String s=sc.next();
        int[] w=new int[n];
        for (int i = 0; i < n-1; i++) {
            w[i]=sc.nextInt();
        }
        System.out.println(getAns(n,s,w));
    }

    private static BigInteger getAns(int n, String s, int[] w) {
        int firstMAx = 0;
        int secondMax = 0;
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                if (w[i] >= firstMAx) {
                    secondMax = firstMAx;
                    firstMAx = w[i];
                }
            } else {
                ans=ans.add(BigInteger.valueOf(w[i]));
            }
        }
        long reverAdd = Math.max(0, firstMAx + secondMax);
        ans=ans.add(BigInteger.valueOf(reverAdd));
        return ans;
    }
}
