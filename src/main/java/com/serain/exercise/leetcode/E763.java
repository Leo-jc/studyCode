package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  15:37
 * @Description: TODO
 * @Version: 1.0
 */
public class E763 {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c='a'; c<='z'; c++){
            for (int i=s.length()-1;i>=0;i--){
                if(s.charAt(i)==c){
                    map.put(c,i);
                    break;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        while(left<s.length()){
            int cur = left;
            int right = map.get(s.charAt(left));
            while(left<right){
                char c = s.charAt(left);
                int index = map.get(c);
                if(index>right){
                    right = index;
                }
                left++;
            }
            ans.add(right-cur+1);
        }
        return ans;
    }
}
