package com.serain.exercise.leetcode;

import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-28  10:04
 * @Description: TODO
 * @Version: 1.0
 */
public class E2033 {
    public int minOperations(int[][] grid, int x) {
        int n = grid.length;
        int m = grid[0].length;
        //1.判断是否有解
        int preNum=grid[0][0]%x;
        for (int[] ints : grid) {
            for (int j = 0; j < m; j++) {
                if (ints[j] % x != preNum) {
                    return -1;
                }
            }
        }
        int[] a = new int[n * m];
        //2.排序
        for(int i = 0; i < n; i++){
            System.arraycopy(grid[i], 0, a, i * m , m);
        }
        Arrays.sort(a);

        //3.找中位数
        int mid = a[(n * m) / 2];
        int res = 0;
        for (int i = 0; i < n * m; i++) {
            res += Math.abs(a[i] - mid) / x;
        }
        return res;
    }
}
