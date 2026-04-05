package com.kashyap.dsa.trees;

import java.util.*;

/**
 * PATTERN   : Binary Tree — BFS Level Order
 * DIFFICULTY: Medium
 * DATE      : Apr 5, 2026
 * LEETCODE  : 199
 *
 * PROBLEM:
 * Return values of nodes visible from right side.
 * = rightmost node at each level.
 *
 * TRICK SENTENCE:
 * "BFS level by level. Last node of each level
 *  = rightmost. i == levelSize-1 → add to result."
 *
 * KEY INSIGHT:
 * "Level" in problem → BFS → Queue.
 * Snapshot size before each level.
 * Last node in for loop (i == size-1) = rightmost.
 *
 * DRY RUN:
 *         1              Level 0: [1]    → last=1 ✅
 *        / \             Level 1: [2,3]  → last=3 ✅
 *       2   3            Level 2: [5,4]  → last=4 ✅
 *        \   \
 *         5   4          Output: [1,3,4] ✅
 *
 * TIME : O(n) — visit every node once
 * SPACE: O(w) — w = max width of tree (queue size)
 *
 * VARIATION: Left Side View → i == 0 instead of i == levelSize-1
 *
 * SIMILAR PROBLEMS:
 * - Binary Tree Level Order Traversal (102)
 * - Binary Tree Zigzag Level Order (103)
 * - Average of Levels in Binary Tree (637)
 */
public class RightSideViewOfTree {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();          // snapshot

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();



                if (i == levelSize - 1)            // last node = rightmost
                    result.add(node.val);

                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }
}

/*
// ONE character change:
if (i == 0) result.add(node.val);  // first node = leftmost
// vs
if (i == levelSize - 1) result.add(node.val);  // last = rightmost

// Same code. Different condition. Different view.
// Mention this in interviews — shows pattern mastery.
 */