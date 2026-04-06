package com.kashyap.dsa.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * PATTERN   : BST — Inorder DFS Iterative
 * DIFFICULTY: Medium
 * DATE      : Apr 5, 2026
 * LEETCODE  : 98
 *
 * PROBLEM:
 * Validate whether binary tree is a valid BST.
 *
 * TRICK SENTENCE:
 * "Inorder of valid BST = strictly ascending sequence.
 *  Track previous value. If curr <= prev → invalid.
 *  Inorder catches global violations local check misses."
 *
 * WHY INORDER NOT NODE-BY-NODE CHECK:
 * Local check (left < root < right) misses global constraint.
 * Example: node 3 in right subtree of 5 looks valid locally
 * at node 6, but violates BST globally (3 < 5).
 * Inorder catches it: visits 5 then 3 → not ascending → caught.
 *
 * WHY ITERATIVE NOT RECURSIVE:
 * Both work. Iterative avoids stack overflow on large trees.
 * Uses same inorder pattern: curr pointer + explicit stack.
 *
 * DRY RUN (invalid BST):
 *       5
 *      / \           Inorder: 4, 5, 3, 6, 7
 *     4   6          At 3: prev=5, 3<=5 → return false ✅
 *        / \
 *       3   7
 *
 * TIME : O(n) — visit every node once
 * SPACE: O(h) — stack depth = tree height
 *
 * SIMILAR PROBLEMS:
 * - Kth Smallest in BST (230)
 * - Convert BST to Greater Tree (538)
 * - Recover BST (99)
 */
public class ValidateBSTCheck {

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        Integer prev  = null;  // null = no previous node yet

        while (curr != null || !stack.isEmpty()) {

            // go left as far as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // process node
            curr = stack.pop();

            // check strictly ascending
            if (prev != null && curr.val <= prev)
                return false;

            prev = curr.val;   // update previous
            curr = curr.right; // go right
        }

        return true;
    }
}