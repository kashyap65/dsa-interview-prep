package com.kashyap.dsa.trees;

/**
 * PATTERN   : BST — Divide and Conquer
 * DIFFICULTY: Easy
 * DATE      : Apr 14, 2026
 * LEETCODE  : 108
 *
 * TRICK SENTENCE:
 * "Middle element = root. Left half = left subtree.
 *  Right half = right subtree. start>end = null.
 *  Reverse of: BST inorder = sorted array."
 *
 * KEY INSIGHT:
 * Inorder of BST = sorted array (known from traversal).
 * This problem = REVERSE: sorted array → BST.
 * Middle element as root guarantees height balance.
 * Left half always smaller, right half always larger → BST property.
 *
 * MID FORMULA: (start + end) / 2
 *   Always picks left-center for even-length arrays.
 *   Multiple valid answers accepted by LeetCode.
 *
 * SIMILAR TO: Build Binary Tree from Pre+Inorder (105)
 *   Both use divide-and-conquer on array ranges.
 *   Both use start>end as base case.
 *
 * DRY RUN: [-10,-3,0,5,9]
 *   mid=2 → root=0
 *   left:  [-10,-3] → mid=0 → root=-10, right=-3
 *   right: [5,9]    → mid=3 → root=5,  right=9
 *
 * TIME : O(n) — visit every element once
 * SPACE: O(log n) — recursion stack (balanced tree height)
 *
 * SIMILAR PROBLEMS:
 * - Build Binary Tree from Pre+Inorder (105)
 * - Convert Sorted List to BST (109)
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int start, int end) {
        if (start > end) return null;               // empty range

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);    // middle = root

        root.left  = build(nums, start,   mid - 1); // left half
        root.right = build(nums, mid + 1, end);     // right half

        return root;
    }
}