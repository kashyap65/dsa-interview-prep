package com.kashyap.dsa.trees;

/**
 * PATTERN   : Binary Tree — DFS (two pointers on tree)
 * DIFFICULTY: Easy
 * DATE      : Apr 3, 2026
 * LEETCODE  : 101
 *
 * PROBLEM:
 * Check if binary tree is symmetric (mirror of itself).
 *
 * TRICK SENTENCE:
 * "Compare TWO nodes at once — one from each side.
 *  Outer pair: left.left vs right.right.
 *  Inner pair: left.right vs right.left.
 *  Values differ → false. Never return true on value match alone."
 *
 * KEY INSIGHT:
 * Symmetry ≠ equality.
 * Left subtree must be MIRROR of right subtree.
 * Mirror = outer children match + inner children match.
 * Always check children — value match alone is not enough.
 *
 * 4 CASES in isMirror:
 *   Both null    → true  (symmetric empty branches)
 *   One null     → false (asymmetric)
 *   Values differ → false (not mirror)
 *   Otherwise    → recurse outer AND inner pairs
 *
 * DRY RUN:
 *         1
 *        / \
 *       2   2
 *      / \ / \
 *     3  4 4  3
 *
 *  isMirror(2,2): vals match → recurse
 *    isMirror(3,3): vals match → recurse → both null → true ✅
 *    isMirror(4,4): vals match → recurse → both null → true ✅
 *  return true ✅
 *
 * TIME : O(n) — every node visited once
 * SPACE: O(h) — recursion stack = tree height
 *
 * SIMILAR PROBLEMS:
 * - Invert Binary Tree (226)
 * - Same Tree (100)
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;  // both null
        if (left == null || right == null) return false; // one null
        if (left.val != right.val) return false;         // values differ

        // recurse: outer pair + inner pair BOTH must be true
        return isMirror(left.left,  right.right)
                && isMirror(left.right, right.left);
    }
}