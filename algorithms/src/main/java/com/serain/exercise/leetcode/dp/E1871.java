package com.serain.exercise.leetcode.dp;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.dp
 * @Author: Serain
 * @CreateTime: 2026-05-25  09:54
 * @Description: LeetCode 第 1871 题
 * @Version: 1.0
 */
public class E1871 {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if(s.charAt(n-1)=='1') return false;
        
        // dp[i] 表示位置 i 是否可达
        boolean[] dp = new boolean[n];
        dp[0] = true;
        
        // preSum[i] 表示前 i 个位置的可达数量(前缀和)
        int[] preSum = new int[n + 1];
        preSum[1] = 1; // dp[0]=true,所以preSum[1]=1
        
        for(int i = 1; i < n; i++) {
            if(s.charAt(i) == '1') {
                // 如果当前位置是障碍,不可达,前缀和不变
                preSum[i + 1] = preSum[i];
                continue;
            }
            
            // 计算可以跳到位置 i 的来源区间 [left, right]
            int left = i - maxJump;
            int right = i - minJump;
            
            // 确保区间有效
            left = Math.max(left, 0);
            right = Math.min(right, i - 1);
            
            // 如果区间无效,则位置 i 不可达
            if(left > right) {
                preSum[i + 1] = preSum[i];
                continue;
            }
            
            // 通过前缀和快速查询区间 [left, right] 内是否有可达位置
            int reachableCount = preSum[right + 1] - preSum[left];
            dp[i] = reachableCount > 0;
            
            // 更新前缀和
            preSum[i + 1] = preSum[i] + (dp[i] ? 1 : 0);
        }
        
        return dp[n - 1];
    }
}
