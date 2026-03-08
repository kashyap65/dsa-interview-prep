package com.kashyap.dsa.binary_search;

/**
 * PATTERN   : Binary Search — Type 1 (Exact Search)
 * DIFFICULTY: Easy
 * DATE      : Mar 4, 2026
 *
 * PROBLEM:
 * Given sorted array of distinct integers and target,
 * return index of target or -1 if not found.
 *
 * TRICK SENTENCE:
 * "Eliminate half. Check mid. Decide direction.
 *  Never overflow — use left + (right-left)/2 always."
 *
 * TYPE 1 — EXACT SEARCH:
 * Use when: distinct elements, find any occurrence
 * Condition: while (left <= right)
 * Returns: index of target or -1
 *
 * THREE TRAPS TO AVOID:
 * 1. left <= right not left < right (exact search)
 * 2. mid = left+(right-left)/2 not (left+right)/2 (overflow)
 * 3. left=mid+1, right=mid-1 not mid (infinite loop)
 *
 * DRY RUN:
 * nums=[-1,0,3,5,9,12], target=9
 * Step 1: left=0,right=5 → mid=2, nums[2]=3 < 9 → left=3
 * Step 2: left=3,right=5 → mid=4, nums[4]=9 == 9 → return 4 ✅
 *
 * TIME : O(log n) — eliminates half search space each step
 * SPACE: O(1)     — only left, right, mid variables
 *
 * SIMILAR PROBLEMS:
 * - Search in Rotated Sorted Array (Medium)
 * - Find First and Last Position (Medium) — Type 2 + Type 3
 * - Search Insert Position (Easy) — Type 2 variant
 * - Find Minimum in Rotated Sorted Array (Medium)
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;  // safe — no overflow

            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;   // target in right half
            } else {
                right = mid - 1;  // target in left half
            }
        }

        return -1;  // target not found
    }
}