package com.serain.exercise.leetcode;

/**
 * 计算区间内所有数字的"波动度"之和。
 * 波动度定义：数字中连续三位，若中间位是局部峰值或谷值（即同时大于或同时小于相邻两位），则计数+1。
 */
public class E3751 {

    /**
     * 遍历 [num1, num2]，累加每个数字的波动度。
     *
     * @param num1 区间下界（含）
     * @param num2 区间上界（含）
     * @return 区间内所有数字波动度的总和
     */
    public int totalWaviness(int num1, int num2) {
        int total = 0;
        for (int i = num1; i <= num2; i++) {
            total += countWaviness(i);
        }
        return total;
    }

    /**
     * 统计单个数字的波动度。
     * 滑动窗口依次检查每连续三位数字，判断中间位是否为局部极值。
     *
     * @param num 待计算的数字
     * @return 波动度值
     */
    private int countWaviness(int num) {
        // 优化1：一次转换为char数组，后续用 char-'0' 取数值，避免 substring+parseInt 的开销
        char[] digits = String.valueOf(num).toCharArray();
        int n = digits.length;

        // 优化2：少于3位时无法形成连续三位子串，提前返回，避免无意义循环
        if (n < 3) {
            return 0;
        }

        int count = 0;
        // 修复Bug：j应从0开始（原代码 j=1 遗漏了首个窗口，导致3位数结果全为0）
        for (int i = 0; i <= n - 3; i++) {
            int left = digits[i] - '0';
            int mid = digits[i + 1] - '0';
            int right = digits[i + 2] - '0';
            if ((mid > left && mid > right) || (mid < left && mid < right)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
