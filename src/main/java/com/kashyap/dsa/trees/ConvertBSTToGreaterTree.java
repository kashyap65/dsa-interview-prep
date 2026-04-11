package com.kashyap.dsa.trees;

/**
 * PATTERN   : BST — Reverse Inorder Traversal
 * DIFFICULTY: Medium
 * DATE      : Apr 9, 2026
 * LEETCODE  : 538
 *
 * PROBLEM:
 * Convert BST so every node = original value
 * + sum of all greater values.
 *
 * TRICK SENTENCE:
 * "Reverse inorder (Right→Root→Left) visits largest first.
 *  Running sum. At each node: sum+=val, val=sum.
 *  One class field. Six lines."
 *
 * KEY INSIGHT:
 * Need sum of all values GREATER than current node.
 * BST reverse inorder = descending order (large→small).
 * Traverse right→root→left with running sum.
 * Each node = sum of itself + all previously visited (larger) nodes.
 *
 * REVERSE INORDER vs INORDER:
 *   Inorder:         Left → Root → Right  (ascending)
 *   Reverse inorder: Right → Root → Left  (descending)
 *   ONE change: recurse right FIRST instead of left.
 *
 * DRY RUN: visits 8,7,6,5,4,1 in that order
 *   sum: 8→15→21→26→30→31
 *   Each node updated to its running sum ✅
 *
 * TIME : O(n) — visit every node once
 * SPACE: O(h) — recursion stack
 *
 * SIMILAR PROBLEMS:
 * - Kth Smallest in BST (230)
 * - BST to Sorted Doubly Linked List (426)
 * - Range Sum of BST (938)
 */
public class ConvertBSTToGreaterTree {

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        reverseInorder(root);
        return root;
    }

    private void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);  // RIGHT first — largest to smallest
        sum += node.val;             // accumulate
        node.val = sum;              // update
        reverseInorder(node.left);   // LEFT last
    }
}