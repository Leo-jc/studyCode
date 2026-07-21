package com.serain.exercise.leetcode.string;

/**
 * 3612. 用特殊操作处理字符串 I
 * 给定字符串 s，只包含小写英文字母和特殊字符 *、#、%
 * 从左到右处理 s 中的字符，构造新字符串 result：
 *   - 小写英文字母：添加到 result
 *   - '*'：删除 result 的最后一个字符（如果存在）
 *   - '#'：复制当前 result 并追加到自身后面
 *   - '%'：反转当前的 result
 */
public class E3621 {

    public String processStr(String s) {
        StringBuilder result = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                result.append(ch);
            } else if (ch == '*') {
                // 删除最后一个字符
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else if (ch == '#') {
                // 复制当前 result 并追加到自身
                String current = result.toString();
                result.append(current);
            } else if (ch == '%') {
                // 反转当前 result
                result.reverse();
            }
        }

        return result.toString();
    }
}
