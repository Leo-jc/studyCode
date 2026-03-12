package com.serain.exercise;

public class E1888 {
    public int minFlips(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        
        char[] s = S.toCharArray();
        int n = s.length;
        int minFlips = n;
        int mismatchCount = 0;
        
        // 滑动窗口遍历长度为 2n-1 的扩展字符串
        for (int i = 0; i < n * 2 - 1; i++) {
            int idx = i % n;
            // 检查当前字符是否与位置 i 的期望值不匹配
            // 目标模式：位置 i 应该是字符 ('0' + i % 2)
            if (s[idx] != (char)('0' + i % 2)) {
                mismatchCount++;
            }
            
            // 窗口左边界
            int left = i - n + 1;
            if (left < 0) {
                continue;
            }
            
            // 更新最小翻转次数：可以选择翻转为全 0 开头或全 1 开头
            minFlips = Math.min(minFlips, Math.min(mismatchCount, n - mismatchCount));
            
            // 移除窗口左边界的字符
            if (s[left] != (char)('0' + left % 2)) {
                mismatchCount--;
            }
        }
        return minFlips;
    }
}
