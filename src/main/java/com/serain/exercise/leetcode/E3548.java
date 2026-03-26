package com.serain.exercise.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  14:27
 * @Description: TODO
 * @Version: 1.0
 */
public class E3548 {
        public boolean canPartitionGrid(int[][] grid) {
            long total = 0;
            for (int[] row : grid) {
                for (int x : row) {
                    total += x;
                }
            }

            // 水平分割 or 垂直分割
            return check(grid, total) || check(rotate(grid), total);
        }

        private boolean check(int[][] a, long total) {
            // 删除上半部分中的一个数
            if (f(a, total)) {
                return true;
            }
            reverse(a);
            // 删除下半部分中的一个数
            return f(a, total);
        }

        private boolean f(int[][] a, long total) {
            int m = a.length, n = a[0].length;
            Set<Long> st = new HashSet<>();
            st.add(0L); // 0 对应不删除数字
            long s = 0;
            for (int i = 0; i < m - 1; i++) {
                int[] row = a[i];
                for (int j = 0; j < n; j++) {
                    int x = row[j];
                    s += x;
                    // 第一行，不能删除中间元素
                    if (i > 0 || j == 0 || j == n - 1) {
                        st.add((long) x);
                    }
                }
                // 特殊处理只有一列的情况，此时只能删除第一个数或者分割线上那个数
                if (n == 1) {
                    if (s * 2 == total || s * 2 - total == a[0][0] || s * 2 - total == row[0]) {
                        return true;
                    }
                    continue;
                }
                if (st.contains(s * 2 - total)) {
                    return true;
                }
                // 如果分割到更下面，那么可以删第一行的元素
                if (i == 0) {
                    for (int x : row) {
                        st.add((long) x);
                    }
                }
            }
            return false;
        }

        // 顺时针旋转矩阵 90°
        private int[][] rotate(int[][] a) {
            int m = a.length, n = a[0].length;
            int[][] b = new int[n][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    b[j][m - 1 - i] = a[i][j];
                }
            }
            return b;
        }

        private void reverse(int[][] a) {
            for (int i = 0, j = a.length - 1; i < j; i++, j--) {
                int[] tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }

}
