package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-18  09:15
 * @Description: TODO
 * @Version: 1.0
 */
public class E3070 {
    public int countSubmatrices(int[][] grid, int k) {
        int ans = 0;
        int[][] sum = new int[grid.length+1][grid[0].length+1];
        for (int i = 1; i <= grid.length; i++) {
            for (int j = 1; j <= grid[0].length; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + grid[i-1][j-1];
                System.out.println(sum[i][j]);
                if(sum[i][j] >= k) ans++;
            }
        }
        return ans;
    }
}
