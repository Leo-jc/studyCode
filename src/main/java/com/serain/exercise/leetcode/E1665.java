package com.serain.exercise.leetcode;

import java.util.Arrays;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-05-12  10:12
 * @Description: TODO
 * @Version: 1.0
 */
public class E1665 {
    public int minimumEffort(int[][] tasks) {
        int n = tasks.length;
        Task[] tasksArray = new Task[n];
        for (int i = 0; i < n; i++) {
            tasksArray[i] = new Task(tasks[i][0], tasks[i][1]);
        }
        Arrays.sort(tasksArray, (a, b) -> (b.minimum-b.actual) - (a.minimum-a.actual));
        int ans = 0;
        int curEnergy = 0;
        for (int i = 0; i < n; i++) {
            int cur = tasksArray[i].actual;
            int min = tasksArray[i].minimum;
            System.out.println(cur+" "+min);
            if(min>curEnergy){
                ans+=min-curEnergy;
                curEnergy = min;
            }
            curEnergy = curEnergy-cur;
        }
        return ans;
    }
}

class Task{
    int actual;
    int minimum;
    public Task(int actual, int minimum){
        this.actual = actual;
        this.minimum = minimum;
    }
}