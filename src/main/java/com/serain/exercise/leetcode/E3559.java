package com.serain.exercise.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class E3559 {

    private static final int MOD = 1000000007;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        // 邻接表建图：直接使用数组+ArrayList，避免HashMap装箱与TreeNode对象创建
        List<Integer>[] adj = buildAdjacencyList(n, edges);

        // 计算二进制提升所需的最大对数级
        int logN = 32 - Integer.numberOfLeadingZeros(n);
        int[][] up = new int[n + 1][logN];
        int[] depth = new int[n + 1];

        // BFS 计算深度与直接父节点信息
        bfs(n, adj, depth, up);

        // 构建二进制提升表（倍增法），用于快速求 LCA
        buildBinaryLiftingTable(n, up, logN);

        // 预计算 2 的幂次，避免每次查询都调用快速幂
        int[] pow2 = preComputePowersOfTwo(n);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            int lca = getLCA(u, v, depth, up, logN);
            // 路径上的边数 = depth[u] + depth[v] - 2 * depth[lca]
            int dist = depth[u] + depth[v] - 2 * depth[lca];
            // 距离为 k 时，答案为 2^(k-1)；距离为 0 时答案为 0
            ans[i] = dist == 0 ? 0 : pow2[dist - 1];
        }
        return ans;
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
     * 迭代BFS计算以节点1为根的树的深度与直接父节点。
     * 逐层处理，无需为每个节点创建配对对象。
     */
    private void bfs(int n, List<Integer>[] adj, int[] depth, int[][] up) {
        Deque<Integer> queue = new ArrayDeque<>(n);
        boolean[] visited = new boolean[n + 1];

        queue.offer(1);
        visited[1] = true;
        depth[1] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    depth[neighbor] = depth[node] + 1;
                    up[neighbor][0] = node; // 记录直接父节点
                    queue.offer(neighbor);
                }
            }
        }
    }

    /**
     * 构建二进制提升表（倍增法）。
     * up[node][j] 表示从 node 向上跳 2^j 步到达的祖先节点。
     */
    private void buildBinaryLiftingTable(int n, int[][] up, int logN) {
        for (int j = 1; j < logN; j++) {
            for (int i = 1; i <= n; i++) {
                if (up[i][j - 1] != 0) {
                    up[i][j] = up[up[i][j - 1]][j - 1];
                }
            }
        }
    }

    /**
     * 预计算 2^0 到 2^(n-1) 的幂，取模 MOD。
     * pow2[i] = (2^i) % MOD
     */
    private int[] preComputePowersOfTwo(int n) {
        int[] pow2 = new int[n]; // 最大需要 2^(n-1)，距离最大为 n-1
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (int) (((long) pow2[i - 1] * 2) % MOD);
        }
        return pow2;
    }

    /**
     * 使用二进制提升法求节点 u 和 v 的最近公共祖先（LCA）。
     * 时间复杂度 O(log n)。
     */
    private int getLCA(int u, int v, int[] depth, int[][] up, int logN) {
        // 保证 depth[u] >= depth[v]
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        // 将较深的节点 u 提升到与 v 相同的深度
        int diff = depth[u] - depth[v];
        for (int j = 0; j < logN; j++) {
            if (((diff >> j) & 1) == 1) {
                u = up[u][j];
            }
        }
        // 如果 u == v，说明 v 是 u 的祖先
        if (u == v) {
            return u;
        }
        // 同时向上跳跃，直到父节点相同
        for (int j = logN - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        return up[u][0];
    }
}
