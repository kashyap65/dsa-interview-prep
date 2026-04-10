package com.kashyap.dsa.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * PATTERN   : Binary Tree — Divide and Conquer
 * DIFFICULTY: Medium
 * DATE      : Apr 8, 2026
 * LEETCODE  : 105
 *
 * PROBLEM:
 * Construct binary tree from preorder and inorder arrays.
 *
 * TRICK SENTENCE:
 * "Preorder[0] = root always. Find root in inorder →
 *  left of root = left subtree. right = right subtree.
 *  HashMap for O(1) inorder lookup. preStart>preEnd → null."
 *
 * KEY PROPERTIES USED:
 *   Preorder: first element = root of current subtree.
 *   Inorder:  root divides left and right subtrees.
 *   leftSize  = rootIdx - inStart
 *
 * INDEX FORMULAS:
 *   root     = preorder[preStart]
 *   rootIdx  = map.get(rootVal)  → O(1) with HashMap
 *   leftSize = rootIdx - inStart
 *
 *   left subtree:
 *     preorder: preStart+1 → preStart+leftSize
 *     inorder:  inStart    → rootIdx-1
 *
 *   right subtree:
 *     preorder: preStart+leftSize+1 → preEnd
 *     inorder:  rootIdx+1           → inEnd
 *
 * BASE CASE: preStart > preEnd → return null (empty range)
 *
 * DRY RUN: pre=[3,9,20,15,7], in=[9,3,15,20,7]
 *   root=3, rootIdx=1, leftSize=1
 *   left:  pre[1..1]=[9],    in[0..0]=[9]   → node(9)
 *   right: pre[2..4]=[20,15,7], in[2..4]=[15,20,7] → node(20)
 *          root=20, rootIdx=3, leftSize=1
 *          left: [15] → node(15). right: [7] → node(7)
 *
 * TIME : O(n) — HashMap makes each lookup O(1)
 * SPACE: O(n) — HashMap + recursion stack
 *
 * SIMILAR PROBLEMS:
 * - Construct from Inorder+Postorder (106)
 * - Construct from Preorder only (1008) — BST
 */
public class BuildBinaryTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0)
            return null;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        return build(preorder, 0, preorder.length - 1,
                inorder,  0, inorder.length  - 1, map);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder,  int inStart,  int inEnd,
                           Map<Integer, Integer> map) {

        if (preStart > preEnd) return null;  // empty range

        int rootVal  = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int rootIdx  = map.get(rootVal);
        int leftSize = rootIdx - inStart;

        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder,  inStart,       rootIdx - 1, map);

        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder,  rootIdx + 1,              inEnd, map);

        return root;
    }
}