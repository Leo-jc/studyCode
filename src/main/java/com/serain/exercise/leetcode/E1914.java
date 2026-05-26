package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-09  16:53
 * @Description: LeetCode 第 1914 题
 * @Version: 1.0
 */
public class E1914 {
    public static final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<Math.min(m,n)/2;i++){
            int m0=m-2*i;
            int n0=n-2*i;
            int x=i;
            int y=i;
            List<Integer> list = new ArrayList<>();
            for(int[] dir:dirs){
                for(int j=0;j<n0-1;j++){
                    list.add(grid[x][y]);
                    x+=dir[0];
                    y+=dir[1];
                }
                int temp=n0;
                n0=m0;
                m0=temp;
            }
            int shift=k%list.size();
            Collections.rotate(list,-shift);
            int pos=0;
            for(int[] dir:dirs){
                for(int j=0;j<n0-1;j++){
                    grid[x][y]=list.get(pos++);
                    x+=dir[0];
                    y+=dir[1];
                }
                int temp=n0;
                n0=m0;
                m0=temp;
            }
        }
        return grid;
    }
}
