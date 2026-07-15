package com.serain.exercise.leetcode;

public class E3754 {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        for (char c : Integer.toString(n).toCharArray()) {
            int digit = c - '0';
            if (digit != 0) {
                x = x * 10 + digit;
                sum += digit;
            }
        }
        return x * sum;
    }
}
