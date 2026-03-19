package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-19  09:40
 * @Description: TODO
 * @Version: 1.0
 */
public class E3212 {
    public int numberOfSubmatrices(char[][] grid) {
        int[][] sumX=new int[grid.length+1][grid[0].length+1];
        int[][] sumY=new int[grid.length+1][grid[0].length+1];
        int ans=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sumX[i+1][j+1]=sumX[i+1][j]+sumX[i][j+1]-sumX[i][j]+(grid[i][j]=='X'?1:0);
                sumY[i+1][j+1]=sumY[i][j+1]+sumY[i+1][j]-sumY[i][j]+(grid[i][j]=='Y'?1:0);
                if(sumX[i+1][j+1]>0&&sumY[i+1][j+1]==sumX[i+1][j+1]){
                    ans++;
                }
            }
        }
        return ans;
    }
}
