package com.kashyap.dsa.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN   : Binary Tree — DFS + Backtracking
 * DIFFICULTY: Medium
 * DATE      : Apr 6, 2026
 * LEETCODE  : 113
 *
 * PROBLEM:
 * Return ALL root-to-leaf paths summing to targetSum.
 *
 * TRICK SENTENCE:
 * "Choose: add to path. Explore: recurse both sides.
 *  Unchoose: remove last. Add COPY not reference at leaf."
 *
 * BACKTRACKING 3-STEP PATTERN:
 *   CHOOSE:   path.add(node.val)
 *   EXPLORE:  dfs(left) + dfs(right)
 *   UNCHOOSE: path.remove(path.size()-1)
 *
 * WHY new ArrayList<>(path) NOT path:
 *   path is shared across all calls (pass by reference).
 *   Adding path directly = all result entries point to SAME list.
 *   As backtracking erases path → all result entries become [].
 *   new ArrayList<>(path) = snapshot/copy at this moment.
 *   Future changes to path don't affect the copy. ✅
 *
 * WHY remove is AFTER both recursive calls:
 *   While exploring left and right subtrees —
 *   current node is still part of the active path.
 *   Only remove AFTER both subtrees fully explored.
 *   = "I sign out only when completely done here."
 *
 * DRY RUN (targetSum=22):
 *   dfs(5): path=[5]
 *     dfs(4): path=[5,4]
 *       dfs(11): path=[5,4,11]
 *         dfs(7): path=[5,4,11,7] leaf, sum=-5 ≠ 0
 *                 remove → path=[5,4,11]
 *         dfs(2): path=[5,4,11,2] leaf, sum=0 ✅
 *                 result=[[5,4,11,2]]
 *                 remove → path=[5,4,11]
 *         remove → path=[5,4]
 *       remove → path=[5]
 *     dfs(8): path=[5,8] ... finds [5,8,4,5] ✅
 *     remove → path=[5]
 *   remove → path=[]
 *
 * TIME : O(n²) — n nodes, each path copy takes O(n)
 * SPACE: O(h)  — recursion stack + path list = tree height
 *
 * SIMILAR PROBLEMS:
 * - Path Sum I (112)    — true/false only
 * - Path Sum III (437)  — any path, not root-to-leaf
 * - Sum Root to Leaf Numbers (129)
 * - All Paths from Source to Target (797) — graph version
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int remaining,
                     List<Integer> path,
                     List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);          // CHOOSE
        remaining -= node.val;

        // base case: leaf + target hit
        if (node.left == null && node.right == null
                && remaining == 0) {
            result.add(new ArrayList<>(path)); // add COPY
        }

        dfs(node.left,  remaining, path, result); // EXPLORE
        dfs(node.right, remaining, path, result);

        path.remove(path.size() - 1); // UNCHOOSE
    }
}