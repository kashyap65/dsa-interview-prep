package com.kashyap.dsa.binary_search;

/**
 * PATTERN   : Binary Search — Type 1 Variant (Rotated Array)
 * DIFFICULTY: Medium
 * DATE      : Mar 4, 2026
 *
 * PROBLEM:
 * Search target in a rotated sorted array of distinct integers.
 * Return index or -1. Must run in O(log n).
 *
 * TRICK SENTENCE:
 * "One half is always sorted in a rotated array.
 *  Find the sorted half. Check if target lives there.
 *  If yes — search it. If no — search the other half."
 *
 * THE TWO QUESTIONS AT EVERY STEP:
 * Q1: Which half is sorted?
 *     nums[left] <= nums[mid] → LEFT half sorted
 *     else                   → RIGHT half sorted
 *
 * Q2: Is target in the sorted half's range?
 *     LEFT sorted:  target >= nums[left] && target < nums[mid]
 *     RIGHT sorted: target > nums[mid]  && target <= nums[right]
 *     YES → search that half
 *     NO  → search the OTHER half
 *
 * KEY BOUNDARY RULE:
 *     Left range:  >= nums[left] (include) and < nums[mid] (exclude)
 *     Right range: > nums[mid]  (exclude) and <= nums[right] (include)
 *     mid excluded because it's already checked at loop top.
 *     Boundaries included because target could be there.
 *
 * DRY RUN [4,5,6,7,0,1,2] target=0:
 * Step1: mid=3(7), left sorted[4..7], 0 not in [4..7] → left=4
 * Step2: mid=5(1), right sorted[1..2], 0 not in [1..2] → right=4
 * Step3: mid=4(0), target==nums[mid] → return 4 ✅
 *
 * TIME : O(log n) — eliminates half search space each step
 * SPACE: O(1)     — only left, right, mid variables
 *
 * SIMILAR PROBLEMS:
 * - Find Minimum in Rotated Sorted Array (Medium)
 * - Search in Rotated Sorted Array II (with duplicates)
 * - Binary Search (basic)
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check mid first — might be the answer
            if (nums[mid] == target) return mid;

            // Q1: Is left half sorted?
            if (nums[left] <= nums[mid]) {
                // Q2: Is target in left sorted range?
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;  // target in left half
                } else {
                    left = mid + 1;   // target in right half
                }
            } else {
                // Right half is sorted
                // Q2: Is target in right sorted range?
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;   // target in right half
                } else {
                    right = mid - 1;  // target in left half
                }
            }
        }

        return -1;
    }
}