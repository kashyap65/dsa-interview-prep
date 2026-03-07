package com.kashyap.dsa.two_pointers;

/**
 * PATTERN   : Two Pointers — Opposite Ends
 * DIFFICULTY: Medium
 * DATE      : Mar 3, 2026
 *
 * PROBLEM:
 * Given array of heights, find two lines that form
 * a container holding the most water.
 *
 * TRICK SENTENCE:
 * "Move the shorter wall — it's the bottleneck.
 *  Moving the taller wall only costs width with zero upside."
 *
 * THE WHY (Principal Engineer answer):
 * Water level = min(h[left], h[right]) — shorter wall limits.
 * Moving taller pointer inward = lose width + same bottleneck
 *                              = area can only decrease. ❌
 * Moving shorter pointer inward = lose width + possible taller wall
 *                               = area might increase. ✅
 * Always take possible gain over guaranteed loss.
 *
 * DRY RUN:
 * [1, 8, 6, 2, 5, 4, 8, 3, 7]
 * left=0(1), right=8(7) → area=8  → move left
 * left=1(8), right=8(7) → area=49 → move right ← MAX
 * left=1(8), right=7(3) → area=18 → move right
 * left=1(8), right=6(8) → area=40 → move left (equal)
 * left=2(6), right=6(8) → area=24 → move left
 * ... converges to max=49
 *
 * TIME : O(n) — single pass, each element visited once
 * SPACE: O(1) — only left, right, maxArea variables
 *
 * SIMILAR PROBLEMS:
 * - Trapping Rain Water (Hard)
 * - Two Sum II (sorted array)
 * - Three Sum
 */
public class ContainerWithMostWater {

    public int maxArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int left = 0, right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int area = (right - left) * Math.min(heights[left], heights[right]);
            maxArea = Math.max(area, maxArea);

            // Move the shorter wall — it's the bottleneck
            // Moving taller wall = guaranteed loss (same height, less width)
            // Moving shorter wall = possible gain (might find taller wall)
            if (heights[left] <= heights[right])
                left++;
            else
                right--;
        }

        return maxArea;
    }
}
