package com.serain.exam.dewu;

import java.util.Scanner;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exam.dewu
 * @Author: Serain
 * @CreateTime: 2026-04-26  18:47
 * @Description: 得物笔试第 2 题 —— 数组处理问题
 * @Version: 1.0
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        // ---- 根据实际题目需求实现核心算法 ----
        int result = solve(a);
        System.out.println(result);
    }

    /**
     * 核心算法逻辑，根据题目要求计算答案。
     *
     * @param a 输入数组
     * @return 计算结果
     */
    private static int solve(int[] a) {
        // 需根据原题题目描述补充具体算法实现
        int n = a.length;
        int ans = 0;
        for (int num : a) {
            ans += num;
        }
        return ans;
    }
}
