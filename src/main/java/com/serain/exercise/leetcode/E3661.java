package com.serain.exercise.leetcode;

import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-03  20:40
 * @Description: TODO
 * @Version: 1.0
 */
public class E3661 {
        public int maxWalls(int[] robots, int[] distance, int[] walls) {
            if (robots == null || distance == null || walls == null || 
                robots.length != distance.length || robots.length == 0) {
                return 0;
            }
            
            int n = robots.length;
            int[][] robotInfo = new int[n + 2][2];
            
            for (int i = 0; i < n; i++) {
                robotInfo[i + 1][0] = robots[i];
                robotInfo[i + 1][1] = distance[i];
            }
            
            robotInfo[0][0] = Integer.MIN_VALUE;
            robotInfo[0][1] = 0;
            robotInfo[n + 1][0] = Integer.MAX_VALUE;
            robotInfo[n + 1][1] = 0;
            
            Arrays.sort(robotInfo, (p, q) -> Integer.compare(p[0], q[0]));
            Arrays.sort(walls);

            int[][] dp = new int[n + 2][2];
            
            for (int i = 1; i <= n; i++) {
                int x = robotInfo[i][0];
                int d = robotInfo[i][1];

                int leftX = Math.max(x - d, robotInfo[i - 1][0] + 1);
                int left = lowerBound(walls, leftX);
                int cur = lowerBound(walls, x + 1);
                int leftRes = dp[i - 1][0] + cur - left;

                cur = lowerBound(walls, x);
                
                for (int j = 0; j < 2; j++) {
                    int nextRobotX = robotInfo[i + 1][0];
                    int nextRobotDist = robotInfo[i + 1][1];
                    
                    if (j == 0) {
                        nextRobotX -= nextRobotDist;
                    }
                    
                    int rightX = Math.min(x + d, nextRobotX - 1);
                    int right = lowerBound(walls, rightX + 1);
                    
                    dp[i][j] = Math.max(leftRes, dp[i - 1][1] + right - cur);
                }
            }
            
            return dp[n][1];
        }

        private int lowerBound(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int left = 0;
            int right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return right;
        }
}
