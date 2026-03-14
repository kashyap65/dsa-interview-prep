package com.kashyap.dsa.prefixsumandhashtable;

import java.util.HashSet;

/**
 * PATTERN   : HashSet — Existence Check
 * DIFFICULTY: Easy
 * DATE      : Mar 6, 2026
 *
 * PROBLEM:
 * Return true if any value appears at least twice.
 *
 * TRICK SENTENCE:
 * "set.add() returns false when duplicate found.
 *  Flip it — that's your early return true."
 *
 * TWO APPROACHES:
 * 1. Sort + compare adjacent → O(n log n) time, O(1) space
 * 2. HashSet                 → O(n) time,       O(n) space
 * Trade-off: speed vs memory. HashSet faster. Sort saves space.
 *
 * WHY HASHSET NOT HASHMAP:
 * Need existence only — not frequency, not index.
 * HashSet = keys only. HashMap = overkill here.
 * Rule: need frequency → HashMap. need existence → HashSet.
 *
 * set.add() RETURN VALUE TRICK:
 * returns TRUE  → element is NEW → continue
 * returns FALSE → element EXISTS → duplicate → return true
 *
 * TIME : O(n) — single pass
 * SPACE: O(n) — HashSet stores up to n elements
 *
 * SIMILAR PROBLEMS:
 * - Find All Duplicates in Array
 * - Single Number
 * - Intersection of Two Arrays
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (!set.add(n)) return true;   // duplicate found
        }

        return false;  // no duplicate found
    }
}