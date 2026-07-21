package com.serain.exercise.leetcode.bitmanipulation;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.bitmanipulation
 * @Author: Serain
 * @CreateTime: 2026-05-20  16:26
 * @Description: LeetCode 第 2657 题
 * @Version: 1.0
 */
public class E2657 {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];
        long numA=0;
        long numB=0;
        for (int i = 0; i < n; i++) {
            numA=numA|(1L<<A[i]);
            numB=numB|(1L<<B[i]);
            ans[i]=Long.bitCount(numA&numB);
        }
        return ans;
    }
}
