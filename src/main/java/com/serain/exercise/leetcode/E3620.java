package com.serain.exercise.leetcode;

import java.util.*;

public class E3620 {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, _ -> new ArrayList<>());

        int maxWt = -1;
        for (int[] e : edges) {
            int x = e[0], y = e[1], wt = e[2];
            if (online[x] && online[y]) {
                g[x].add(new int[]{y, wt});
                if (x == 0) {
                    maxWt = Math.max(maxWt, wt);
                }
            }
        }

        long[] memo = new long[n];
        int[] visitVer = new int[n]; // 版本号：避免每次二分都 O(n) fill memo

        int left = -1, right = maxWt + 1; // 在权重数组索引上二分
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (dfs(0, mid, g, memo, visitVer, mid + 1) <= k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private long dfs(int x, int lower, List<int[]>[] g,
                     long[] memo, int[] visitVer, int curVer) {
        if (x == g.length - 1) {
            return 0;
        }
        if (visitVer[x] == curVer) {
            return memo[x];
        }
        visitVer[x] = curVer;
        long res = Long.MAX_VALUE / 2;
        for (int[] e : g[x]) {
            int y = e[0], wt = e[1];
            if (wt >= lower) {
                res = Math.min(res, dfs(y, lower, g, memo, visitVer, curVer) + wt);
            }
        }
        return memo[x] = res;
    }
}
