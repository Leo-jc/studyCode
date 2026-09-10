package com.serain.exercise.leetcode.tree;

import com.serain.parameter.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode.tree
 * @Author: Serain
 * @CreateTime: 2026-09-10  10:20
 * @Description: TODO
 * @Version: 1.0
 */
public class E2265 {
    public static List<Integer> list=new ArrayList<>();
    public int averageOfSubtree(TreeNode root) {
        getAns(root,0,1);
        return list.size();
    }

    private int[] getAns(TreeNode root, int val, int num) {
        int[] ans = new int[2];
        if(root==null){
            return ans;
        }
        int[] left = getAns(root.left,val,num);
        int[] right = getAns(root.right,val,num);
        int average=(root.val+left[0]+right[0])/(1+left[1]+right[1]);
        if(average==root.val){
            list.add(root.val);
        }
        ans[0]=root.val+left[0]+right[0];
        ans[1]=1+left[1]+right[1];
        return ans;
    }
}
