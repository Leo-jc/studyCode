package com.serain.exercise.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-24  13:27
 * @Description: LeetCode 第 1340 题
 * @Version: 1.0
 */
public class E1340 {
    /**
     * 计算最多可以访问的下标数量
     * @param arr 整数数组
     * @param d 最大跳跃距离
     * @return 最多可以访问的下标数量
     */
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] memo = new int[n]; // 记忆化数组，memo[i]表示从位置i开始最多能访问的下标数
        Arrays.fill(memo, -1); // 初始化为-1表示未计算
            
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dfs(arr, i, d, memo));
        }
        return result;
    }
    
    /**
     * 深度优先搜索 + 记忆化
     * @param arr 数组
     * @param pos 当前位置
     * @param d 最大跳跃距离
     * @param memo 记忆化数组
     * @return 从当前位置开始最多能访问的下标数
     */
    private int dfs(int[] arr, int pos, int d, int[] memo) {
        // 如果已经计算过，直接返回结果
        if (memo[pos] != -1) {
            return memo[pos];
        }
            
        int n = arr.length;
        int maxVisits = 1; // 至少可以访问当前位置
            
        // 向左跳跃
        for (int i = pos - 1; i >= Math.max(0, pos - d); i--) {
            // 如果遇到大于等于当前高度的位置，不能继续跳
            if (arr[i] >= arr[pos]) {
                break;
            }
            // 递归计算从位置i开始能访问的最大下标数
            maxVisits = Math.max(maxVisits, dfs(arr, i, d, memo) + 1);
        }
            
        // 向右跳跃
        for (int i = pos + 1; i <= Math.min(n - 1, pos + d); i++) {
            // 如果遇到大于等于当前高度的位置，不能继续跳
            if (arr[i] >= arr[pos]) {
                break;
            }
            // 递归计算从位置i开始能访问的最大下标数
            maxVisits = Math.max(maxVisits, dfs(arr, i, d, memo) + 1);
        }
            
        // 记录结果并返回
        memo[pos] = maxVisits;
        return maxVisits;
    }
}
