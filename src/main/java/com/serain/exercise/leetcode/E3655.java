package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-09  14:43
 * @Description: TODO
 * @Version: 1.0
 */
public class E3655 {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int ans=0;
        for (int num : nums) {
            ans ^= num;
        }
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];
            if((r-l)/k%2==0) ans^=v;
        }

        return ans;
    }
}
