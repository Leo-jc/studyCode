package com.serain.exercise.leetcode.graph;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.graph
 * @Author: Serain
 * @CreateTime: 2026-05-17  13:22
 * @Description: LeetCode 第 1306 题
 * @Version: 1.0
 */
public class E1306 {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return canReach(arr, start, visited);
    }
    private boolean canReach(int[] arr, int start, boolean[] visited) {
        if(start < 0 || start >= arr.length || visited[start]) return false;
        if(arr[start] == 0) return true;
        visited[start] = true;
        return canReach(arr, start + arr[start], visited) || canReach(arr, start - arr[start], visited);
    }
}
