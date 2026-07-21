package com.serain.exercise.leetcode.graph;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.graph
 * @Author: Serain
 * @CreateTime: 2026-05-08  10:13
 * @Description: LeetCode 第 3629 题
 * @Version: 1.0
 */
public class E3629 {
        private static final int MX = 1_000_001;
        private static final List<Integer>[] primeFactors = new ArrayList[MX];
        private static boolean initialized = false;

        // 这样写比 static block 更快
        private void init() {
            if (initialized) {
                return;
            }
            initialized = true;

            Arrays.setAll(primeFactors, _ -> new ArrayList<>());
            // 预处理每个数的质因子列表，思路同埃氏筛
            for (int i = 2; i < MX; i++) {
                if (primeFactors[i].isEmpty()) { // i 是质数
                    for (int j = i; j < MX; j += i) { // i 的倍数有质因子 i
                        primeFactors[j].add(i);
                    }
                }
            }
        }

        public int minJumps(int[] nums) {
            init();

            int n = nums.length;
            Map<Integer, List<Integer>> groups = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int p : primeFactors[nums[i]]) {
                    // 对于质数 p，可以跳到下标 i
                    groups.computeIfAbsent(p, _ -> new ArrayList<>()).add(i);
                }
            }

            int ans = 0;
            boolean[] vis = new boolean[n];
            vis[0] = true;
            List<Integer> q = List.of(0);

            while (true) {
                List<Integer> tmp = q;
                q = new ArrayList<>();
                for (int i : tmp) {
                    if (i == n - 1) {
                        return ans;
                    }
                    List<Integer> idx = groups.computeIfAbsent(nums[i], _ -> new ArrayList<>());
                    idx.add(i + 1);
                    if (i > 0) {
                        idx.add(i - 1);
                    }
                    for (int j : idx) { // 可以从 i 跳到 j
                        if (!vis[j]) {
                            vis[j] = true;
                            q.add(j);
                        }
                    }
                    idx.clear(); // 避免重复访问下标列表
                }
                ans++;
            }
        }
}

class Solution3629 {
    public static final int MAX_NUM=1000001;
    public static final List<Integer>[] graph=new ArrayList[MAX_NUM];
    private static boolean initialized = false;

    public void initGraph(){
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.setAll(graph, _ -> new ArrayList<>());
        for (int i = 2; i < MAX_NUM; i++) {
            if (graph[i].isEmpty()) { // i 是质数
                for (int j = i; j < MAX_NUM; j += i) { // i 的倍数有质因子 i
                    graph[j].add(i);
                }
            }
        }
    }
    public int minJumps(int[] nums) {
        initGraph();
        int n=nums.length;
        Map<Integer,List<Integer>> map=new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j:graph[nums[i]]){
                map.computeIfAbsent(j,k->new ArrayList<>()).add(i);
            }
        }
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);
        boolean[] vis=new boolean[n];
        vis[0]=true;
        int ans=0;
        while(true){
            int size=q.size();
            while(size-->0&&!q.isEmpty()){
                int cur=q.poll();
                if(cur==n-1) return ans;
                List<Integer> idx=map.getOrDefault(nums[cur],new ArrayList<>());
                idx.add(cur+1);
                if(cur > 0) idx.add(cur-1);
                for(int id:idx){
                    if(!vis[id]){
                        vis[id]=true;
                        q.offer(id);
                    }
                }
                idx.clear();
            }
            ans++;
        }
    }

}
