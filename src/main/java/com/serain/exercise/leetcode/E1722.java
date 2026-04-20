package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-21  10:26
 * @Description: TODO
 * @Version: 1.0
 */
public class E1722 {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n=source.length;
        UnionFindSet ufs = new UnionFindSet(n);
        for (int[] swap : allowedSwaps) {
            ufs.union(swap[0], swap[1]);
        }
        
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = ufs.find(i);
            map.computeIfAbsent(root, k -> new HashMap<>())
               .merge(source[i], 1, Integer::sum);
        }
        
        int res = 0;
        for(int i = 0; i < n; i++){
            int root = ufs.find(i);
            Map<Integer, Integer> valueCount = map.get(root);
            if (valueCount != null && valueCount.getOrDefault(target[i], 0) > 0) {
                valueCount.merge(target[i], -1, Integer::sum);
                if (valueCount.get(target[i]) == 0) {
                    valueCount.remove(target[i]);
                }
            } else {
                res++;
            }
        }
        return res;
    }
}

class UnionFindSet{
    private final int[] parent;
    private final int[] rank;
    private int n;
    public UnionFindSet(int n){
        this.n = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    public int find(int x){
        if (x != parent[x]){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot){
            return;
        }
        if (rank[xRoot] < rank[yRoot]){
            parent[xRoot] = yRoot;
        }else if (rank[xRoot] > rank[yRoot]){
            parent[yRoot] = xRoot;
        }else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }
}
