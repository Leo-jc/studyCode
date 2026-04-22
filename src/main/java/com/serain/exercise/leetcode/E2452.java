package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-22  10:41
 * @Description: TODO
 * @Version: 1.0
 */
public class E2452 {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String query : queries) {
            for (String dic : dictionary) {
                int diff = 0;
                for (int i = 0; i < query.length(); i++) {
                    if (query.charAt(i) != dic.charAt(i)) {
                        diff++;
                    }
                    if (diff > 2) {
                        break;
                    }
                }
                if (diff <= 2) {
                    res.add(query);
                    break;
                }
            }
        }
        return res;
    }
}
