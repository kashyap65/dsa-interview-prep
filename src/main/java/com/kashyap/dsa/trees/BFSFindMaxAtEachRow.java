package com.kashyap.dsa.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSFindMaxAtEachRow {

    List<Integer> bfsFindMaxAtEachRow(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max,node.val);
                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(max);
        }
        return result;
    }
}
