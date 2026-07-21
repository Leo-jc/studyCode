package com.serain.singleweekgame.game508;

public class Q3 {

    public long maxSubarraySum(int[] nums, int k) {
        // 分别计算乘法和除法两种操作的结果，取最大值
        long ans1 = solve(nums, k, true);  // 乘以 k
        long ans2 = solve(nums, k, false); // 除以 k
        return Math.max(ans1, ans2);
    }

    /**
     * @param multiply true 表示乘 k, false 表示除 k
     */
    private long solve(int[] nums, int k, boolean multiply) {
        int n = nums.length;
        long ans = Long.MIN_VALUE;

        // dp[0]: 尚未进入操作区间的最大子数组和
        // dp[1]: 正在操作区间内的最大子数组和
        // dp[2]: 已离开操作区间的最大子数组和
        long dp0 = 0, dp1 = 0, dp2 = 0;
        // hasStarted[1]: 是否已经进入过操作区间（dp1 有意义的条件）
        // hasStarted[2]: 是否已经离开过操作区间（dp2 有意义的条件）
        boolean has1 = false, has2 = false;

        for (int i = 0; i < n; i++) {
            long x = nums[i];
            long opVal = multiply ? x * k : customDiv(x, k);

            // state0: 尚未操作，标准 Kadane
            long newDp0 = Math.max(x, dp0 + x);
            ans = Math.max(ans, newDp0);

            // state1: 进入/继续操作区间
            long newDp1 = 0;
            boolean newHas1 = false;
            // 方式1: 从 state0 进入（sum 从 state0 转入 + 当前乘以系数的值）
            long cand1 = dp0 + opVal;
            newDp1 = cand1;
            newHas1 = true;
            // 方式2: 从当前元素开始（操作区间从 i 开始）
            long cand2 = opVal;
            if (cand2 > newDp1) {
                newDp1 = cand2;
            }
            // 方式3: 延续操作区间
            if (has1) {
                long cand3 = dp1 + opVal;
                if (cand3 > newDp1) {
                    newDp1 = cand3;
                }
            }
            ans = Math.max(ans, newDp1);

            // state2: 离开操作区间
            long newDp2 = 0;
            boolean newHas2 = false;
            // 方式1: 从 state1 退出
            if (has1) {
                newDp2 = dp1 + x;
                newHas2 = true;
            }
            // 方式2: 从当前元素开始（在操作之后）
            // 不可行：必须先有操作才能进入 state2，从 x 单独开始意味着没有操作
            // 方式3: 延续 state2
            if (has2) {
                long cand3 = dp2 + x;
                if (!newHas2 || cand3 > newDp2) {
                    newDp2 = cand3;
                    newHas2 = true;
                }
            }
            if (newHas2) {
                ans = Math.max(ans, newDp2);
            }

            dp0 = newDp0;
            dp1 = newDp1;
            has1 = newHas1;
            dp2 = newDp2;
            has2 = newHas2;
        }

        return ans;
    }

    /**
     * 自定义除法：正数向下取整，负数向上取整
     */
    private long customDiv(long x, int k) {
        if (x >= 0) {
            return x / k;
        } else {
            // 负数：向上取整，例如 -3/2 → -1
            return (long) Math.ceil((double) x / k);
        }
    }

    // 测试
    public static void main(String[] args) {
        Q3 q3 = new Q3();
        System.out.println(q3.maxSubarraySum(new int[]{1, 2, 3}, 2));       // 预期 12
        System.out.println(q3.maxSubarraySum(new int[]{-1, -2, -3}, 2));   // 预期 0
        System.out.println(q3.maxSubarraySum(new int[]{1, -10, 100}, 2));  // 验证
    }
}
