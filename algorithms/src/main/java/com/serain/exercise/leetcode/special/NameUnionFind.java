package com.serain.exercise.leetcode.special;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.special
 * @Author: Serain
 * @CreateTime: 2026-05-14  15:04
 * @Description: 姓名并查集
 * @Version: 1.0
 */
public class NameUnionFind {
    public NameAndIndex[] parent;

    public NameUnionFind(int n) {
        parent = new NameAndIndex[n];
        for (int i = 0; i < n; i++) {
            parent[i] = new NameAndIndex(i);
        }
    }

    public int find(int index) {
        if (parent[index].index == index) {
            return index;
        }
        return find(parent[index].index);
    }

    public void union(int nameAndIndex1, int nameAndIndex2) {
        NameAndIndex parent1 = parent[find(nameAndIndex1)];
        NameAndIndex parent2 = parent[find(nameAndIndex2)];
        if (parent1.index == parent2.index) {
            return;
        }
        if (parent1.name.compareTo(parent2.name) < 0) {
            parent2.index = parent1.index;
            parent1.count += parent2.count;
        } else {
            parent1.index = parent2.index;
            parent2.count += parent1.count;
        }
    }
}
