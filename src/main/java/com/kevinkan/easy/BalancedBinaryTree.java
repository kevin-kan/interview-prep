package com.kevinkan.easy;

import com.kevinkan.utility.TreeNode;


/**
* Given a binary tree, return true if it is height-balanced and false otherwise
* A height-balanced binary tree is defined as a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
* 
* Constraints:
* The number of nodes in the tree is int he range [0, 1000].
* -1000 <= Node.val <= 1000
*/
public class BalancedBinaryTree {
    /**
     * Recursive DFS Solution
     * We need to process this bottom up, or else it'll be O(n^2) speed due to getting the height at each node on our way down
     * Time Complexity: O(n) 
     * Space Complexity: O(n)
     */
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root).isBalanced;
    }

    private TreeInfo checkBalance(TreeNode root) {
        // Base Case: Tree is null
        if (root == null) return new TreeInfo(true, 0);

        // Tree is unbalanced as soon as either child is unbalanced, so check along the way.
        TreeInfo leftInfo = checkBalance(root.left);
        if (!leftInfo.isBalanced) return leftInfo;
        TreeInfo rightInfo = checkBalance(root.right);
        if (!rightInfo.isBalanced) return rightInfo;

        // We know left & right trees are balanced. Update heights and continue. 
        return new TreeInfo(
            Math.abs(leftInfo.height - rightInfo.height) <= 1,
            Math.max(leftInfo.height, rightInfo.height) + 1
        );
    }

    private class TreeInfo {
        boolean isBalanced;
        int height;

        public TreeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

}
