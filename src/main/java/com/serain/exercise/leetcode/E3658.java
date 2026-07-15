package com.serain.exercise.leetcode;

public class E3658 {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd=0,sumEven=0;
        for(int i=1;i<=n;i++){
            sumEven+=2*i;
            sumOdd+=2*(i-1)+1;
        }
        return gcd(sumOdd,sumEven);
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}