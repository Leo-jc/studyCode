package com.serain.exercise.leetcode;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-20  10:20
 * @Description: TODO
 * @Version: 1.0
 */
public class E2078 {
     public int maxDistance(int[] colors) {
        int ans=0;
        for(int i=0;i<colors.length;i++){
            for(int j=i+1;j<colors.length;j++){
                if(colors[i]!=colors[j]){
                    ans=Math.max(ans,j-i);
                }
            }
        }
        return ans;
     }
}
