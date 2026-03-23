package com.serain.exercise.leetcode;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-20  09:35
 * @Description: TODO
 * @Version: 1.0
 */
public class E3567 {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int[][] ans=new int[grid.length-k+1][grid[0].length-k+1];
        for(int i=0;i< grid.length-k+1;i++){
            for (int j=0;j< grid[0].length-k+1;j++) {
                if(k==1) {
                    ans[i][j]=0;
                    continue;
                }
                int[] arr = new int[k * k];
                int pos = 0;
                for (int m = i; m < i + k; m++) {
                    for (int n = j; n < j + k; n++) {
                        arr[pos++] = grid[m][n];
                    }
                }
                Arrays.sort(arr);
                int minDiff = Integer.MAX_VALUE;
                for (int p = 1; p < k * k; p++) {
                    if(arr[p]!=arr[p-1]){
                        minDiff = Math.min(minDiff, arr[p] - arr[p - 1]);
                    }
                }
                ans[i][j] = minDiff==Integer.MAX_VALUE?0:minDiff;
            }
        }
        return ans;
    }
}
