package com.kashyap.dsa.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
"Seed queue with root. Snapshot size before each level.
 Process exactly snapshot nodes. Add children if not null.
 Snapshot = the trick that separates levels."

 TIME:  O(n) — every node polled and added exactly once
SPACE: O(w) — w = maximum width of tree
              worst case = last level = n/2 nodes
              so O(n) in worst case
 */

public class BFSTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // snapshot
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                assert node != null;
                level.add(node.val);
                if(node.left!=null)  queue.add(node.left);
                if(node.right!=null)  queue.add(node.right);
            }

            result.add(level);
        }
        return result;
    }
}
