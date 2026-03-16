package com.serain.exercise;

import java.util.Arrays;
import java.util.Stack;

/**
 * Classname E32
 * Description https://leetcode.cn/problems/longest-valid-parentheses/?envType=study-plan-v2&envId=top-100-liked
 * Date 2026/3/14 20:32
 * Created by Serain
 */
public class E32 {
    public record pair(char c, int index) {}
    public int longestValidParentheses(String s) {
        int result = 0;
        if (s == null || s.isEmpty()) {
            return result;
        }
        char[] chars = s.toCharArray();
        Stack<pair> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '(' -> stack.push(new pair(chars[i], i));
                case ')' -> {
                    if (!stack.isEmpty() && stack.peek().c() == '(') {
                        stack.pop();
                        result = Math.max(result, i - (stack.isEmpty() ? -1 : stack.peek().index()));
                    } else {
                        stack.push(new pair(chars[i], i));
                    }
                }
            }
        }
        return result;
    }
}
