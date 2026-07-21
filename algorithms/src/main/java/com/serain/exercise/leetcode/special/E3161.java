package com.serain.exercise.leetcode.special;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 3161. 放置障碍物后是否可以放置物块
 *
 * 问题概述：
 * 在一条从原点 0 向 x 轴正方向无限延伸的数轴上，有两种操作：
 * 1) [1, x] —— 在位置 x 放置一个障碍物（保证该位置原本无障碍物）。
 * 2) [2, x, sz] —— 查询在区间 [0, x] 内是否能完全放入一个长度为 sz 的物块。
 *    物块不能与障碍物重合，但可以刚好接触。查询相互独立，不实际放置物块。
 *
 * 解题思路：
 * 1. 使用 TreeMap 维护数轴上的空闲区间（key: 左端点, value: 右端点）。
 *    初始时假设存在一个虚拟大区间 [0, MAX+1]，其中 MAX 为所有查询中 x 的最大值。
 *    MAX+1 作为虚拟右边界，用于处理查询时最后一个区间被截断的情况。
 * 2. 使用线段树维护所有“右端点 <= MAX”的区间长度，支持下标单点更新与区间最大值查询。
 *    通过线段树可在 O(log MAX) 时间内获取 [0, x] 内所有完全包含的区间的最大长度。
 * 3. 对于查询操作 [2, x, sz]：
 *    - 先查询线段树 [0, x] 的最大值，得到右端点不超过 x 的完整区间的最大长度。
 *    - 再通过 TreeMap 的 floorEntry(x) 找到 x 所在（或左侧最近）的区间 [l, r]。
 *      若 x 严格位于该区间内部（x < r），则 [0, x] 末尾还有一段可用长度 x - l。
 *    - 取两者最大值与 sz 比较即可。
 * 4. 对于添加障碍物 [1, x]：找到包含 x 的区间 [l, r]，将其分裂为 [l, x] 和 [x, r]，
 *    并同步更新线段树中对应右端点的长度值。
 *
 * 时间复杂度：O(Q * log(MAX))，其中 Q 为查询数量，MAX 为查询中最大坐标值。
 * 空间复杂度：O(MAX + Q)。
 */
public class E3161 {

    public List<Boolean> getResults(int[][] queries) {
        // 确定坐标上限，用于开线段树
        int maxX = 0;
        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        // 线段树维护每个右端点坐标处的区间长度最大值
        SegmentTree seg = new SegmentTree(maxX + 1);

        // intervals: 左端点 -> 右端点，维护当前所有空闲区间
        TreeMap<Integer, Integer> intervals = new TreeMap<>();
        intervals.put(0, maxX + 1);

        List<Boolean> results = new ArrayList<>();

        for (int[] q : queries) {
            if (q[0] == 1) {
                // 操作类型 1：在 x 处放置障碍物
                int x = q[1];
                Map.Entry<Integer, Integer> entry = intervals.floorEntry(x);
                int left = entry.getKey();
                int right = entry.getValue();

                // 删除原区间 [left, right]
                intervals.remove(left);
                if (right <= maxX) {
                    seg.update(right, 0);
                }

                // 插入新区间 [left, x]
                intervals.put(left, x);
                if (x <= maxX) {
                    seg.update(x, x - left);
                }

                // 插入新区间 [x, right]
                intervals.put(x, right);
                if (right <= maxX) {
                    seg.update(right, right - x);
                }
            } else {
                // 操作类型 2：查询 [0, x] 内是否能放置长度为 sz 的物块
                int x = q[1];
                int sz = q[2];

                // 右端点不超过 x 的所有完整区间中的最大长度
                int maxLen = seg.query(0, x);

                // 找到 x 所在或左侧最近的区间 [left, right]
                Map.Entry<Integer, Integer> entry = intervals.floorEntry(x);
                int left = entry.getKey();
                int right = entry.getValue();

                // 若 x 位于区间内部，则末尾还有一段从 left 到 x 的可用长度
                if (x < right) {
                    maxLen = Math.max(maxLen, x - left);
                }

                results.add(maxLen >= sz);
            }
        }

        return results;
    }

    /**
     * 线段树：单点更新 + 区间最大值查询。
     */
    private static class SegmentTree {
        private final int n;
        private final int[] tree;

        SegmentTree(int size) {
            this.n = size;
            this.tree = new int[4 * n];
        }

        void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        private void update(int node, int l, int r, int index, int value) {
            if (l == r) {
                tree[node] = value;
                return;
            }
            int mid = (l + r) >>> 1;
            if (index <= mid) {
                update(node << 1, l, mid, index, value);
            } else {
                update(node << 1 | 1, mid + 1, r, index, value);
            }
            tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
        }

        int query(int left, int right) {
            if (left > right) {
                return 0;
            }
            return query(1, 0, n - 1, left, right);
        }

        private int query(int node, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) {
                return 0;
            }
            if (ql <= l && r <= qr) {
                return tree[node];
            }
            int mid = (l + r) >>> 1;
            return Math.max(
                    query(node << 1, l, mid, ql, qr),
                    query(node << 1 | 1, mid + 1, r, ql, qr)
            );
        }
    }

    /**
     * 本地测试用例
     */
    public static void main(String[] args) {
        E3161 solution = new E3161();

        // 示例 1（题目给出）
        int[][] queries1 = {{1, 2}, {2, 3, 3}, {2, 3, 1}, {2, 2, 2}};
        System.out.println("示例 1: " + solution.getResults(queries1)); // 预期: [false, true, true]

        // 示例 2：多个障碍物
        int[][] queries2 = {{1, 3}, {1, 1}, {2, 4, 2}, {2, 4, 1}};
        System.out.println("示例 2: " + solution.getResults(queries2)); // 预期: [true, true]

        // 示例 3：无障碍物直接查询
        int[][] queries3 = {{2, 5, 5}, {1, 2}, {2, 5, 3}, {2, 5, 4}};
        System.out.println("示例 3: " + solution.getResults(queries3)); // 预期: [true, true, false]
    }
}
