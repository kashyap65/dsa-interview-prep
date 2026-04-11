package com.kashyap.dsa.trees;

import java.util.*;

/**
 * PATTERN   : Binary Tree — BFS Level Order
 * DIFFICULTY: Medium
 * DATE      : Apr 12, 2026
 *
 * TRICK SENTENCE:
 * "BFS snapshot. levelSize%2!=0 → odd level → add to result.
 *  Check level size BEFORE loop. Add path only when odd."
 *
 * TIME : O(n)   SPACE: O(w)
 */
public class BFSReturnOddNumberOfNodes {

    List<List<Integer>> bfsReturnOddLevels(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> path = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (levelSize % 2 != 0) path.add(node.val); // odd level
                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            if (levelSize % 2 != 0) result.add(path); // only add if odd
        }
        return result;
    }
}