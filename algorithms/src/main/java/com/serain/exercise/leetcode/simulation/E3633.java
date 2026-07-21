package com.serain.exercise.leetcode.simulation;


public class E3633 {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int landWater = solve(landStartTime, landDuration, waterStartTime, waterDuration);
        int waterLand = solve(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(landWater, waterLand);
    }

    private int solve(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinish = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            minFinish = Math.min(minFinish, landStartTime[i] + landDuration[i]);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < waterStartTime.length; i++) {
            res = Math.min(res, Math.max(waterStartTime[i], minFinish) + waterDuration[i]);
        }
        return res;
    }
}
