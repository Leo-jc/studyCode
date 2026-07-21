package com.serain.singleweekgame.game492;

import java.util.HashMap;
import java.util.Map;

public class Q3 {
    public int minOperations(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }
        if (n == 2 && s.charAt(0) > s.charAt(1)){
            return -1;
        }
        char[] chars = s.toCharArray();
        char minChar = chars[0];
        char maxChar = chars[0];
        boolean flag = true;
        Map<Character,Integer> map = new HashMap<>();
        map.put(chars[0],1);
        for (int i = 1; i < n; i++) {
            map.put(chars[i],map.getOrDefault(chars[i],0)+1);
            if(chars[i] < chars[i-1]){
                flag = false;
            }
            char c = chars[i];
            if (c < minChar) {
                minChar = c;
            }
            if (c > maxChar) {
                maxChar = c;
            }
        }
        if(flag) return 0;
        if(chars[0] == minChar || chars[n-1] == maxChar){
            return 1;
        }
        if(chars[0] == maxChar && chars[n-1] == minChar && map.get(minChar) == 1 && map.get(maxChar) == 1){
            return 3;
        }
        return 2;
    }
}
