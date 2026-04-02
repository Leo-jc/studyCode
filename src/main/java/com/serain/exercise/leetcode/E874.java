package com.serain.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-06  19:49
 * @Description: TODO
 * @Version: 1.0
 */
public class E874 {
    public int robotSim(int[] commands, int[][] obstacles) {
        Map<String,Boolean> map=new HashMap<>();
        for (int[] obstacle : obstacles) {
            map.put(obstacle[0]+","+obstacle[1], true);
        }
        int[][] dirs=new int[][]{{0,1},{1,0},{0,-1},{-1,0}} ;
        int x=0,y=0,d=0;
        int res=0;
        for (int command : commands){
            if (command==-1){
                d=(d+1)%4;
            }else if (command==-2){
                d=(d+3)%4;
            }else {
                for (int i = 0; i < command; i++) {
                    int nx=x+dirs[d][0];
                    int ny=y+dirs[d][1];
                    System.out.println(nx+","+ny);
                    if (map.containsKey(nx+","+ny)){
                        break;
                    }
                    x=nx;
                    y=ny;
                    res=Math.max(res,x*x+y*y);
                }
            }
        }
        return res;
    }
}
