package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-08  10:54
 * @Description: TODO
 * @Version: 1.0
 */
public class E3653 {
    public static int Mod_NUM=1000000007;
    public int xorAfterQueries(int[] nums, int[][] queries) {
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            int k = query[2];
            int v = query[3];
            while (l <= r) {
                nums[l] = (nums[l] * v) % Mod_NUM;
                l += k;
            }
        }
        int ans=0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
