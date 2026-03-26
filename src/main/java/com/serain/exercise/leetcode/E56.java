package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  21:48
 * @Description: TODO
 * @Version: 1.0
 */
public class E56 {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.sort((a,b)->{return a[1]-b[1];});
        List<int[]> ans=new ArrayList<>();
        int left=list.getFirst()[0];
        int right=list.getFirst()[1];
        for(int i=1;i<list.size();i++){
            if(list.get(i)[0]>right){
                ans.add(new int[]{left,right});
                left=list.get(i)[0];
                right=list.get(i)[1];
            }
            right=Math.max(right,list.get(i)[1]);
            left=Math.min(left,list.get(i)[0]);
        }
        ans.add(new int[]{left,right});
        int[][] result = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
}
