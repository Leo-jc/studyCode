package com.serain.exercise.leetcode;

import java.lang.reflect.Proxy;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-03  10:13
 * @Description: LeetCode 第 796 题
 * @Version: 1.0
 */
public class E796 {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
