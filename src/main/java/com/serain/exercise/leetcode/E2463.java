package com.serain.exercise.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-14  13:02
 * @Description: TODO
 * @Version: 1.0
 */
public class E2463 {
    public long minimumTotalDistance(List<Integer> robotList, int[][] factory){
        int[] robot=robotList.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(robot);
        Arrays.sort(factory, (a,b)->a[0]-b[0]);
        int n=robot.length;
        int m=factory.length;
        long[][] dp=new long[n+1][m+1];
        Arrays.fill(dp[0], Long.MAX_VALUE/2);
        dp[0][0]=0;
        for (int i = 1; i <= n; i++) {
            int position = factory[i-1][0];
            int limit = factory[i-1][1];
            for (int j = 1; j <= m; j++) {
                dp[i][j]=dp[i-1][j];
                int disSum=0;
               for(int k=j;k>=Math.max(1,j-limit+1);k--){
                   disSum+=Math.abs(position-factory[k-1][0]);
                   dp[i][j]=Math.min(dp[i][j],dp[i-1][k-1]+disSum);
               }
            }
        }
        return dp[n][m];
    }
}
