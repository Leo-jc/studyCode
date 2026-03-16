package com.serain.doubleweekgame.game178;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Serain
 * @date 2026-03-14 00:00:00
 */
public class Q3 {

    public int minCost(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int[] nums = new int[2*n];
        System.arraycopy(nums1, 0, nums, 0, n);
        System.arraycopy(nums2, 0, nums, n, n);
        Arrays.sort(nums);
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<2*n;i+=2){
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
            if(nums[i]!=nums[i+1]){
                return -1;
            }
        }
        int hasNum = 0;
        for (int j : nums1) {
            if (map.containsKey(j)&&map.get(j) > 0) {
                map.put(j, map.get(j) - 1);
                hasNum++;
            }
        }
        return n-hasNum;
    }
    public static void main(String[] args) {
        
    }
}