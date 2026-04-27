package com.serain.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-27  16:43
 * @Description: TODO
 * @Version: 1.0
 */
public class E1391 {
    public static final Map<Integer,int[][]> map=new HashMap<>();

    static {
        map.put(1,new int[][]{{0,1},{0,-1}});
        map.put(2,new int[][]{{-1,0},{1,0}});
        map.put(3,new int[][]{{0,-1},{1,0}});
        map.put(4,new int[][]{{0,1},{1,0}});
        map.put(5,new int[][]{{0,-1},{-1,0}});
        map.put(6,new int[][]{{0,1},{-1,0}});
    }
    public boolean hasValidPath(int[][] grid) {
        int[][] visited=new int[grid.length][grid[0].length];
        return dfs(grid,0,0,visited);
    }

    private boolean dfs(int[][] grid, int i, int j, int[][] visited) {
        if(i==grid.length-1&&j==grid[0].length-1){
            return true;
        }
        visited[i][j]=1;
        int[][] dirs=map.get(grid[i][j]);
        for(int[] dir:dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(x<0||x>=grid.length||y<0||y>=grid[0].length) continue;
            if(visited[x][y]==1) continue;
            if(!contains(grid[x][y],-dir[0],-dir[1])) continue;
            if(dfs(grid,x,y,visited)) return true;
        }
        return false;
    }

    private boolean contains(int i, int x, int y) {
        return map.get(i)[0][0]==x&&map.get(i)[0][1]==y||map.get(i)[1][0]==x&&map.get(i)[1][1]==y;
    }
}
