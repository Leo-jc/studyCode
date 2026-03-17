package com.serain.exercise.leetcode;

import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-17  10:09
 * @Description: TODO
 * @Version: 1.0
 */
public class E1727 {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] maxCol = new int[m+1][n+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0) maxCol[i+1][j+1]=0;
                else maxCol[i+1][j+1]=maxCol[i][j+1]+1;
                System.out.println(maxCol[i+1][j+1]);
            }
        }
        int ans = 0;
        for(int i=0;i<m;i++){
            int[] arr=maxCol[i+1];
            Arrays.sort(arr);
            for(int j=1;j<=n;j++){
                ans=Math.max(ans,arr[j]*(n-j+1));
            }
        }
        return ans;
    }
}
