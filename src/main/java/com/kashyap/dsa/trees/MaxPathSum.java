package com.kashyap.dsa.trees;

/**
 * PATTERN   : Binary Tree — DFS Postorder (bottom-up)
 * DIFFICULTY: Hard
 * DATE      : Apr 7, 2026
 * LEETCODE  : 124
 *
 * PROBLEM:
 * Return maximum path sum. Path can start/end anywhere.
 * Must contain at least one node.
 *
 * TRICK SENTENCE:
 * "Two values per node: ARCH (L+me+R) updates global max.
 *  STICK (me+max(L,R)) returned to parent.
 *  max(0,gain) floors negative subtrees at zero."
 *
 * KEY INSIGHT — TWO SEPARATE JOBS:
 *   ARCH  = leftGain + node.val + rightGain
 *           → "best path PEAKING at this node"
 *           → updates maxSum (never returned to parent)
 *           → parent cannot use arch (would revisit node)
 *
 *   STICK = node.val + max(leftGain, rightGain)
 *           → "best path STARTING at this node, one direction"
 *           → returned to parent (parent extends upward)
 *
 * WHY Integer.MIN_VALUE not 0:
 *   All nodes could be negative. e.g. [-3] → answer = -3.
 *   0 would give wrong answer for all-negative trees.
 *
 * WHY max(0, gain):
 *   Negative subtree HURTS the sum.
 *   If leftGain = -5 → including left reduces path.
 *   max(0, -5) = 0 → effectively ignores that subtree.
 *
 * DRY RUN:
 *   -10, 9, 20(15,7)
 *   dfs(15)=15, dfs(7)=7
 *   dfs(20): arch=15+20+7=42 → maxSum=42
 *            stick=20+max(15,7)=35 → returned
 *   dfs(-10): arch=9+(-10)+35=34 → maxSum stays 42
 *   return 42 ✅
 *
 * TIME : O(n) — visit every node once
 * SPACE: O(h) — recursion stack = tree height
 *
 * SIMILAR PROBLEMS:
 * - Diameter of Binary Tree (543) — same pattern, different metric
 * - Longest Univalue Path (687)
 * - Binary Tree Maximum Path Sum with constraints
 */
public class MaxPathSum {

    int maxSum = Integer.MIN_VALUE;  // handles all-negative trees

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // gain from each side — floor at 0 (ignore if negative)
        int leftGain  = Math.max(0, dfs(node.left));
        int rightGain = Math.max(0, dfs(node.right));

        // ARCH: update global max (peaks at this node)
        maxSum = Math.max(maxSum, leftGain + node.val + rightGain);

        // STICK: return best one-direction path to parent
        return node.val + Math.max(leftGain, rightGain);
    }
}