package com.serain.exercise.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class E1331 {
        public int[] arrayRankTransform(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : arr) {
            set.add(num);
        }
        Map<Integer, Integer> rank = new HashMap<>();
        int pos = 1;
        for (int num : set) {
            rank.put(num, pos++);
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = rank.get(arr[i]);
        }
        return ans;
    }
}