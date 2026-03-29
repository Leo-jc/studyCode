package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  14:16
 * @Description: TODO
 * @Version: 1.0
 */
public class E118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        res.add(list);
        while (numRows > 1) {
            numRows--;
            list = new ArrayList<>();
            list.add(1);
            for(int i=0;i<res.getLast().size()-1;i++){
                list.add(res.getLast().get(i)+res.getLast().get(i+1));
            }
            list.add(1);
            res.add(list);
        }
        return res;
    }
}
