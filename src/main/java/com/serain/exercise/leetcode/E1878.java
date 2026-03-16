package com.serain.exercise.leetcode;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise
 * @Author: Serain
 * @CreateTime: 2026-03-16  16:43
 * @Description: TODO
 * @Version: 1.0
 */
public class E1878 {
    public int[] getBiggestThree(int[][] grid) {
        int[][] left=new int[grid.length+2][grid[0].length+2];
        int[][] right=new int[grid.length+2][grid[0].length+2];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                left[i+1][j+1]=left[i][j+2]+grid[i][j];
                right[i+1][j+1]=right[i][j]+grid[i][j];
            }
        }
        TreeSet<Integer> set=new TreeSet<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                for(int k=i;k<grid.length;k++){
                    int X= i+(k-i)/2;
                    int leftY=j-(k-i)/2;
                    int rightY=j+(k-i)/2;
                    if(leftY<0||rightY>=grid[0].length) break;
                    int sum=left[X+1][leftY+1]-left[i][j]+right[X+1][rightY+1]-right[i][j]+
                            left[k+1][j+1]-left[X][rightY]+right[k+1][j+1]-right[X][leftY]
                            -grid[i][j]-grid[X][leftY]-grid[X][rightY]-grid[k][j];
                    System.out.println(left[X+1][leftY+1]-left[i][j]);
                    System.out.println(right[X+1][rightY+1]-right[i][j]);
                    System.out.println(left[k+1][j+1]-left[X][rightY]);
                    System.out.println(right[k+1][j+1]-right[X][leftY]);
                    System.out.println(X+" "+leftY+" "+rightY+" "+k+" "+sum);
                    set.add(sum);
                }
            }
        }
        int num=0;
        List<Integer> list=new ArrayList<>();
        while(num<3&&!set.isEmpty()){
            list.add(set.pollLast());
            num++;
        }
        return list.stream().mapToInt(i->i).toArray();
    }
}
