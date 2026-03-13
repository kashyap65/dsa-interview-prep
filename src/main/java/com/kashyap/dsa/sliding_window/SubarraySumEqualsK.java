package com.kashyap.dsa.sliding_window;

import java.util.HashMap;

/**
 * PATTERN   : Prefix Sum + HashMap
 * DIFFICULTY: Medium
 * DATE      : Mar 6, 2026
 *
 * PROBLEM:
 * Return count of contiguous subarrays whose sum equals k.
 *
 * TRICK SENTENCE:
 * "At each index ask: have I seen (currSum-k) before?
 *  If yes — those many subarrays end here and sum to k."
 *
 * WHY NOT SLIDING WINDOW:
 * Negative numbers allowed → shrinking window doesn't
 * guarantee smaller sum → sliding window logic breaks.
 *
 * KEY INSIGHT — prefixSum trick:
 * If prefix[j] - prefix[i] = k
 * then subarray nums[i+1..j] sums to k.
 * So at index j: how many previous i have prefix[i] = currSum-k?
 * HashMap stores exactly this count.
 *
 * WHY {0:1} initialization:
 * Handles case where entire subarray from index 0 sums to k.
 * "Empty prefix before index 0 has sum 0 — occurred once."
 *
 * WHY lookup BEFORE update:
 * Must not count current index as a previous prefix.
 * Order: ADD to currSum → LOOKUP → COUNT → UPDATE map.
 *
 * DRY RUN [1,1,1] k=2:
 * i=0: sum=1, lookup=-1 → NO,  count=0, map={0:1,1:1}
 * i=1: sum=2, lookup=0  → YES, count=1, map={0:1,1:1,2:1}
 * i=2: sum=3, lookup=1  → YES, count=2, map={0:1,1:1,2:1,3:1}
 * return 2 ✅
 *
 * TIME : O(n) — single pass through array
 * SPACE: O(n) — HashMap stores at most n+1 prefix sums
 *
 * SIMILAR PROBLEMS:
 * - Continuous Subarray Sum (multiple of k)
 * - Subarray Sum Divisible by K
 * - Longest Subarray with Sum K
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  // empty prefix has sum 0, occurred once

        int currSum = 0, count = 0;

        for (int num : nums) {
            currSum += num;                                    // update prefix sum
            count += map.getOrDefault(currSum - k, 0);        // lookup then count
            map.put(currSum, map.getOrDefault(currSum, 0) + 1); // update map
        }

        return count;
    }
}