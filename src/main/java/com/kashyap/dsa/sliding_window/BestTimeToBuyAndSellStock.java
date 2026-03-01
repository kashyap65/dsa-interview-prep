package com.kashyap.dsa.sliding_window;

/**
 * PATTERN   : Sliding Window / Track Best So Far
 * DIFFICULTY: Easy
 * DATE      : Mar 1, 2026
 *
 * PROBLEM:
 * Given prices[] where prices[i] is stock price on day i,
 * return the maximum profit from one buy and one sell.
 * Sell day must come after buy day. Return 0 if no profit possible.
 *
 * TRICK SENTENCE:
 * "Track the valley (lowest price seen). At every peak, check if
 *  this is your best profit ever."
 *
 * APPROACH:
 * Single pass. Track the minimum price seen so far (cheapest buy day).
 * At every other day, calculate profit if we sold today.
 * Keep updating maximum profit seen.
 *
 * KEY INSIGHT:
 * You never need to look back more than once. The minimum price seen
 * so far is always the best possible buy day for any future sell day.
 * No need for nested loops — O(n²) brute force is never needed.
 *
 * THE MENTAL MOVIE:
 * prices = [7, 1, 5, 3, 6, 4]
 *          min=7  min=1  profit=4  profit=2  profit=5  profit=3
 *                                                      ↑ best = 5
 *
 * TIME : O(n)  — single pass
 * SPACE: O(1)  — only two variables
 *
 * SIMILAR PROBLEMS USING THIS SAME PATTERN:
 * - Maximum Subarray (Kadane's — track best sum so far)
 * - Best Time to Buy and Sell Stock II (multiple transactions)
 * - Trapping Rain Water (track max from left so far)
 * - Sliding Window Maximum
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;  // cheapest buy day seen so far
        int maxProfit = 0;                 // best profit seen so far

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];      // found a cheaper buy day
            } else {
                // if we sell today, is it better than our best so far?
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }

        return maxProfit;
    }
}