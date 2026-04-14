package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-14  17:45
 * @Description: TODO
 * @Version: 1.0
 */
public class E417 {

    public  static int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights){
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited1 = new boolean[m][n];
        boolean[][] visited2 = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(heights,i,0,visited1);
            dfs(heights,i,n-1,visited2);
        }
        for (int i = 0; i < n; i++) {
            dfs(heights,0,i,visited1);
            dfs(heights,m-1,i,visited2);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(visited1[i][j] && visited2[i][j]){
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for(int i=0;i<4;i++){
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if(visited[nx][ny]) continue;
            if(nx < 0 || nx >= heights.length || ny < 0 || ny >= heights[0].length) continue;
            if(heights[nx][ny] < heights[x][y]) continue;
            dfs(heights,nx,ny,visited);
        }
    }
}
