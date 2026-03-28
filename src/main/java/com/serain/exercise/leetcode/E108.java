package com.serain.exercise.leetcode;

import com.serain.parameter.TreeNode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-28  19:11
 * @Description: TODO
 * @Version: 1.0
 */
public class E108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int i, int i1) {
        if (i > i1) {
            return null;
        }
        int mid = (i + i1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, i, mid - 1);
        root.right = helper(nums, mid + 1, i1);
        return root;
    }
}
