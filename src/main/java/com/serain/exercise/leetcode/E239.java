package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  15:56
 * @Description: TODO
 * @Version: 1.0
 */
public class E239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res=new int[nums.length-k+1];
        List<int[]> list=new ArrayList<>();
        for(int i=0;i<k;i++){
            while(!list.isEmpty()&&nums[i]>list.getLast()[0]){
                list.removeLast();
            }
            list.add(new int[]{nums[i],i});
        }
        res[0]=list.getFirst()[0];
        for(int i=k;i<nums.length;i++){
            while(!list.isEmpty()&&list.getFirst()[1]<=i-k){
                list.removeFirst();
            }
            while(!list.isEmpty()&&nums[i]>list.getLast()[0]){
                list.removeLast();
            }
            list.add(new int[]{nums[i],i});
            res[i-k+1]=list.getFirst()[0];
        }
        return res;
    }
}
