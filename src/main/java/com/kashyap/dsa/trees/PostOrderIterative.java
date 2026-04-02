package com.kashyap.dsa.trees;

import java.util.*;

/*
INORDER:                    PREORDER:                POSTORDER:
─────────────────────────── ─────────────────────── ──────────────────────
curr pointer + stack        stack only              stack only
                                                    + reverse at end

while curr||stack:          while stack:            while stack:
  while curr:                 pop node                pop node
    push curr                 add to result           add to result
    curr=curr.left            push RIGHT first        push LEFT first
  curr=stack.pop()            push LEFT second        push RIGHT second
  add curr.val
  curr=curr.right           No reverse needed       reverse at end

Uses curr pointer           No curr pointer         No curr pointer
(go left manually)          (stack does it all)     (stack does it all)
 */

public class PostOrderIterative {
    public List<Integer> postorderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.left!=null)
                stack.add(node.left);

            if(node.right!=null)
                stack.add(node.right);
        }
         Collections.reverse(result);

        return result;
    }
}
