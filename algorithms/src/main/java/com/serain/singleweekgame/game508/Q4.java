package com.serain.singleweekgame.game508;

import java.util.*;

public class Q4 {
    public long[] minTimeMaxPower(int n, int[][] edges, int power, int[] cost, int source, int target) {
        // 构建邻接表
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        // dist[node][remainingPower] = 到达该状态的最短时间
        long[][] dist = new long[n][power + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // 优先队列: [节点, 剩余电量, 累计时间]
        // 优先按时间升序，时间相同时按剩余电量降序（电量越高越好）
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return Long.compare(a[2], b[2]);
            return Long.compare(b[1], a[1]);
        });

        dist[source][power] = 0;
        pq.offer(new long[]{source, power, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            int remPower = (int) cur[1];
            long curDist = cur[2];

            // 过期的状态，跳过
            if (curDist > dist[u][remPower]) continue;

            // 到达目标，因为优先队列的排序保证了这是最优路径
            if (u == target) {
                return new long[]{curDist, remPower};
            }

            List<int[]> neighbors = graph.get(u);
            if (neighbors == null) continue;

            for (int[] edge : neighbors) {
                int v = edge[0];
                int w = edge[1];
                int newRemPower = remPower - cost[u];
                if (newRemPower < 0) continue;  // 电量不足，无法离开当前节点

                long newDist = curDist + w;
                if (newDist < dist[v][newRemPower]) {
                    dist[v][newRemPower] = newDist;
                    pq.offer(new long[]{v, newRemPower, newDist});
                }
            }
        }

        // 无法到达目标
        return new long[]{-1, -1};
    }
}
