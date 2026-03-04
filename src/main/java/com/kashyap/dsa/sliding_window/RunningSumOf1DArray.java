package com.kashyap.dsa.sliding_window;

/**
 * PATTERN   : Prefix Sum
 * DIFFICULTY: Easy
 * DATE      : Mar 1, 2026
 *
 * PROBLEM:
 * Given array nums, return running sum where
 * runningSum[i] = sum(nums[0]...nums[i])
 *
 * TRICK SENTENCE:
 * "Build prefix once O(n). Query any range in O(1).
 *  sum(i,j) = prefix[j+1] - prefix[i]"
 *
 * APPROACH:
 * In-place prefix sum. Each cell becomes the sum of
 * itself and everything before it.
 * No extra array needed — previous cell already holds
 * the running total.
 *
 * KEY INSIGHT:
 * After processing index i, arr[i] holds sum(0..i).
 * So arr[i-1] is always the running sum up to previous
 * element — no temp variable needed if reading i-1.
 *
 * TIME : O(n)  — single pass
 * SPACE: O(1)  — in-place, no extra array
 *
 * SIMILAR PROBLEMS USING THIS SAME PATTERN:
 * - Subarray Sum Equals K (prefix sum + HashMap)
 * - Product of Array Except Self
 * - Range Sum Query (2D prefix sum)
 * - Contiguous Array
 */
public class RunningSumOf1DArray {

    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) return nums;

        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];  // previous cell = running sum
        }

        return nums;
    }
}