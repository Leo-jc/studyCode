package com.serain.exercise.leetcode;

import java.util.Arrays;

public class E2126 {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long curMass = mass;
        for (int asteroid : asteroids) {
            if (curMass < asteroid) {
                return false;
            }
            curMass += asteroid;
        }
        return true;
    }
}
