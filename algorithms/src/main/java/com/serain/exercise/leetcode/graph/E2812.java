package com.serain.exercise.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

public class E2812 {
    private final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dis = new int[n][n];
        List<int[]> q = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) > 0) {
                    q.add(new int[]{i, j});
                } else {
                    dis[i][j] = -1;
                }
            }
        }

        List<List<int[]>> groups = new ArrayList<List<int[]>>();
        groups.add(q);
        // 多源 BFS
        while (!q.isEmpty()) { 
            List<int[]> tmp = q;
            q = new ArrayList<>();
            for (int[] p : tmp) {
                for (int[] d : DIRS) {
                    int x = p[0] + d[0], y = p[1] + d[1];
                    if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] < 0) {
                        dis[x][y] = groups.size();
                        q.add(new int[]{x, y});
                    }
                }
            }
            groups.add(q); // 相同 dis 分组记录
        }

        // 初始化并查集
        fa = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            fa[i] = i;
        }

        // 从大到小枚举答案
        for (int ans = groups.size() - 2; ans > 0; ans--) {
            for (int[] p : groups.get(ans)) {
                int i = p[0], j = p[1];
                for (int[] d : DIRS) {
                    int x = i + d[0], y = j + d[1];
                    if (0 <= x && x < n && 0 <= y && y < n && dis[x][y] >= ans) {
                        fa[find(x * n + y)] = find(i * n + j);
                    }
                }
            }
            if (find(0) == find(n * n - 1)) { // 写这里判断更快些
                return ans;
            }
        }
        return 0;
    }

    // 并查集模板
    private int[] fa;

    private int find(int x) {
        if (fa[x] != x) {
            fa[x] = find(fa[x]);
        }
        return fa[x];
    }
}
