package com.kashyap.dsa.trees;

/**
 * PATTERN   : Binary Tree — DFS Postorder
 * DIFFICULTY: Medium
 * DATE      : Apr 8, 2026
 * LEETCODE  : 114
 *
 * PROBLEM:
 * Flatten binary tree to linked list in-place.
 * Use right pointers. Left always null.
 * Order = preorder traversal.
 *
 * TRICK SENTENCE:
 * "Flatten left, flatten right (postorder trust).
 *  Save right. Move left to right. Null left.
 *  Walk to end. Attach saved right."
 *
 * WHY POSTORDER:
 *   Need both subtrees flat BEFORE connecting.
 *   If you connect before flattening → still a tree.
 *   Children finish first → parent connects last.
 *
 * 5 STEPS AT EACH NODE (after both recursive calls):
 *   1. save  = root.right
 *   2. root.right = root.left  (move left chain to right)
 *   3. root.left  = null
 *   4. walk to end of new right chain
 *   5. attach saved right chain at end
 *
 * DRY RUN:
 *   flatten(2): left=3,right=4 → 2→3→4
 *   flatten(5): left=null,right=6 → 5→6
 *   flatten(1): left=2→3→4, right=5→6
 *     save 5→6. move 2→3→4 to right.
 *     walk to end(4). attach 5→6.
 *     result: 1→2→3→4→5→6 ✅
 *
 * TIME : O(n²) worst case — walk to end at each node
 *        O(n)  if using Morris traversal (advanced)
 * SPACE: O(h)  recursion stack
 *
 * SIMILAR PROBLEMS:
 * - Flatten a Multilevel Doubly Linked List (430)
 * - Convert BST to Sorted Doubly Linked List (426)
 */
public class FlattenTree {

    public void flatten(TreeNode root) {
        if (root == null) return;

        // postorder: flatten children first
        flatten(root.left);
        flatten(root.right);

        // save flattened right chain
        TreeNode right = root.right;

        // move flattened left chain to right
        root.right = root.left;
        root.left  = null;

        // walk to end of new right chain
        TreeNode curr = root;
        while (curr.right != null) curr = curr.right;

        // attach saved right chain
        curr.right = right;
    }
}