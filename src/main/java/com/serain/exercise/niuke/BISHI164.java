package com.serain.exercise.niuke;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.niuke
 * @Author: Serain
 * @CreateTime: 2026-04-28  15:34
 * @Description: TODO
 * @Version: 1.0
 */
public class BISHI164 {
    public static final int MIN_VALUE=10;
    public static final int MAX_VALUE=300;
    public static void main(String[] args) {
        Map<Integer, Integer> map=new HashMap<>();
        bfs(map);
        Scanner in = new Scanner(System.in);
        int T=in.nextInt();
        while(T-->0){
            //1.输入
            int a=in.nextInt(),b=in.nextInt(),c=in.nextInt(),d=in.nextInt();
            //2.进行搜索
            int ans=0;
            ans+=map.get(a);
            ans+=map.get(b);
            ans+=map.get(c);
            ans+=map.get(d);
            System.out.println(ans);
        }
    }

    private static void bfs(Map<Integer, Integer> map) {
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(MIN_VALUE);
        map.put(MIN_VALUE, 0);
        while(!queue.isEmpty()){
            int cur=queue.poll();
            int[] nexts=new int[]{cur+1, cur-1, cur+10, cur-10,cur+100, cur-100,300,10};
            for (int next : nexts) {
                if(next<=MAX_VALUE && next>=MIN_VALUE && !map.containsKey(next)){
                    queue.offer(next);
                    map.put(next, map.get(cur)+1);
                }
            }
        }
    }

}
