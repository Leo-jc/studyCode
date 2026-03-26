package com.serain.exercise.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-26  16:04
 * @Description: TODO
 * @Version: 1.0
 */
public class E76 {
    Map<Character, Integer> tMap,sMap;
    public boolean check(){
        for(Map.Entry<Character,Integer> entry:tMap.entrySet()){
            if(sMap.getOrDefault(entry.getKey(),0)<entry.getValue()){
                return false;
            }
        }
        return true;
    }
    public String minWindow(String s, String t) {
        tMap=new HashMap<>();
        sMap=new HashMap<>();
        for(char c:t.toCharArray()){
            tMap.put(c,tMap.getOrDefault(c,0)+1);
        }
        int left=0,right=0;
        int min=Integer.MAX_VALUE;
        String ans="";
        while(right<s.length()){
            char c=s.charAt(right);
            sMap.put(c,sMap.getOrDefault(c,0)+1);
            right++;
            while(check()){
                if(right-left+1<min){
                    min=right-left+1;
                    ans=s.substring(left,right+1);
                }
                char lc=s.charAt(left);
                sMap.put(lc,sMap.getOrDefault(lc,0)-1);
            }
        }
        return ans;
    }
}
