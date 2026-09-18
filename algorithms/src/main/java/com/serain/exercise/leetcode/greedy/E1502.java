package com.serain.exercise.leetcode.greedy;

import java.util.*;

/**
*@BelongsProject: StudyCode
*@BelongsPackage: com.serain.exercise.leetcode.greedy
*@Author: Serain
*@CreateTime: 2026-09-18  21:16
*@Description: TODO
*@Version: 1.0
*/
public class E1502 {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        // first[c] / last[c]: 字母 c 第一次 / 最后一次出现的下标
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        // 对每个字母求"闭包区间": 从 [首次出现, 最后一次出现] 出发,
        // 区间内每出现一个新字母 d, 就把区间扩张到覆盖 d 的全部出现位置,
        // 直到区间内所有字母的出现范围都被完整包含 (满足条件2的最小区间)。
        List<int[]> cand = new ArrayList<>(); // 每项 {L, R, len}
        int[] queue = new int[n];             // 工作队列, 保证每个下标最多入队一次 -> O(n)
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;
            int L = first[c], R = last[c];
            int head = 0, tail = 0;
            for (int i = L; i <= R; i++) queue[tail++] = i;
            while (head < tail) {
                int i = queue[head++];
                int d = s.charAt(i) - 'a';
                if (last[d] > R) {              // d 的出现范围超出右边界 -> 向右扩
                    for (int j = R + 1; j <= last[d]; j++) queue[tail++] = j;
                    R = last[d];
                }
                if (first[d] < L) {             // d 的出现范围超出左边界 -> 向左扩
                    for (int j = L - 1; j >= first[d]; j--) queue[tail++] = j;
                    L = first[d];
                }
            }
            cand.add(new int[]{L, R, R - L + 1});
        }

        // 带权区间调度: 按右端点排序后 DP。
        // f[k] = 前 k 个候选中的最优值, 编码为 count*K - totalLen (K 很大 => 数量优先)
        cand.sort((a, b) -> {
            if(a[1]==b[1]) return b[0]-a[0];
            return a[1]-b[1];
        });
        int m = cand.size();
        List<String> result = new ArrayList<>();
        int r = -1;
        for (int[] cur : cand) {
            if (cur[0] > r) {
                result.add(s.substring(cur[0], cur[1] + 1));
                r = cur[1];
            }
        }
        return result;
    }
}