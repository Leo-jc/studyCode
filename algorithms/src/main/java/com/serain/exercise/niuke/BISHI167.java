package com.serain.exercise.niuke;

import java.util.*;

/**
 * 牛客网 - 完美迷宫
 * <p>
 * 判断有向图是否为“半连通图”：对任意两不同顶点 x, y，
 * 要么 x 可达 y，要么 y 可达 x。
 * <p>
 * 思路：
 * 1. 用 Kosaraju 算法（迭代 DFS，避免递归栈溢出）求强连通分量（SCC）。
 * 2. 将 SCC 缩点得到缩点后的 DAG。
 * 3. 半连通图等价于缩点后的 DAG 存在哈密顿路径；
 *    对 DAG 而言，这又等价于拓扑排序唯一。
 * 4. 用 Kahn 算法拓扑排序，若任意时刻存在多个入度为 0 的点，
 *    则拓扑序不唯一，输出 No；否则输出 Yes。
 * <p>
 * 输出格式：若题目要求 YES/NO 或 1/0，请自行替换 "Yes"/"No"。
 */
public class BISHI167 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        // 原图与反图（链式前向星）
        int[] head = new int[n + 1];
        int[] rHead = new int[n + 1];
        Arrays.fill(head, -1);
        Arrays.fill(rHead, -1);
        int[] to = new int[m];
        int[] nxt = new int[m];
        int[] rTo = new int[m];
        int[] rNxt = new int[m];
        int idx = 0;

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            // 原图 u -> v
            to[idx] = v;
            nxt[idx] = head[u];
            head[u] = idx;
            // 反图 v -> u
            rTo[idx] = u;
            rNxt[idx] = rHead[v];
            rHead[v] = idx;
            idx++;
        }

        // ---------- Kosaraju 第一遍：记录完成顺序 ----------
        boolean[] visited = new boolean[n + 1];
        int[] order = new int[n];
        int orderTop = 0;
        int[] stack = new int[n + 1];
        int[] eIdx = new int[n + 1];

        for (int start = 1; start <= n; start++) {
            if (visited[start]) continue;
            int top = 0;
            stack[top] = start;
            eIdx[top] = head[start];
            visited[start] = true;
            while (top >= 0) {
                int u = stack[top];
                int e = eIdx[top];
                if (e == -1) {
                    // 所有出边已处理，记录完成时间
                    order[orderTop++] = u;
                    top--;
                } else {
                    eIdx[top] = nxt[e];
                    int v = to[e];
                    if (!visited[v]) {
                        visited[v] = true;
                        stack[++top] = v;
                        eIdx[top] = head[v];
                    }
                }
            }
        }

        // ---------- Kosaraju 第二遍：在反图上找 SCC ----------
        int[] comp = new int[n + 1];
        int sccCount = 0;

        for (int i = orderTop - 1; i >= 0; i--) {
            int u = order[i];
            if (comp[u] != 0) continue;
            sccCount++;
            int top = 0;
            stack[top++] = u;
            comp[u] = sccCount;
            while (top > 0) {
                int cur = stack[--top];
                for (int e = rHead[cur]; e != -1; e = rNxt[e]) {
                    int v = rTo[e];
                    if (comp[v] == 0) {
                        comp[v] = sccCount;
                        stack[top++] = v;
                    }
                }
            }
        }

        // 只缩成一个点，任意两点互相可达
        if (sccCount == 1) {
            System.out.println("Yes");
            return;
        }

        // ---------- 构建缩点 DAG（去重边） ----------
        int[] cHead = new int[sccCount + 1];
        Arrays.fill(cHead, -1);
        int[] cTo = new int[m];
        int[] cNxt = new int[m];
        int[] cInDegree = new int[sccCount + 1];
        int cIdx = 0;
        HashSet<Long> edgeSet = new HashSet<>(m * 2);

        for (int u = 1; u <= n; u++) {
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                int cu = comp[u], cv = comp[v];
                if (cu != cv) {
                    long key = ((long) cu << 32) | (cv & 0xffffffffL);
                    if (edgeSet.add(key)) {
                        cTo[cIdx] = cv;
                        cNxt[cIdx] = cHead[cu];
                        cHead[cu] = cIdx++;
                        cInDegree[cv]++;
                    }
                }
            }
        }

        // ---------- Kahn 拓扑排序，检查是否唯一 ----------
        int sources = 0;
        for (int i = 1; i <= sccCount; i++) {
            if (cInDegree[i] == 0) sources++;
        }
        if (sources != 1) {
            System.out.println("No");
            return;
        }

        int[] queue = new int[sccCount];
        int head1 = 0, tail = 0;
        for (int i = 1; i <= sccCount; i++) {
            if (cInDegree[i] == 0) queue[tail++] = i;
        }

        int processed = 0;
        while (head1 < tail) {
            int u = queue[head1++];
            processed++;
            for (int e = cHead[u]; e != -1; e = cNxt[e]) {
                int v = cTo[e];
                if (--cInDegree[v] == 0) {
                    queue[tail++] = v;
                }
            }
            // 若尚未处理完所有点，当前可用的入度为 0 的点必须恰好一个
            if (processed < sccCount && tail - head1 != 1) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }
}
