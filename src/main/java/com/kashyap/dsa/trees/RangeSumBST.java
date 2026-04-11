package com.kashyap.dsa.trees;

/**
 * PATTERN   : BST — DFS with Pruning
 * DIFFICULTY: Easy
 * DATE      : Apr 10, 2026
 * LEETCODE  : 938
 *
 * PROBLEM:
 * Return sum of BST node values within [low, high].
 *
 * TRICK SENTENCE:
 * "Use BST property to prune. val>high → only go left.
 *  val<low → only go right. In range → add + both sides."
 *
 * PRUNING RULES (key insight):
 *   node.val > high → right subtree all > high → skip right
 *   node.val < low  → left subtree all < low   → skip left
 *   in range        → add + recurse both sides
 *
 * WITHOUT PRUNING: O(n) — visits all nodes
 * WITH PRUNING:    O(log n) avg on balanced BST
 *
 * CLEANER APPROACH: return int directly (no class field)
 *   Sum bubbles up via return values.
 *   No helper method needed.
 *
 * DRY RUN (low=7, high=15):
 *   visit(10): in range → sum=10, recurse both
 *   visit(5):  5<7 → skip left(3), only go right
 *   visit(7):  in range → sum=17
 *   visit(15): in range → sum=32
 *   visit(18): 18>15 → only go left (null) → stop
 *   return 32 ✅
 *
 * TIME : O(n) worst, O(log n) avg with pruning
 * SPACE: O(h) recursion stack
 *
 * SIMILAR PROBLEMS:
 * - Kth Smallest in BST (230)
 * - Convert BST to Greater Tree (538)
 * - Search in BST (700)
 */
public class RangeSumBST {

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;

        // PRUNE — BST property
        if (root.val > high)
            return rangeSumBST(root.left,  low, high); // skip right
        if (root.val < low)
            return rangeSumBST(root.right, low, high); // skip left

        // in range — add + recurse both sides
        return root.val
                + rangeSumBST(root.left,  low, high)
                + rangeSumBST(root.right, low, high);
    }
}