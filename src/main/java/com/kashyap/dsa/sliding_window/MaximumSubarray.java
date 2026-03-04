package com.kashyap.dsa.sliding_window;

/**
 * PATTERN   : Kadane's Algorithm (Dynamic Programming / Greedy hybrid)
 * DIFFICULTY: Medium
 * DATE      : Mar 1, 2026
 *
 * PROBLEM:
 * Find the contiguous subarray with the largest sum and return its sum.
 *
 * TRICK SENTENCE:
 * "At every element — is the past helping or hurting?
 *  Keep it if helping. Ditch it if hurting. Track the best ever seen."
 *
 * THE MENTAL MOVIE:
 * Road trip across cities. Each city adds/subtracts happiness.
 * At every city: "Continue this journey OR start fresh here?"
 * Continue if past is still adding value. Restart if past is a liability.
 *
 * cities  = [-2,  1, -3,  4, -1,  2,  1, -5,  4]
 * current =  -2   1  -2   4   3   5   6   1   5
 * max     =  -2   1   1   4   4   5   6   6   6  → Answer: 6
 *
 * THE ONE-LINE INSIGHT:
 * currSum = Math.max(nums[i], currSum + nums[i])
 *                    ↑ start fresh    ↑ continue journey
 * This single line makes the entire decision automatically.
 *
 * WHY nums[0] NOT 0:
 * Array can be all negative e.g. [-3,-1,-2].
 * Answer is -1 (least negative), NOT 0.
 * Starting with 0 would return wrong answer.
 *
 * WHY THIS IS ALSO DYNAMIC PROGRAMMING:
 * currSum[i] = best subarray sum ENDING at index i
 * It depends only on currSum[i-1] — classic DP recurrence.
 * We just optimized space from O(n) array to O(1) variable.
 *
 * TIME : O(n)  — single pass
 * SPACE: O(1)  — two variables only
 *
 * FOLLOW-UP VARIANTS (same pattern, slightly modified):
 * - Maximum Product Subarray (track both max AND min — negatives flip)
 * - Maximum Sum Circular Subarray (total - minimum subarray)
 * - Longest Subarray with Sum <= K (sliding window variant)
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxSum = nums[0];   // best sum seen ANYWHERE in array
        int currSum = nums[0];  // best sum ENDING at current position

        for (int i = 1; i < nums.length; i++) {
            // Decision: is the past helping or hurting?
            currSum = Math.max(nums[i], currSum + nums[i]);

            // Is this the best we've ever seen?
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }
}