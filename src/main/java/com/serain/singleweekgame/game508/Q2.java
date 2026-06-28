package com.serain.singleweekgame.game508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2 {
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        if (occupiedIntervals == null || occupiedIntervals.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(occupiedIntervals, (a, b) -> a[0] - b[0]);

        List<List<Integer>> result = new ArrayList<>();
        int curL = occupiedIntervals[0][0];
        int curR = occupiedIntervals[0][1];

        // 合并并过滤：每次合并区间关闭时，检查其与自由区间的重叠关系
        for (int i = 1; i < occupiedIntervals.length; i++) {
            int[] interval = occupiedIntervals[i];
            if (interval[0] > curR) {
                addTrimmedInterval(result, curL, curR, freeStart, freeEnd);
                curL = interval[0];
            }
            curR = Math.max(curR, interval[1]);
        }
        addTrimmedInterval(result, curL, curR, freeStart, freeEnd);

       for(int i=1;i<result.size();i++){
          if(result.get(i-1).get(1)==result.get(i).get(0)-1){
              result.get(i-1).set(1,result.get(i).get(1));
              result.remove(i);
              i--;
          }
       }

        return result;
    }

    /**
     * 检查合并后的区间 [L, R] 与自由区间 [freeStart, freeEnd] 的重叠关系，
     * 将非重叠部分加入结果集。
     */
    private void addTrimmedInterval(List<List<Integer>> result, int L, int R, int freeStart, int freeEnd) {
        if (R < freeStart || L > freeEnd) {
            // 无重叠，完整保留
            result.add(Arrays.asList(L, R));
        } else if (L < freeStart && R > freeEnd) {
            // 完全跨越自由区间，左右各保留一段
            result.add(Arrays.asList(L, freeStart - 1));
            result.add(Arrays.asList(freeEnd + 1, R));
        } else if (L < freeStart && R <= freeEnd) {
            // 左侧重叠
            result.add(Arrays.asList(L, freeStart - 1));
        } else if (L >= freeStart && R > freeEnd) {
            // 右侧重叠
            result.add(Arrays.asList(freeEnd + 1, R));
        }
        // L >= freeStart && R <= freeEnd：完全被自由区间覆盖，丢弃
    }
}
