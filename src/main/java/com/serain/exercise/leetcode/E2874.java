package com.serain.exercise.leetcode;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-14  10:12
 * @Description: TODO
 * @Version: 1.0
 */
public class E2874 {
    public boolean isGood(int[] nums) {
        int[] visited=new int[nums.length];
        for (int num : nums) {
            if(num>=nums.length) return false;
            visited[num]++;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(visited[i]+" "+i);
            if (visited[i] == 0) {
                return false;
            }
            if(i!= nums.length-1&&visited[i]>1){
                return false;
            }
        }
        return true;
    }
}
