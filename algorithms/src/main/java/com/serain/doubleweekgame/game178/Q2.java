package com.serain.doubleweekgame.game178;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Serain
 * @date 2026-03-14 00:00:00
 */
public class Q2 {


    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public long gcdSum(int[] nums) {
        int maxNum = 0;
        int[] gcds = new int[nums.length];
        for (int i=0; i < nums.length; i++) {
            maxNum = Math.max(maxNum, nums[i]);
            gcds[i]=gcd(nums[i], maxNum);
        }
        Arrays.sort(gcds);
        long ans = 0;
        int left=0, right=gcds.length-1;
        while (left < right) {
            ans += gcd(gcds[left], gcds[right]);
        }
        return ans;
    }
    public static void main(String[] args) {
        
    }
}