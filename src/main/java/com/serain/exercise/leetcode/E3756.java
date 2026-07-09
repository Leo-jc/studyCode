package com.serain.exercise.leetcode;

public class E3756 {

    private static final int MOD = 1000000007;
    private static final int[] pow10 = new int[100001];
    static {
        pow10[0] = 1;
        for (int i = 1; i < 100001; i++) {
            pow10[i] = (int) (pow10[i - 1] * 10L % MOD);
        }
    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long[] sum = new long[n + 1];
        long[][] multiply = new long[n + 1][2];
        for (int i = 1; i <= n; i++) {
            int num = s.charAt(i - 1) - '0';
            sum[i] = sum[i - 1] + num;
            multiply[i][0] = multiply[i - 1][0];
            multiply[i][1] = multiply[i - 1][1];
            if (num > 0) {
                multiply[i][0] = (multiply[i][0] * 10 + num) % MOD;
                multiply[i][1]++;
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int len = (int) (multiply[r + 1][1] - multiply[l][1]);
            long x = (multiply[r + 1][0] - multiply[l][0] * pow10[len] % MOD + MOD) % MOD;
            ans[i] = (int) (x * ((sum[r + 1] - sum[l]) % MOD) % MOD);
        }
        return ans;
    }
}
