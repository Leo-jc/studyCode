package com.serain.exercise.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.stack
 * @Author: Serain
 * @CreateTime: 2026-03-29  16:56
 * @Description: LeetCode 第 20 题 —— 有效的括号
 * @Version: 1.0
 */
public class E20 {
    /**
     * 判断字符串中的括号是否有效配对。
     * 使用栈匹配 '(' ')', '{' '}', '[' ']' 三对括号。
     *
     * @param s 只包含 '(', ')', '{', '}', '[', ']' 的字符串
     * @return 括号是否全部正确配对
     */
    public boolean isValid(String s) {
        // 奇数长度不可能完全匹配
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // 左括号压栈
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 右括号必须与栈顶左括号匹配
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(')
                    || (c == '}' && top != '{')
                    || (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
