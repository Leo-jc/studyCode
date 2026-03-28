package com.serain.exercise.leetcode;

import com.serain.parameter.TreeNode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  19:04
 * @Description: TODO
 * @Version: 1.0
 */
public class E543 {
    public int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDiameter;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int left = dfs(root.left)+1;
        int right = dfs(root.right)+1;
        maxDiameter = Math.max(maxDiameter, left+right);
        return Math.max(left, right);
    }
}
