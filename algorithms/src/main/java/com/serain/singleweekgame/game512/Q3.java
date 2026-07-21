package com.serain.singleweekgame.game512;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.singleweekgame.game512
 * @Author: Serain
 * @CreateTime: 2026-07-26  10:41
 * @Description: 有效序列计数 - 组合数学解法
 * @Version: 1.0
 */
public class Q3 {
    private static final int MOD = 1000000007;
    private static long[] fact, invFact;
    private static int precomputedN = -1;

    public int countValidSequences(int n, int k) {
        int ravolqedin = n;
        if (k > n) return 0;
        precompute(ravolqedin);

        long total = comb(n - 1, k - 1);
        long allOdd = 0;
        if (((n + k) & 1) == 0) {
            allOdd = comb((n + k) / 2 - 1, k - 1);
        }
        long result = (total - allOdd) % MOD;
        if (result < 0) result += MOD;
        return (int) result;
    }

    private static void precompute(int n) {
        if (precomputedN >= n) return;
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
        precomputedN = n;
    }

    private static long comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

    private static long modPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b=b>>1;
        }
        return res;
    }
}
