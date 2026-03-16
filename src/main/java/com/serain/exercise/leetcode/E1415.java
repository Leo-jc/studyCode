package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

public class E1415 {
    private final String HAPPY_STRING = "abc";
    public String getHappyString(int n, int k) {
        List<String> list = new ArrayList<>();
        dfs(n, k, "", list);
        return list.size() < k ? "" : list.get(k - 1);
    }

    private void dfs(int n, int k, String s, List<String> list) {
        if (s.length() == n) {
            list.add(s);
            return;
        }
        for (char c : HAPPY_STRING.toCharArray()) {
            if (s.length() > 0 && s.charAt(s.length() - 1) == c) continue;
            dfs(n, k, s + c, list);
        }
    }
}
