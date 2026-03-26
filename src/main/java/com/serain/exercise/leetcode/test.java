package com.serain.exercise.leetcode;

import java.util.*;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-25  22:10
 * @Description: TODO
 * @Version: 1.0
 */
public class test {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            System.out.println(chars);
            Arrays.sort(chars);
            System.out.println(chars);
            List< String> list = map.getOrDefault(Arrays.toString(chars),new ArrayList<>());
            list.add(str);
            map.put(Arrays.toString(chars),list);
        }
        return new ArrayList<>(map.values());
    }
}
