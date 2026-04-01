package com.serain.exercise.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-04-01  10:07
 * @Description: TODO
 * @Version: 1.0
 */
public class E2751 {

    static class Robot {
        int pos;
        int health;
        int dir;
        int index;
        public Robot(int pos, int health, int dir, int index) {
            this.pos = pos;
            this.health = health;
            this.dir = dir;
            this.index = index;
        }
    }
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(positions[i], healths[i], directions.charAt(i) == 'L' ? 0 : 1, i);
        }
        Arrays.sort(robots, (a, b) -> a.pos - b.pos);
        Stack<Robot> st = new Stack<>();
        for (Robot robot : robots) {
            if(robot.dir==1){
                st.push(robot);
                continue;
            }
            while (!st.isEmpty()&&healths[robot.index]>0) {
                Robot top = st.peek();
                if(healths[top.index]==healths[robot.index]){
                    healths[top.index]=0;
                    healths[robot.index]=0;
                    st.pop();
                }else if(healths[top.index]<healths[robot.index]){
                    healths[robot.index]--;
                    healths[top.index]=0;
                    st.pop();
                }else{
                    healths[top.index]--;
                    healths[robot.index]=0;
                }
            }
        }
        return Arrays.stream(healths)
                .filter(h -> h > 0)
                .boxed()
                .toList();
    }
}
