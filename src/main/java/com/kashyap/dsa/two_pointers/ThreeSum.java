package com.kashyap.dsa.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PATTERN   : Two Pointers — Three Pointer Extension
 * DIFFICULTY: Medium
 * DATE      : Mar 3, 2026
 *
 * PROBLEM:
 * Find all unique triplets in array that sum to zero.
 *
 * TRICK SENTENCE:
 * "Sort first. Fix one number with i.
 *  Use two pointers on the rest.
 *  Skip duplicates by looking BACKWARD not forward."
 *
 * APPROACH:
 * 1. Sort array — enables two pointer logic
 * 2. Fix nums[i] as first number of triplet
 * 3. Find pairs in [i+1..n-1] that sum to -nums[i]
 * 4. Skip duplicate i values (compare to i-1, not i+1)
 * 5. After finding triplet, skip duplicate left/right values
 *
 * DUPLICATE HANDLING — THE KEY INSIGHT:
 * i:     compare nums[i] == nums[i-1] → skip current i
 *        (already explored same value in previous iteration)
 * left:  after triplet found, skip while nums[left]==nums[left+1]
 * right: after triplet found, skip while nums[right]==nums[right-1]
 *
 * EARLY TERMINATION:
 * If nums[i] > 0 → all three numbers positive → sum > 0 → break
 * Array is sorted so this holds for all remaining i values.
 *
 * DRY RUN [-4,-1,-1,0,1,2]:
 * i=0(-4): no triplet (sum always negative)
 * i=1(-1): [-1,-1,2] ✅  [-1,0,1] ✅
 * i=2(-1): duplicate of i=1 → SKIP
 * i=3( 0): 0+1+2=3 > 0 → break
 * Result: [[-1,-1,2],[-1,0,1]] ✅
 *
 * TIME : O(n²) — O(n log n) sort + O(n²) two pointer search
 * SPACE: O(n) — sorting space, output list not counted
 *
 * SIMILAR PROBLEMS:
 * - Two Sum II (sorted array)
 * - Four Sum
 * - Three Sum Closest
 * - Container With Most Water
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        if (nums == null || nums.length < 3) return triplets;

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            // Early termination — smallest number > 0 means
            // no triplet can sum to zero
            if (nums[i] > 0) break;

            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    triplets.add(List.of(nums[i], nums[left], nums[right]));

                    // Skip duplicates on both sides
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return triplets;
    }
}