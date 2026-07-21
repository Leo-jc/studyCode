package com.serain.exercise.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.array
 * @Author: Serain
 * @CreateTime: 2026-05-13  15:36
 * @Description: LeetCode 第 1674 题
 * @Version: 1.0
 */
public class E1674 {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] f = new int[2 * limit + 2];
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i], b = nums[n - i - 1];
            int l=Math.min(a,b)+1;
            int r=Math.max(a,b)+limit;
            f[2]+=2;
            f[l]-=1;
            f[a+b]-=1;
            f[a+b+1]+=1;
            f[r+1]+=1;
        }
        int res = f[2];
        for (int i = 3; i <= 2 * limit; i++) {
            f[i] += f[i - 1];
            System.out.println(f[i]);
            res = Math.min(res, f[i]);
        }
        return res;
    }
}
