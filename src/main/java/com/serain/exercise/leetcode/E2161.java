package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

public class E2161 {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int count = 0;
        for (int num : nums) {
            if (num < pivot) {
                left.add(num);
            } else if (num > pivot) {
                right.add(num);
            } else {
                count++;
            }
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < left.size(); i++) {
            ans[i] = left.get(i);
        }
        for (int i = 0; i < count; i++) {
            ans[left.size() + i] = pivot;
        }
        for (int i = 0; i < right.size(); i++) {
            ans[left.size() + count + i] = right.get(i);
        }
        return ans;
    }
}
