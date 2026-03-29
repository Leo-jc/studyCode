package com.serain.exercise.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  14:39
 * @Description: TODO
 * @Version: 1.0
 */
public class E139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Map<String, Boolean> wordDictMap = new HashMap<>();
        for(String word : wordDict){
            wordDictMap.put(word, true);
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 0; i < s.length(); i++){
            for(int j=0;j<i;j++){
                if(dp[j] && wordDictMap.containsKey(s.substring(j+1, i+1))){

                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n-1];
    }
}
