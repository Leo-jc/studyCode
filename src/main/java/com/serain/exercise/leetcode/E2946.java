package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-27  11:52
 * @Description: TODO
 * @Version: 1.0
 */
public class E2946 {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] mat2 = new int[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(mat[i], 0, mat2[i], 0, n);
        }
        for (int i = 0; i < m; i++) {
            if(i%2==0){
                reverse(mat[i], 0, k-1);
                reverse(mat[i], k, n-1);
                reverse(mat[i], 0, n-1);
            }else{
                reverse(mat[i], 0, n-k-1);
                reverse(mat[i], n-k, n-1);
                reverse(mat[i], 0, n-1);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[i][j]!=mat2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public void reverse(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
