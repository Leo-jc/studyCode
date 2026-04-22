package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-11  16:55
 * @Description: TODO
 * @Version: 1.0
 */
public class E74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=0;
        int n=matrix[0].length-1;
        while(m<matrix.length&&n>=0){
            if(matrix[m][n]==target){
                return true;
            }else if(matrix[m][n]>target){
                n--;
            }else{
                m++;
            }
        }
        return false;
    }
}
