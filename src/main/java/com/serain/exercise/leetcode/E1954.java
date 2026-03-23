package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-23  09:59
 * @Description: TODO
 * @Version: 1.0
 */
public class E1954 {
    private static final int MOD = 1000000007;
    public int maxProductPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        long[][][] maxProduct = new long[rows][cols][2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int val = grid[i][j];
                if(i==0 && j==0){
                    maxProduct[i][j][0] = val;
                    maxProduct[i][j][1] = val;
                    continue;
                }
                long maxRes=Long.MAX_VALUE;
                long minRes=Long.MAX_VALUE;
                if(i>0){
                    maxRes = Math.max(maxProduct[i-1][j][0]*val, maxProduct[i-1][j][1]*val);
                    minRes = Math.min(maxProduct[i-1][j][0]*val, maxProduct[i-1][j][1]*val);
                }
                if(j>0){
                    maxRes = Math.max(maxRes,Math.max(maxProduct[i][j-1][0]*val, maxProduct[i][j-1][1]*val));
                    minRes = Math.min(minRes,Math.min(maxProduct[i][j-1][0]*val, maxProduct[i][j-1][1]*val));
                }
                maxProduct[i][j][0] = minRes;
                maxProduct[i][j][1] = maxRes;
            }
        }
        return maxProduct[rows-1][cols-1][1] < 0 ? -1 : (int)maxProduct[rows-1][cols-1][1]% MOD;
    }
}
