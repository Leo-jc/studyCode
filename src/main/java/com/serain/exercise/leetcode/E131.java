package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-11  16:50
 * @Description: TODO
 * @Version: 1.0
 */
public class E131 {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(s,0,path,ans);
        return ans;
    }

    private void dfs(String s, int i, List<String> path, List<List<String>> ans) {
        if(i==s.length()){
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int j = i; j < s.length(); j++){
            String sub = s.substring(i,j+1);
            if(isPalindrome(sub)){
                path.add(sub);
                dfs(s,j+1,path,ans);
                path.removeLast();
            }
        }
    }

    private boolean isPalindrome(String sub) {
        int i = 0, j = sub.length()-1;
        while (i < j) {
            if(sub.charAt(i)!=sub.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
