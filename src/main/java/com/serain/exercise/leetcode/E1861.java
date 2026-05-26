package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-06  10:36
 * @Description: LeetCode 第 1861 题
 * @Version: 1.0
 */
public class E1861 {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = n; j >= 0;){
                if(j==n||boxGrid[i][j] !='.'){
                    int k=j-1;
                    while(k>=0&&boxGrid[i][k]=='.'){
                        k--;
                    }
                    if(k==-1||boxGrid[i][k]=='*'){
                        j=k;
                        continue;
                    }
                    char tmp=boxGrid[i][k];
                    boxGrid[i][k]=boxGrid[i][j-1];
                    boxGrid[i][j-1]=tmp;
                }
                j--;
            }
        }
        char[][] ans=new char[n][m];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans[j][m-i-1]=boxGrid[i][j];
            }
        }
        return ans;
    }
}
