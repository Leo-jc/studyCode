package com.serain.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

public class E1980 {
    public String findDifferentBinaryString(String[] nums) {
        int n= nums.length;
        Map<Integer, Boolean> map = new HashMap<>();
        for (String num : nums) {
            Integer key = Integer.parseInt(num, 2);
            System.out.println(key);
            map.put(key, true);
        }
        String res = "";
        for(int i=0; i<Math.pow(2, n); i++){
            if(!map.containsKey(i)){
                String temp = Integer.toBinaryString(i);
                res = String.format("%" + n + "s", temp).replace(' ', '0');
                break;
            }
        }
        return res;
    }
}
