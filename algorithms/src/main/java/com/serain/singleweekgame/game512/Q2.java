package com.serain.singleweekgame.game512;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.singleweekgame.game512
 * @Author: Serain
 * @CreateTime: 2026-07-26  10:38
 * @Description: TODO
 * @Version: 1.0
 */
public class Q2 {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < series1.length && j < series2.length) {
            if (series1[i][0] < series2[j][0]) {
                res.add(Arrays.asList(series1[i][0], series1[i][1]+series2[j][1]));
                i++;
            } else if (series1[i][0] > series2[j][0]) {
                res.add(Arrays.asList(series2[j][0], series2[j][1]+series1[i][1]));
                j++;
            } else {
                res.add(Arrays.asList(series1[i][0], series1[i][1] + series2[j][1]));
                i++;
                j++;
            }
        }
        while (i < series1.length) {
            res.add(Arrays.asList(series1[i][0], series1[i][1]));
            i++;
        }
        while (j < series2.length) {
            res.add(Arrays.asList(series2[j][0], series2[j][1]));
            j++;
        }
        return res;
    }
}
