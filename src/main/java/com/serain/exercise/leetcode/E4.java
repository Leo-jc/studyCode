package com.serain.exercise.leetcode;

import java.util.ArrayList;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise
 * @Author: Serain
 * @CreateTime: 2026-03-14  20:51
 * @Description: TODO
 * @Version: 1.0
 */
public class E4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int mid = (m + n) / 2;
        if(m==0){
            return n%2==1 ? nums2[mid]:(nums2[mid-1]+nums2[mid])/2.0;
        }else if(n==0){
            return m%2==1 ? nums1[mid]:(nums1[mid-1]+nums1[mid])/2.0;
        }
        int pos1=-1;
        int pos2=-1;
        mid++;
        ArrayList<Integer> list = new ArrayList<>();
        while (pos1 + pos2 + 2 < mid) {
            if (pos1 < m - 1 && pos2 < n - 1) {
                if (nums1[pos1 + 1] < nums2[pos2 + 1]) {
                    pos1++;
                    list.add(nums1[pos1]);
                } else {
                    pos2++;
                    list.add(nums2[pos2]);
                }
            } else if (pos1 < m - 1) {
                pos1++;
                list.add(nums1[pos1]);
            } else {
                pos2++;
                list.add(nums2[pos2]);
            }
        }
        if ((m + n) % 2 == 1) {
            return list.get(list.size() - 1);
        } else {
            return (list.get(list.size() - 1) + list.get(list.size() - 2)) / 2.0;
        }
    }
}
