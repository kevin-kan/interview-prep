package com.kevinkan.easy;

import com.kevinkan.utility.TreeNode;

/**
* Given the root of a binary tree, return its maximum depth.
* A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*
* Constraints:
* The number of nodes in the tree is in the range [0, 10^4].
* -100 <= Node.val <= 100
*/
public class MaximumDepthOfBinaryTree {

    /*
    * Time Complexity: O(N) where N is the number of nodes in the binary tree.
    * We visit each node exactly once to compute the maximum depth.
    * Space Complexity: O(H) where H is the height of the binary tree.
    * The space complexity is determined by the recursion stack, which in the worst case can go as deep as the height of the tree.
    */
    public int maxDepth(TreeNode root) {
        // Base Case
        if (root == null) return 0;
        
        // Recursion until Base Case is met
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
