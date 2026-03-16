package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E3600 {
    public static void main(String[] args) {
        Solution3600 solution = new Solution3600();
        // 测试用例
        int n = 4;
        int[][] edges = {{0, 1, 5, 1}, {1, 2, 3, 0}, {2, 3, 4, 0}, {0, 3, 6, 0}};
        int k = 1;
        System.out.println(solution.maxStability(n, edges, k));
    }
}

class UnionFind {
    private final int[] fa;
    private final int[] rank; // 秩数组，用于优化合并
    public int cc; // 连通块个数

    UnionFind(int n) {
        fa = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
            rank[i] = 1;
        }
        cc = n;
    }

    public int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }

    public boolean merge(int from, int to) {
        int x = find(from);
        int y = find(to);
        if (x == y) {
            return false;
        }
        // 按秩合并：将秩小的树合并到秩大的树下
        if (rank[x] < rank[y]) {
            fa[x] = y;
        } else if (rank[x] > rank[y]) {
            fa[y] = x;
        } else {
            fa[y] = x;
            rank[x]++;
        }
        cc--;
        return true;
    }
}

class Solution3600 {
    public int maxStability(int n, int[][] edges, int k) {
        if (edges == null || edges.length == 0) {
            return n == 1 ? 0 : -1;
        }

        UnionFind uf = new UnionFind(n);
        UnionFind allUf = new UnionFind(n);
        int minS1 = Integer.MAX_VALUE;

        // 第一步：处理所有必选边
        for (int[] e : edges) {
            int x = e[0], y = e[1], s = e[2], must = e[3];
            if (must == 1) {
                if (!uf.merge(x, y)) {
                    return -1; // 必选边成环
                }
                minS1 = Math.min(minS1, s);
            }
            allUf.merge(x, y);
        }

        // 检查图是否连通
        if (allUf.cc > 1) {
            return -1;
        }

        // 如果只需选必选边
        if (uf.cc == 1) {
            return minS1;
        }

        // 第二步：Kruskal 求最大生成树
        Arrays.sort(edges, (a, b) -> b[2] - a[2]);
        List<Integer> selectedEdges = new ArrayList<>();
        for (int[] e : edges) {
            int x = e[0], y = e[1], s = e[2], must = e[3];
            if (must == 0 && uf.merge(x, y)) {
                selectedEdges.add(s);
            }
        }

        // 第三步：计算答案
        int m = selectedEdges.size();
        int ans = Math.min(minS1, selectedEdges.get(m - 1) * 2);
        if (k < m) {
            ans = Math.min(ans, selectedEdges.get(m - 1 - k));
        }
        return ans;
    }
}
