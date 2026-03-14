package com.kashyap.dsa.prefixsumandhashtable;

/**
 * PATTERN   : Prefix Sum (Left-Right Product Passes)
 * DIFFICULTY: Medium
 * DATE      : Mar 1, 2026
 *
 * PROBLEM:
 * Return array where output[i] = product of all nums except nums[i].
 * No division allowed. Must be O(n) time, O(1) extra space.
 *
 * TRICK SENTENCE:
 * "Look left, then look right. Left pass fills the answer.
 *  Right pass updates it with a single running variable."
 *
 * THE MENTAL MOVIE:
 * Stand in a line. Your answer = product of everyone LEFT of you
 * multiplied by product of everyone RIGHT of you.
 * Two passes — never need to look at yourself.
 *
 * THE 3 AHA MOMENTS:
 * Aha 1: answer[i] = leftProduct[i] × rightProduct[i]
 * Aha 2: Build left products in one forward pass → output array
 * Aha 3: No right array needed → one variable going backwards
 *
 * CRITICAL ORDER IN PASS 2:
 * output[i] *= rightProduct  →  THEN  rightProduct *= nums[i]
 * Multiply first, update after.
 * Reversing this includes nums[i] in its own answer — wrong.
 *
 * WHY BOTH START WITH 1:
 * output[0] = 1  → nothing to the left of index 0 (empty product = 1)
 * rightProduct=1 → nothing to the right of last index (empty product = 1)
 * Starting with 0 would corrupt every multiplication downstream.
 *
 * WHY NO DIVISION:
 * Division breaks when nums[i] = 0.
 * Left×right approach handles zeros naturally — no division needed.
 *
 * EXAMPLE WALKTHROUGH:
 * nums     = [ 1,  2,  3,  4]
 * Pass 1   = [ 1,  1,  2,  6]   (left products)
 * Pass 2   = [24, 12,  8,  6]   (× right products)
 *
 * TIME : O(n)  — two passes
 * SPACE: O(1)  — output array doesn't count per problem rules
 *
 * SIMILAR PROBLEMS USING THIS SAME PATTERN:
 * - Trapping Rain Water (left max + right max arrays)
 * - Sum of distances in Tree
 * - Minimum Suffix and Prefix products
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        int[] output = new int[nums.length];
        output[0] = 1;  // nothing to the left of index 0

        // Pass 1 (left to right): fill output with left products
        for (int i = 1; i < nums.length; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        // Pass 2 (right to left): multiply by running right product
        int rightProduct = 1;  // nothing to the right of last index
        for (int i = nums.length - 1; i >= 0; i--) {
            output[i] = output[i] * rightProduct;  // multiply FIRST
            rightProduct *= nums[i];               // update AFTER
        }

        return output;
    }
}