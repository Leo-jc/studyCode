package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-24  10:18
 * @Description: TODO
 * @Version: 1.0
 */
public class E2833 {
    public int furthestDistanceFromOrigin(String moves) {
        int cntR = 0;
        int cntL = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'R') {
                cntR++;
            } else if (c == 'L') {
                cntL++;
            }
        }
        return Math.abs(cntR - cntL) + moves.length() - cntR - cntL;
    }
}