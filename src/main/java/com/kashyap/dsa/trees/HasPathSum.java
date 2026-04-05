package com.kashyap.dsa.trees;

/**
 * PATTERN   : Binary Tree — DFS Preorder (top-down)
 * DIFFICULTY: Easy
 * DATE      : Apr 5, 2026
 * LEETCODE  : 112
 *
 * PROBLEM:
 * Return true if tree has root-to-leaf path
 * whose node values sum to targetSum.
 *
 * TRICK SENTENCE:
 * "Subtract node value. At leaf: remaining==0? YES.
 *  Information flows DOWN → Preorder.
 *  Leaf = both children null — NOT null node."
 *
 * KEY INSIGHTS:
 * 1. Pass REMAINING down (not original target)
 *    Parent subtracts its value → child gets updated remainder
 * 2. Leaf check = left==null && right==null
 *    NOT when node==null (that's past the leaf)
 * 3. || not && — ANY path to leaf qualifies
 *
 * DIRECTION OF INFORMATION:
 *   remaining flows DOWN parent→children = Preorder
 *   (contrast: height flows UP children→parent = Postorder)
 *
 * DRY RUN (targetSum=22):
 *   5→4→11→2: remaining 22→17→13→2→0 → TRUE ✅
 *   5→4→11→7: remaining 22→17→13→2→-5 → FALSE
 *
 * EDGE CASES:
 *   Empty tree (null root)    → false
 *   Single node = target      → true (it IS a leaf)
 *   Single node ≠ target      → false
 *   Node with one child       → not a leaf → keep going
 *   Negative values in tree   → works (no assumptions on sign)
 *
 * TIME : O(n) — visit every node once (worst case)
 * SPACE: O(h) — recursion stack = tree height
 *
 * SIMILAR PROBLEMS:
 * - Path Sum II (113) — return all paths
 * - Path Sum III (437) — any path not just root-to-leaf
 * - Sum Root to Leaf Numbers (129)
 */
public class HasPathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        int remaining = targetSum - root.val;

        // leaf node — did we hit the target exactly?
        if (root.left == null && root.right == null)
            return remaining == 0;

        // not a leaf — try both paths
        return hasPathSum(root.left,  remaining)
                || hasPathSum(root.right, remaining);
    }
}