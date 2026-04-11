package com.kashyap.dsa.trees;

import java.util.*;

/**
 * PATTERN   : Binary Tree — BFS Level Order
 * DIFFICULTY: Easy
 * DATE      : Apr 10, 2026
 * LEETCODE  : 637
 *
 * PROBLEM:
 * Return average value of nodes on each level.
 *
 * TRICK SENTENCE:
 * "BFS snapshot. Sum all nodes per level.
 *  Use LONG not int — node values can overflow int.
 *  avg = (double) sum / levelSize."
 *
 * CRITICAL BUG:
 * int sum overflows when many large node values.
 * -2³¹ to 2³¹-1 per node × 10,000 nodes = overflow.
 * Always use long for running sums in tree problems.
 *
 * TIME : O(n)
 * SPACE: O(w) — max width of tree
 *
 * SIMILAR PROBLEMS:
 * - Level Order Traversal (102)
 * - Right Side View (199)
 * - Max Width of Binary Tree (662)
 */
public class AverageOfLevelsBFS {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long sum = 0;                        // long not int

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add((double) sum / levelSize); // cast to double
        }
        return result;
    }
}