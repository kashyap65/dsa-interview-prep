package com.kashyap.dsa.trees;

/**
 * PATTERN   : Binary Tree — DFS Postorder (bottom-up)
 * DIFFICULTY: Easy
 * DATE      : Apr 2, 2026
 * LEETCODE  : 104
 *
 * PROBLEM:
 * Return maximum depth of binary tree.
 * Depth = number of nodes on longest root-to-leaf path.
 *
 * TRICK SENTENCE:
 * "My depth = 1 + deeper of my two subtrees.
 *  Base case: null = 0. That's it."
 *
 * APPROACH — Postorder DFS:
 * Can't know depth from top down.
 * Must ask children first → bottom-up.
 * Left subtree reports depth. Right subtree reports depth.
 * Take max. Add 1 for current node.
 *
 * WHY POSTORDER:
 * Work happens AFTER recursive calls.
 * Children's results needed before computing current.
 * Classic bottom-up pattern.
 *
 * DRY RUN:
 *        3          depth(3) = 1 + max(depth(9), depth(20))
 *       / \                  = 1 + max(1, 2)
 *      9   20                = 3 ✅
 *         / \
 *        15   7
 *
 * TIME : O(n) — visit every node exactly once
 * SPACE: O(h) — recursion stack depth = tree height
 *        O(log n) balanced tree
 *        O(n)     skewed tree (worst case)
 *
 * SIMILAR PROBLEMS:
 * - Minimum Depth of Binary Tree (111)
 * - Balanced Binary Tree (110)
 * - Diameter of Binary Tree (543)
 */
public class MaximumDepthOfTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}