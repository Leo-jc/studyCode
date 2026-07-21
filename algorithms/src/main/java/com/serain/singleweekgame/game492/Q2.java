package com.serain.singleweekgame.game492;

public class Q2 {
    public int smallestBalancedIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] suffixProduct = new int[n + 1];
        suffixProduct[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffixProduct[i] = suffixProduct[i + 1] * nums[i+1];
        }
        prefixSum[0] = 0;
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            if (prefixSum[i] == suffixProduct[i]) {
                return i;
            }
        }
        return -1;
    }
}
