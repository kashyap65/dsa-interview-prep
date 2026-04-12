package com.kashyap.dsa.trees;

/**
 * PATTERN   : BST — Directed Search using BST property
 * DIFFICULTY: Medium
 * DATE      : Apr 13, 2026
 * LEETCODE  : 235
 *
 * TRICK SENTENCE:
 * "BST LCA: both < root → go left. Both > root → go right.
 *  Split (one each side, or one IS root) → current = LCA.
 *  O(h) not O(n) — BST property eliminates half the tree."
 *
 * 3 CASES:
 *   p < root AND q < root → both left → go left
 *   p > root AND q > root → both right → go right
 *   otherwise (split)     → root = LCA
 *
 * SPLIT condition covers:
 *   p < root < q  (classic split)
 *   q < root < p  (reverse split)
 *   p == root     (p is LCA, q in right subtree)
 *   q == root     (q is LCA, p in left subtree)
 *
 * vs LC#236 (Binary Tree LCA):
 *   236: O(n) — must search both subtrees blindly
 *   235: O(h) — BST property gives direction each step
 *
 * DRY RUN: LCA(3,5) in BST
 *   At 6: both<6 → left
 *   At 2: both>2 → right
 *   At 4: 3<4<5 → split → return 4 ✅
 *
 * TIME : O(h) = O(log n) balanced, O(n) skewed
 * SPACE: O(h) recursion stack
 */
public class LowestCommonAncestorBST {

    public TreeNode lowestCommonAncestor(TreeNode root,
                                         TreeNode p,
                                         TreeNode q) {
        if (root == null) return null;

        // split case → current node is LCA
        if ((p.val <= root.val && root.val <= q.val) ||
                (q.val <= root.val && root.val <= p.val))
            return root;

        // both in left subtree
        if (p.val < root.val)
            return lowestCommonAncestor(root.left,  p, q);

        // both in right subtree
        return lowestCommonAncestor(root.right, p, q);
    }
}