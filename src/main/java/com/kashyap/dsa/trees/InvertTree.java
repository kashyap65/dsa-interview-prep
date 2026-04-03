package com.kashyap.dsa.trees;

/**
 * PATTERN   : Binary Tree — DFS Preorder (top-down)
 * DIFFICULTY: Easy
 * DATE      : Apr 3, 2026
 * LEETCODE  : 226
 *
 * PROBLEM:
 * Invert a binary tree (mirror image). Return root.
 *
 * TRICK SENTENCE:
 * "At every node — swap left and right. Recurse both.
 *  One swap per node. Recursion handles the rest."
 *
 * APPROACH:
 * Preorder DFS — swap at current node FIRST,
 * then recurse into children.
 * Works postorder too — both give same result.
 *
 * DRY RUN:
 *      4              4
 *    /   \   →      /   \
 *   2     7        7     2
 *  / \   / \      / \   / \
 * 1   3 6   9    9   6 3   1
 *
 * TIME : O(n) — visit every node once
 * SPACE: O(h) — recursion stack = tree height
 *
 * SIMILAR PROBLEMS:
 * - Symmetric Tree (101)
 * - Flip Equivalent Binary Trees (951)
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // swap left and right children
        TreeNode temp  = root.left;
        root.left      = root.right;
        root.right     = temp;

        // recurse both sides
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}