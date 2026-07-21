package com.serain.exercise.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

public class E1260 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k %= total;

        List<List<Integer>> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            result.add(row);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int newIdx = (i * n + j + k) % total;
                result.get(newIdx / n).set(newIdx % n, grid[i][j]);
            }
        }
        return result;
    }
}
