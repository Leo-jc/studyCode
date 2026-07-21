package com.serain.singleweekgame.game512;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.singleweekgame.game512
 * @Author: Serain
 * @CreateTime: 2026-07-26  10:33
 * @Description: TODO
 * @Version: 1.0
 */
public class Q1 {
    public int largestInteger(int n, int s) {
        int[] nums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = Math.min(s, 9);
            System.out.println(nums[i]);
            s -= nums[i];
            sum += sum*10 + nums[i];
        }
        return s==0?sum:-1;
    }
}
