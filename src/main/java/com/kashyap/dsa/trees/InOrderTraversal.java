package com.kashyap.dsa.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*

## The One Rule — Lock It
```
Iterative inorder = 3 lines in outer loop:
  1. WHILE going left  → push everything
  2. Pop ONE node      → add to result
  3. Go right          → let outer loop handle left

Pop is NEVER a while loop.
Pop once. Go right. Outer loop does the rest.
```

---

## Complexity — Both Versions
```
RECURSIVE:
  Time:  O(n) — visit every node once
  Space: O(h) — call stack depth = tree height

ITERATIVE:
  Time:  O(n) — visit every node once
  Space: O(h) — explicit stack depth = tree height

Both are identical complexity.
Iterative just makes the stack explicit.


 */

public class InOrderTraversal {

    public List<Integer> inOrderTraversal(TreeNode treeNode){
        List<Integer> output = new ArrayList<>();
        inOrderTraversal(treeNode,output);
        return output;
    }

    private void inOrderTraversal(TreeNode treeNode,List<Integer> output){
        if (treeNode==null)
            return;
        inOrderTraversal(treeNode.left,output);
        output.add(treeNode.val);
        inOrderTraversal(treeNode.right,output);
    }

    public List<Integer> inorderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {

            // go LEFT as far as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // pop ONE node → add → go right
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;   // outer loop will go left from here
        }

        return result;
    }
}
