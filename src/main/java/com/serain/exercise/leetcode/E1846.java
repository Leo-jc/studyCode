package com.serain.exercise.leetcode;

import java.util.Arrays;

public class E1846 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Math.min(arr[i - 1] + 1, arr[i]);
        }
        return arr[arr.length - 1];
    }
}
