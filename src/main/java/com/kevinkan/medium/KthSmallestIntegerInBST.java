package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.List;

import com.kevinkan.utility.TreeNode;

/**
* Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) in the tree.
* A binary search tree satisfies the following constraints:
*  The left subtree of every node contains only nodes with keys less than the node's key.
*  The right subtree of every node contains only nodes with keys greater than the node's key.
*  Both the left and right subtrees are also binary search trees.
* Constraints:
* 1 <= k <= The number of nodes in the tree <= 1000.
* 0 <= Node.val <= 1000
*/
public class KthSmallestIntegerInBST {

    /**
     * In Order DFS Approach
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> inOrder = new ArrayList<>();
        kthSmallest_helper(root, inOrder);

        return inOrder.get(k - 1);
    }

    private void kthSmallest_helper(TreeNode node, List<Integer> inOrder) {
        if (node == null) return;

        kthSmallest_helper(node.left, inOrder);
        inOrder.add(node.val);
        kthSmallest_helper(node.right, inOrder);
    }
}
