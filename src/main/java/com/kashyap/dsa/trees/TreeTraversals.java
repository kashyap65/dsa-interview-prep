package com.kashyap.dsa.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN   : Tree Traversals — Recursive
 * DIFFICULTY: Easy
 * DATE      : Apr 1, 2026
 *
 * TRICK SENTENCES:
 *   Inorder   → "Left, Me, Right   — sorted output on BST"
 *   Preorder  → "Me, Left, Right   — root always first"
 *   Postorder → "Left, Right, Me   — root always last"
 *
 * BASE CASE: node == null → return (always first line)
 *
 * KEY RULE: Every method calls ITSELF recursively.
 *           preorder calls preorder. Never cross-call.
 *
 * TIME : O(n) — visit every node exactly once
 * SPACE: O(h) — h = height of tree
 *               best case O(log n) balanced tree
 *               worst case O(n) skewed tree (like linked list)
 *
 * WHEN TO USE:
 *   Inorder   → BST problems, sorted processing
 *   Preorder  → Copy/serialize tree, top-down processing
 *   Postorder → Delete tree, bottom-up processing (height, diameter)
 */
public class TreeTraversals {

    // ── INORDER: Left → Root → Right ─────────────────────
    public List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;          // base case
        inorderHelper(node.left, result);  // LEFT
        result.add(node.val);              // ROOT (me)
        inorderHelper(node.right, result); // RIGHT
    }

    // ── PREORDER: Root → Left → Right ────────────────────
    public List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;           // base case
        result.add(node.val);               // ROOT (me first)
        preorderHelper(node.left, result);  // LEFT
        preorderHelper(node.right, result); // RIGHT
    }

    // ── POSTORDER: Left → Right → Root ───────────────────
    public List<Integer> postorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;            // base case
        postorderHelper(node.left, result);  // LEFT
        postorderHelper(node.right, result); // RIGHT
        result.add(node.val);                // ROOT (me last)
    }
}
//```
//
//        ---
//
//        ## Complexity — All 3 Traversals
//```
//TIME COMPLEXITY:
//All 3 = O(n)
//WHY: Every node visited exactly once.
//        n nodes = n visits. Always.
//
//SPACE COMPLEXITY:
//All 3 = O(h)  where h = height of tree
//
//WHY O(h)?
//Recursion uses call stack.
//At any point, stack depth = current path from root.
//        Deepest path = height of tree.
//
//Two cases:
//        ┌─────────────────────────────────────────────┐
//        │ Balanced tree:  h = log n → O(log n) space  │
//        │       1                                     │
//        │      / \                                    │
//        │     2   3      height = 2 = log₂(3) ≈ log n │
//        │    /\   /\                                  │
//        │   4  5 6  7                                 │
//        ├─────────────────────────────────────────────┤
//        │ Skewed tree:    h = n   → O(n) space        │
//        │   1                                         │
//        │    \                                        │
//        │     2          height = n (like linked list) │
//        │      \                                      │
//        │       3                                     │
//        └─────────────────────────────────────────────┘
//        ```
//
//        ---
//
//        ## Dry Run — Inorder On This Tree
//```
//        1
//        / \
//        2   3
//        /
//        4
//
//Call stack trace:
//inorder(1)
//inorder(2)           ← go left
//inorder(4)         ← go left
//inorder(null)    ← base case, return
//add 4            ← result: [4]
//inorder(null)    ← base case, return
//add 2              ← result: [4, 2]
//inorder(null)      ← go right, base case
//add 1                ← result: [4, 2, 1]
//inorder(3)           ← go right
//inorder(null)      ← base case
//add 3              ← result: [4, 2, 1, 3]
//inorder(null)      ← base case
//
//Final: [4, 2, 1, 3] ✅
//        ```
//
//        ---
//
//        ## When To Use Each — Interview Decision Table
//```
//        ┌────────────┬──────────────────────────────────────────┐
//        │ Traversal  │ Use When                                 │
//        ├────────────┼──────────────────────────────────────────┤
//        │ Inorder    │ BST sorted output                        │
//        │            │ Validate BST                             │
//        │            │ Kth smallest in BST                      │
//        ├────────────┼──────────────────────────────────────────┤
//        │ Preorder   │ Copy/clone a tree                        │
//        │            │ Serialize tree to string                 │
//        │            │ Print directory structure                │
//        ├────────────┼──────────────────────────────────────────┤
//        │ Postorder  │ Delete a tree (children first)           │
//        │            │ Calculate height / diameter              │
//        │            │ Evaluate expression trees                │
//        │            │ Any problem needing subtree result first │
//        └────────────┴──────────────────────────────────────────┘
//        ```
//
//        ---
//
//        ## The 3-Line Mental Template — Memorise This
//
//void traverse(TreeNode node) {
//    if (node == null) return;  // 1. BASE CASE — always first
//    // [preorder work here]    // 2. BEFORE = root first
//    traverse(node.left);       // 3. LEFT
//    // [inorder work here]     // 4. BETWEEN = root middle
//    traverse(node.right);      // 5. RIGHT
//    // [postorder work here]   // 6. AFTER = root last
//}
