package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  14:51
 * @Description: TODO
 * @Version: 1.0
 */
public class E54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] visited=new int[m][n];
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        List<Integer>list = new ArrayList<>();
        int hasVisited=0;
        int count=0;
        int x=0,y=0;
        while (hasVisited<m*n){
            list.add(matrix[x][y]);
            visited[x][y]=1;
            hasVisited++;
            int nextX=x+dx[count];
            int nextY=y+dy[count];
            if (nextX<0||nextX>=m||nextY<0||nextY>=n||visited[nextX][nextY]==1){count=(count+1)%4;}
            x=x+dx[count];
            y=y+dy[count];
        }
        return list;
    }
}
