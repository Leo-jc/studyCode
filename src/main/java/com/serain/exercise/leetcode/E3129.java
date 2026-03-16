package com.serain.exercise.leetcode;

public class E3129 {
    private static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] f = new int[zero + 1][one + 1][2];
        
        // 初始化：只有 0 的情况（连续不超过 limit 个）
        for (int i = 1; i <= Math.min(limit, zero); i++) {
            f[i][0][0] = 1;
        }
        
        // 初始化：只有 1 的情况（连续不超过 limit 个）
        for (int j = 1; j <= Math.min(limit, one); j++) {
            f[0][j][1] = 1;
        }
        
        // 动态规划填表
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // 计算以 0 结尾的稳定数组数量
                long sumPrev0 = (long) f[i - 1][j][0] + f[i - 1][j][1];
                if (i > limit) {
                    // 减去不合法的情况（超过 limit 个连续 0）
                    sumPrev0 -= f[i - limit - 1][j][1];
                }
                f[i][j][0] = (int) ((sumPrev0 % MOD + MOD) % MOD);
                
                // 计算以 1 结尾的稳定数组数量
                long sumPrev1 = (long) f[i][j - 1][0] + f[i][j - 1][1];
                if (j > limit) {
                    // 减去不合法的情况（超过 limit 个连续 1）
                    sumPrev1 -= f[i][j - limit - 1][0];
                }
                f[i][j][1] = (int) ((sumPrev1 % MOD + MOD) % MOD);
            }
        }
        
        return (f[zero][one][0] + f[zero][one][1]) % MOD;
    }
}
