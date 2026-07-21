package com.serain.exercise.leetcode.special;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.special
 * @Author: Serain
 * @CreateTime: 2026-05-14  15:04
 * @Description: 姓名与索引映射
 * @Version: 1.0
 */
public class NameAndIndex {
    public String name;
    public int count;
    public int index;

    public NameAndIndex(int index) {
        this.index = index;
    }

    public NameAndIndex(String name, int index, int count) {
        this.name = name;
        this.index = index;
        this.count = count;
    }
}
