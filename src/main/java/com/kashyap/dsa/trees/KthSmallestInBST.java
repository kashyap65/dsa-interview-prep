package com.kashyap.dsa.trees;

/**
 * PATTERN   : BST — Inorder Traversal
 * DIFFICULTY: Medium
 * DATE      : Apr 9, 2026
 * LEETCODE  : 230
 *
 * PROBLEM:
 * Return kth smallest element in BST.
 *
 * TRICK SENTENCE:
 * "Inorder of BST = sorted. Count nodes visited.
 *  counter++ THEN check == k. Early exit when found."
 *
 * KEY INSIGHT:
 * Inorder traversal visits BST in ascending order.
 * kth node visited = kth smallest element.
 * No need to collect all values — stop at k.
 *
 * CRITICAL BUG:
 * Check counter==k AFTER counter++, not before.
 * counter++ = "I am visiting this node now."
 * Check happens at moment of visit, not before.
 *
 * DRY RUN (k=2):
 *     3          inorder visits: 1,2,3...
 *    / \         counter=1 at node1 (not k)
 *   1   4        counter=2 at node2 → return 2 ✅
 *    \
 *     2
 *
 * TIME : O(h+k) — go to leftmost + k steps
 * SPACE: O(h)   — recursion stack
 *
 * SIMILAR PROBLEMS:
 * - Validate BST (98)
 * - Convert BST to Greater Tree (538)
 * - BST Iterator (173)
 */
public class KthSmallestInBST {

    private int counter = 0;
    private int value   = 0;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return value;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null || counter >= k) return; // null + early exit

        inorder(node.left, k);    // go left

        counter++;                // visit this node
        if (counter == k) {       // check AFTER increment
            value = node.val;
            return;
        }

        inorder(node.right, k);   // go right
    }
}