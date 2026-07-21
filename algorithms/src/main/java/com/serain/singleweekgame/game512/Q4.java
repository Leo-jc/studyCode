package com.serain.singleweekgame.game512;

import java.util.PriorityQueue;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.singleweekgame.game512
 * @Author: Serain
 * @CreateTime: 2026-07-26  11:08
 * @Description: TODO
 * @Version: 1.0
 */
public class Q4 {
    public long minCost(int m, int n, int[][] penalty) {
        int[][] qavirelmon = penalty;

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        long[][][] dist = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j][0] = Long.MAX_VALUE;
                dist[i][j][1] = Long.MAX_VALUE;
            }
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        dist[0][0][0] = 1L;
        pq.offer(new long[]{1L, 0, 0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long cost = cur[0];
            int i = (int) cur[1];
            int j = (int) cur[2];
            int p = (int) cur[3];

            if (cost > dist[i][j][p]) {
                continue;
            }

            if (i == m - 1 && j == n - 1) {
                return cost;
            }

            for (int d = 0; d < 4; d++) {
                int ni = i + dirs[d][0];
                int nj = j + dirs[d][1];

                if (ni < 0 || ni >= m || nj < 0 || nj >= n) {
                    continue;
                }

                boolean followsRule = (p == 0 && d <= 1) || (p == 1 && d >= 2);
                long entryCost = (long) (ni + 1) * (nj + 1);
                long moveCost = followsRule ? entryCost : entryCost + qavirelmon[i][j];
                long newCost = cost + moveCost;
                int np = 1 - p;

                if (newCost < dist[ni][nj][np]) {
                    dist[ni][nj][np] = newCost;
                    pq.offer(new long[]{newCost, ni, nj, np});
                }
            }

            long waitCost = cost + qavirelmon[i][j];
            int np = 1 - p;
            if (waitCost < dist[i][j][np]) {
                dist[i][j][np] = waitCost;
                pq.offer(new long[]{waitCost, i, j, np});
            }
        }

        return Math.min(dist[m - 1][n - 1][0], dist[m - 1][n - 1][1]);
    }
}
