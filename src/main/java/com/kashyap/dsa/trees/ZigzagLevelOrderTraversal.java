package com.kashyap.dsa.trees;

import java.util.*;

/**
 * PATTERN   : Binary Tree — BFS Level Order + Direction Flag
 * DIFFICULTY: Medium
 * DATE      : Apr 7, 2026
 * LEETCODE  : 103
 *
 * PROBLEM:
 * Return zigzag level order traversal.
 * Alternate left→right and right→left each level.
 *
 * TRICK SENTENCE:
 * "BFS level order. Toggle direction flag ONCE per level
 *  OUTSIDE the for loop. Reverse level list if right→left.
 *  Never toggle inside the loop — breaks mid-level."
 *
 * KEY INSIGHT:
 * Base pattern = Level Order BFS (snapshot trick).
 * Only difference = reverse alternate levels.
 * Always add children left→right (normal BFS).
 * Reverse the RESULT list, not the queue order.
 *
 * COMMON BUG:
 * Toggling flag INSIDE for loop → changes direction mid-level.
 * Toggle must happen ONCE per level, AFTER the for loop.
 *
 * DRY RUN:
 *         3
 *        / \            Level 0: [3]      L→R ✅
 *       9   20          Level 1: [20,9]   R→L ✅ (reversed)
 *          /  \         Level 2: [15,7]   L→R ✅
 *         15   7
 *
 * TIME : O(n) — visit every node once
 * SPACE: O(w) — w = max width (queue size)
 *
 * SIMILAR PROBLEMS:
 * - Binary Tree Level Order Traversal (102)
 * - Binary Tree Right Side View (199)
 * - Average of Levels (637)
 */
public class ZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();           // snapshot
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // addLast = append (left→right)
                // addFirst = prepend (right→left, auto-reverses)
                if (leftToRight) level.addLast(node.val);
                else             level.addFirst(node.val);

                // always add children normally
                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(level);
            leftToRight = !leftToRight; // toggle ONCE per level
        }
        return result;
    }
}