package com.kashyap.dsa.two_pointers;

import java.util.HashMap;

/**
 * PATTERN   : Two Pointers / HashMap Lookup
 * DIFFICULTY: Easy
 * DATE      : Mar 1, 2026
 * PROBLEM:
 * Given an array of integers and a target, return indices of the
 * two numbers that add up to the target. Exactly one solution exists.
 * TRICK SENTENCE:
 * "Store what you've SEEN. Check if what you NEED is already there."
 * APPROACH:
 * Single-pass HashMap. For each element, check if its complement
 * (target - current) was already seen. If yes — we found our pair.
 * If no — store current element and move on.

 * WHY SINGLE PASS WORKS:
 * When we find a valid pair at index i, the smaller index is already
 * in the map (it was stored when we passed it earlier).
 * We never need to look ahead — only behind.

 * TIME : O(n)  — single pass through the array
 * SPACE: O(n)  — HashMap stores at most n elements

 * SIMILAR PROBLEMS USING THIS SAME PATTERN:
 * - Two Sum II (sorted array — use two pointers instead)
 * - Four Sum
 * - Subarray Sum Equals K
 * - Longest Substring Without Repeating Characters
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // Key   = number value
        // Value = index where it was seen
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];   // what we NEED

            if (map.containsKey(complement)) {
                // Found it — complement was seen earlier (smaller index)
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);                 // store what we've SEEN
        }

        return new int[]{-1, -1};  // unreachable per problem constraints
    }
}