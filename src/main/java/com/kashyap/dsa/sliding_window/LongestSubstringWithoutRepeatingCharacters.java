package com.kashyap.dsa.sliding_window;

import java.util.HashSet;

/**
 * PATTERN   : Sliding Window — Variable Size
 * DIFFICULTY: Medium
 * DATE      : Mar 2, 2026
 *
 * PROBLEM:
 * Find the length of the longest substring without
 * repeating characters.
 *
 * TRICK SENTENCE:
 * "Right always expands. Left only moves when the
 *  window breaks a rule."
 *
 * THE 3-STEP RITUAL:
 * 1. WINDOW  : all characters between left and right
 * 2. STATE   : HashSet of characters currently in window
 * 3. VIOLATION: duplicate character enters from right
 *
 * KEY INSIGHT:
 * When duplicate found — don't STOP, don't jump.
 * Slide left forward one step at a time until
 * the duplicate is fully removed from the window.
 * Use WHILE (not IF) — duplicate may not be at left edge.
 *
 * EXAMPLE:
 * "abba"
 *  j=0: add a → {a}        max=1
 *  j=1: add b → {a,b}      max=2
 *  j=2: 'b' exists!
 *       while has 'b': remove a(i=0)→i=1, still has b
 *       while has 'b': remove b(i=1)→i=2, clean ✅
 *       add b → {b}         max=2
 *  j=3: add a → {b,a}      max=2 ✅
 *
 * WHY HashSet NOT HashMap?
 * We only need to know IF char exists in window — not count.
 * Set = O(1) contains/add/remove. Perfect fit.
 *
 * TIME : O(n) — each character added and removed at most once
 * SPACE: O(min(n,m)) — m = charset size (26 for lowercase)
 *
 * SIMILAR PROBLEMS:
 * - Longest Substring with At Most K Distinct Characters
 * - Permutation in String
 * - Minimum Window Substring
 * - Fruit Into Baskets
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String input) {
        if (input == null || input.length() == 0) return 0;

        HashSet<Character> set = new HashSet<>();
        int left = 0, max = 0;

        for (int right = 0; right < input.length(); right++) {
            char ch = input.charAt(right);

            // Slide left until duplicate is fully removed
            while (set.contains(ch)) {
                set.remove(input.charAt(left));
                left++;
            }

            set.add(ch);                              // expand right
            max = Math.max(max, right - left + 1);   // record window size
        }

        return max;
    }
}