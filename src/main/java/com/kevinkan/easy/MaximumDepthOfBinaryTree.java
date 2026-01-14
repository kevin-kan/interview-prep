package com.kevinkan.easy;

import java.util.LinkedList;
import java.util.Queue;

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

    /*
    * Iterative Approach using BFS
    * Time Complexity: O(N) where N is the number of nodes in the binary tree.
    * We visit each node exactly once to compute the maximum depth.
    * Space Complexity: O(H) where H is the height of the binary tree.
    * The space complexity is determined by the stack used for traversal, which in the worst case can go as deep as the height of the tree.
    */
    public int maxDepthIterative(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            depth++;
        }
        return depth;
    }
}
