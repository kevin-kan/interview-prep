package com.kevinkan.easy;

import java.util.LinkedList;
import java.util.Queue;

import com.kevinkan.utility.TreeNode;

/**
* Given the root of a binary tree, invert the tree, and return its root.
*
* Constraints:
* The number of nodes in the tree is in the range [0, 100].
* -100 <= Node.val <= 100
*/
public class InvertBinaryTree {

    /*
    * Time Complexity: O(N) where N is the number of nodes in the binary tree.
    * We visit each node exactly once to invert the tree.
    * Space Complexity: O(H) where H is the height of the binary tree.
    * The space complexity is determined by the recursion stack, which in the worst case can go as deep as the height of the tree.
    */
    public TreeNode invertTree(TreeNode root) {
        // Base Case: root is null
        if (root == null) return root;

        // Recursively perform inversion with DepthFirstSearch (DFS) traversal
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);

        return root;
    }

    /**
     * BFS Iterative Approach:
     * Time Complexity: O(N) where N is the number of nodes in the binary tree.
     * We visit each node exactly once to invert the tree.
     * Space Complexity: O(W) where W is the maximum width of the binary tree.
     * The space complexity is determined by the queue used for BFS traversal, which in the worst case can hold all nodes at the widest level of the tree.
     * Same can be implemented using Deque/Stack as well for an iterative DFS approach.
     */
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) return root;

        // Create a queue for BFS traversal and start it off with the root
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // Swap left and right children
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            // Add children to the queue for further processing
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return root;
    }
}
