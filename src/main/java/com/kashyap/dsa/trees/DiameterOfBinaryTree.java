package com.kashyap.dsa.trees;

/**
 * PATTERN   : Binary Tree — DFS Postorder (bottom-up)
 * DIFFICULTY: Easy
 * DATE      : Apr 2, 2026
 * LEETCODE  : 543
 *
 * PROBLEM:
 * Return length of diameter — longest path between
 * any two nodes (path does NOT need to pass through root).
 *
 * TRICK SENTENCE:
 * "At every node: diameter candidate = leftH + rightH.
 *  Track global max. Return height (1+max(L,R)) to parent.
 *  NEVER return diameter — height and diameter are different."
 *
 * KEY INSIGHT:
 * Diameter does NOT always pass through root.
 * Every node is a potential "peak" of the diameter path.
 * For each node: path through it = leftHeight + rightHeight.
 * Take global max across all nodes.
 *
 * WHY POSTORDER:
 * Need left and right heights BEFORE updating diameter.
 * Bottom-up — children report first, parent decides.
 *
 * DRY RUN:
 *        1
 *       / \           At node 2: leftH=1, rightH=1
 *      2   3          → diameter candidate = 2 ← new max
 *     / \             At node 1: leftH=2, rightH=1
 *    4   5            → diameter candidate = 3 ← new max
 *                     Answer: 3 ✅
 *
 * COMMON BUG:
 * return 1 + diameter → WRONG (diameter grows globally,
 *                               corrupts height calculation)
 * return 1 + Math.max(leftH, rightH) → CORRECT
 *
 * TIME : O(n) — visit every node exactly once
 * SPACE: O(h) — recursion stack = tree height
 *
 * SIMILAR PROBLEMS:
 * - Maximum Depth of Binary Tree (104)
 * - Binary Tree Maximum Path Sum (124)
 * - Longest Univalue Path (687)
 */
public class DiameterOfBinaryTree {

    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;

        int leftH  = height(node.left);
        int rightH = height(node.right);

        // update global diameter — path through this node
        diameter = Math.max(diameter, leftH + rightH);

        // return HEIGHT to parent — not diameter
        return 1 + Math.max(leftH, rightH);
    }
}
/*

        ## Dry Run — Verified

        1
        / \
        2   3
        / \
        4   5

height(4): L=0,R=0 → diameter=max(0,0)=0,  return 1
height(5): L=0,R=0 → diameter=max(0,0)=0,  return 1
height(2): L=1,R=1 → diameter=max(0,2)=2,  return 2
height(3): L=0,R=0 → diameter=max(2,0)=2,  return 1
height(1): L=2,R=1 → diameter=max(2,3)=3,  return 3

        return diameter = 3 ✅


        ## The One Rule — Lock It Forever

Two separate jobs in height():
        1. UPDATE diameter = Math.max(diameter, leftH + rightH)
  2. RETURN  height  = 1 + Math.max(leftH, rightH)

diameter = global — grows across all nodes
height   = local  — specific to this subtree

NEVER return 1 + diameter.
        ALWAYS return 1 + Math.max(leftH, rightH).
*/