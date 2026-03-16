package com.serain.doubleweekgame.game178;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Serain
 * @date 2026-03-14 00:00:00
 */
public class Q1 {
    public int firstUniqueEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (num % 2 == 0 && map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }
    public static void main(String[] args) {

    }
}