package com.serain.exercise.leetcode;

import java.util.Random;

/**
 * @BelongsProject: studyCode
 * @BelongsPackage: com.serain.exercise.leetcode
 * @Author: Serain
 * @CreateTime: 2026-03-29  16:20
 * @Description: TODO
 * @Version: 1.0
 */
public class E215 {
    private static final Random rand = new Random();

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int targetIndex = n - k; // 第 k 大元素在升序数组中的下标是 n - k
        int left = 0;
        int right = n - 1; // 闭区间
        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == targetIndex) {
                return nums[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1; // 搜索右侧
            } else {
                right = pivotIndex - 1; // 搜索左侧
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = nums[pivotIndex];
        pivotIndex = left;
        swap(nums, pivotIndex, left);
        left++;
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if(left>=right){
                break;
            }
            swap(nums, left, right);
            left++;
            right--;
        }
        swap(nums, pivotIndex, right);
        return right;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
