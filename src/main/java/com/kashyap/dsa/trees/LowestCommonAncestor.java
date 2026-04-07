package com.kashyap.dsa.trees;

/**
 * PATTERN   : Binary Tree — DFS Postorder (bottom-up)
 * DIFFICULTY: Medium
 * DATE      : Apr 6, 2026
 * LEETCODE  : 236
 *
 * PROBLEM:
 * Find lowest common ancestor of nodes p and q.
 * A node can be ancestor of itself.
 *
 * TRICK SENTENCE:
 * "Search both subtrees. Both return non-null → current = LCA.
 *  One returns non-null → LCA deeper in that subtree.
 *  Postorder: children report first, parent decides."
 *
 * 4 CASES:
 *   root==null → return null (not found)
 *   root==p||q → return root (found one — could be LCA)
 *   both sides non-null → root is LCA (p one side, q other)
 *   one side non-null → LCA in that subtree, return it
 *
 * WHY POSTORDER:
 *   Children search first → report results up.
 *   Parent decides based on children's reports.
 *   Info flows UP → Postorder.
 *
 * WHY root==p returns immediately without finding q:
 *   If p is ancestor of q → p IS the LCA.
 *   No need to find q. Return p. Correct by definition.
 *
 * DRY RUN LCA(6,4):
 *   node6 found → returns node6
 *   node4 found → returns node4
 *   node5: left=node6, right=node4 → BOTH → return node5 ✅
 *
 * TIME : O(n) — visit every node once
 * SPACE: O(h) — recursion stack = tree height
 *
 * SIMILAR PROBLEMS:
 * - LCA of BST (235) — simpler, use BST property
 * - LCA of Deepest Leaves (1123)
 * - Kth Ancestor of Tree Node (1696)
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode p,
                                         TreeNode q) {
        // base: null = not found. p or q found = return it.
        if (root == null || root == p || root == q) return root;

        // search both subtrees (postorder)
        TreeNode left  = lowestCommonAncestor(root.left,  p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // both found → current node is LCA
        if (left != null && right != null) return root;

        // one side found → LCA is deeper in that subtree
        return left != null ? left : right;
    }
}