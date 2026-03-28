package com.serain.exercise.leetcode;

import com.serain.parameter.TreeNode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  19:14
 * @Description: TODO
 * @Version: 1.0
 */
public class E98 {
    public int pre = Integer.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST(root.left);
        if (!left || root.val <= pre) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }
}
