package com.serain.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * 计算以节点1为根的树的最大深度depth，返回 2^(depth-2) % MOD。
 *
 * 原实现存在以下问题：
 * 1. TreeNode.children 未初始化（NPE）
 * 2. 实例字段deepth在多次调用时状态污染
 * 3. 递归DFS可能栈溢出
 * 4. HashMap+TreeNode建图冗余对象创建
 * 5. O(n) DP可用快速幂优化为O(log n)
 *
 * 优化后：
 * - 数组邻接表替代HashMap+TreeNode，减少对象分配
 * - 迭代BFS替代递归DFS，防栈溢出且缓存友好
 * - 快速幂(binary exponentiation)替代O(n) DP
 * - 时间复杂度：O(n + log n)，空间复杂度：O(n)
 */
public class E3558 {

    private static final int MOD = 1000000007;

    /**
     * @param edges 树的边列表，节点编号1..n，n = edges.length + 1
     * @return 2^(maxDepth-2) % MOD，maxDepth为以节点1为根的树的最大深度
     */
    public int assignEdgeWeights(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return 0;
        }

        int n = edges.length + 1;

        // 邻接表建图：直接使用数组+ArrayList，避免HashMap装箱与TreeNode对象创建
        List<Integer>[] adj = buildAdjacencyList(n, edges);

        // 迭代BFS求最大深度，避免递归栈溢出
        int maxDepth = computeMaxDepth(n, adj);

        if (maxDepth < 2) {
            return 0;
        }

        // 快速幂计算 2^(maxDepth-2) % MOD，O(log n)
        return (int) modPow(2, maxDepth - 2, MOD);
    }

    @SuppressWarnings("unchecked")
    private List<Integer>[] buildAdjacencyList(int n, int[][] edges) {
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>(4); // 小初始容量减少扩容
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        return adj;
    }

    /**
     * 迭代BFS计算以节点1为根的树的最大深度。
     * 逐层处理，无需为每个节点创建配对对象。
     */
    private int computeMaxDepth(int n, List<Integer>[] adj) {
        Deque<Integer> queue = new ArrayDeque<>(n);
        boolean[] visited = new boolean[n + 1];

        queue.offer(1);
        visited[1] = true;

        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                int node = queue.poll();
                for (int neighbor : adj[node]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return depth;
    }

    /**
     * 快速幂（二分幂）：计算 (base^exp) % mod，时间复杂度 O(log exp)。
     */
    private long modPow(long base, long exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}

