package com.kevinkan.medium;

import com.kevinkan.utility.TreeNode;

/**
* Given the root of a binary tree, return true if it is a valid binary search tree, otherwise return false.
* A valid binary search tree satisfies the following constraints:
*  The left subtree of every node contains only nodes with keys less than the node's key.
*  The right subtree of every node contains only nodes with keys greater than the node's key.
*  Both the left and right subtrees are also binary search trees.
* 
* Constraints:
* 1 <= The number of nodes in the tree <= 10000.
* -2^31 <= Node.val <= 2^31 - 1
*/
public class ValidateBinarySearchTree {

    /**
     * DFS Solution
     * Time Complexity: O(n)
     * Space Complexity: O(n) -> in the call stack 
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Helper function to validate while passing the min and max to validate children. 
    // min and max need to be long in order to catch corner case of node.val = Integer.MAX_VALUE
    private boolean isValidBST(TreeNode currentNode, long min, long max) {
        // Base case for null node
        if (currentNode == null) return true;
        // Check yourself
        if (currentNode.val >= max || currentNode.val <= min) return false;
        // Check your children
        return isValidBST(currentNode.left, min, currentNode.val) && 
                isValidBST(currentNode.right, currentNode.val, max);
    }

}
