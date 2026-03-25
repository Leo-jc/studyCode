package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-25  10:20
 * @Description: TODO
 * @Version: 1.0
 */
public class E3546 {
    public boolean canPartitionGrid(int[][] grid) {
        int[][] sum = new int[grid.length+1][grid[0].length+1];
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + grid[i][j];
                total += grid[i][j];
            }
        }
        for(int i=0;i<grid.length;i++){
            if(sum[i+1][grid[0].length]==total/2){
                return true;
            }
        }
        for(int i=0;i<grid[0].length;i++){
            if(sum[grid.length][i+1]==total/2){
                return true;
            }
        }
        return false;
    }
}
