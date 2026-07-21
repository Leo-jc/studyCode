package com.serain.exercise.niuke;

import java.util.Scanner;

/**
 * 牛客网 - 求乘法逆元 a⁻¹ mod m
 * <p>
 * 由于 m 不一定是质数，使用扩展欧几里得算法求解。
 * <p>
 * 扩展欧几里得算法：ax + my = gcd(a, m)
 * 当 gcd(a, m) = 1 时，a⁻¹ ≡ x (mod m)
 */
public class BISHI165 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            long a = in.nextLong();
            long m = in.nextLong();
            System.out.println(modInverse(a, m));
        }
    }

    /**
     * 使用扩展欧几里得算法求 a 在模 m 下的乘法逆元
     * 返回满足 ax ≡ 1 (mod m) 的 x
     * 使用 long 防止溢出
     */
    private static long modInverse(long a, long m) {
        long[] res = extendedGcd(a, m);
        long x = res[0];
        long g = res[2];
        // 乘法逆元存在的条件是 gcd(a, m) = 1
        if (g != 1) {
            return -1; // 逆元不存在
        }
        // 将 x 调整为正数
        long result = (x % m + m) % m;
        return result;
    }

    /**
     * 扩展欧几里得算法
     * 返回数组 [x, y, gcd]，满足 ax + by = gcd(a, b)
     */
    private static long[] extendedGcd(long a, long b) {
        if (b == 0) {
            return new long[]{1, 0, a};
        }
        long[] res = extendedGcd(b, a % b);
        long x1 = res[0];
        long y1 = res[1];
        long g = res[2];
        // x = y1, y = x1 - (a/b) * y1
        long x = y1;
        long y = x1 - (a / b) * y1;
        return new long[]{x, y, g};
    }
}
