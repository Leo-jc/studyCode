package com.serain.exercise.leetcode;

public class E3499 {
    public int maxActiveSectionsAfterTrade(String S) {
        char[] s = S.toCharArray();
        int total1 = 0;
        int mx = 0;
        int pre0 = Integer.MIN_VALUE;
        int cnt = 0;
        for (int i = 0; i < s.length; i++) {
            cnt++;
            if (i == s.length - 1 || s[i] != s[i + 1]) { // i 是这一段的末尾
                if (s[i] == '1') {
                    total1 += cnt;
                } else {
                    mx = Math.max(mx, pre0 + cnt);
                    pre0 = cnt;
                }
                cnt = 0;
            }
        }
        return total1 + mx;
    }
}
