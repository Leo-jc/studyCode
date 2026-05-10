package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-11  09:39
 * @Description: TODO
 * @Version: 1.0
 */
public class E2533 {
    public int[] separateDigits(int[] nums) {
        List<Integer> list=new ArrayList<>();
        for (int num : nums) {
            int temp = num;
            List<Integer> tempList = new ArrayList<>();
            while (temp > 0) {
                tempList.add(temp % 10);
                temp /= 10;
            }
            for (int j = tempList.size() - 1; j >= 0; j--) {
                list.add(tempList.get(j));
            }
        }
        int[] ans=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i]=list.get(i);
        }
        return ans;
    }
}
