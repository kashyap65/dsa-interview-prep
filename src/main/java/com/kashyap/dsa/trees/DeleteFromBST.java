package com.kashyap.dsa.trees;

/**
 * PATTERN   : BST — Search/Insert/Delete
 * DIFFICULTY: Medium
 * DATE      : Apr 13, 2026
 * LEETCODE  : 450
 *
 * TRICK SENTENCE:
 * "Delete has 3 cases: leaf→null, one child→return child,
 *  two children→find inorder successor (leftmost of right),
 *  replace value, delete successor from right subtree."
 *
 * WHY INORDER SUCCESSOR:
 *   Smallest value in right subtree.
 *   > everything in left subtree ✅
 *   < everything in right subtree ✅
 *   Perfect BST replacement. Property maintained.
 *
 * 3 CASES:
 *   Case 1: leaf node      → return null
 *   Case 2: one child      → return that child
 *   Case 3: two children   → successor replaces deleted node
 *
 * KEY PATTERN: root.left = deleteNode(root.left, key)
 *   Return value from recursion MUST be assigned.
 *   Same as Insert — this is how tree gets modified.
 *
 * TIME : O(h) = O(log n) balanced, O(n) skewed
 * SPACE: O(h) recursion stack
 *
 * SIMILAR PROBLEMS:
 * - Search BST (700)
 * - Insert into BST (701)
 * - Kth Smallest in BST (230)
 */
public class DeleteFromBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left  = deleteNode(root.left,  key);  // go left
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);  // go right
        } else {
            // FOUND — handle 3 cases
            if (root.left  == null) return root.right; // case 1 + 2a
            if (root.right == null) return root.left;  // case 2b

            // case 3: two children → inorder successor
            TreeNode successor = findMin(root.right);
            root.val   = successor.val;                // replace value
            root.right = deleteNode(root.right, successor.val); // remove
        }

        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}