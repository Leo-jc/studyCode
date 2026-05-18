package com.serain.exercise.leetcode;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-18  09:49
 * @Description: TODO
 * @Version: 1.0
 */
public class E1345 {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>>map=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            List<Integer> list=map.getOrDefault(arr[i],new ArrayList<>());
            list.add(i);
            map.put(arr[i],list);
        }
        Queue<Integer> queue=new LinkedList<>();
        int n=arr.length;
        boolean[] visited=new boolean[n];
        queue.add(0);
        visited[0]=true;
        int ans=0;
        while(true){
            int size=queue.size();
            while(size-->0){
                int curPos=queue.poll();
                if(curPos==n-1){
                    return ans;
                }
                List<Integer> list=map.get(arr[curPos]);
                if(curPos>0) list.add(curPos-1);
                list.add(curPos+1);
                for(int nextPos:list){
                    if(visited[nextPos]) continue;
                    queue.add(nextPos);
                    visited[nextPos]=true;
                }
                list.clear();
            }
            ans++;
        }
    }
}
