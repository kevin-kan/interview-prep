package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.kevinkan.utility.TreeNode;

/**
* You are given the root node of a binary tree, return the vertical order traversal of its nodes' values.
* For the vertical order traversal, list the nodes column by column starting from the leftmost column and moving to the right.
* Within each column, the nodes should be listed in the order they appear from the top of the tree to the bottom.
* If two nodes are located at the same row and column, the node that appears to the left should come before the other.
* 
* Constraints:
* 0 <= Number of nodes in the tree <= 100
* -100 <= Node.val <= 100
*/
public class BinaryTreeVerticalOrderTraversal {
    /**
     * BFS approach to traverse the tree and group nodes by their vertical columns.
     * Time Complexity: O(N) where N is the number of nodes in the tree.
     * Space Complexity: O(N) for storing the column table and the queue.
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        // Create a map to hold the column index and corresponding list of node values
        Map<Integer, List<Integer>> columnToNodeValues = new HashMap<>();
        int minColumn = 0;

        // Create a queue for BFS traversal
        Queue<NodeColumn> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new NodeColumn(root, 0));
        while (!nodeQueue.isEmpty()) {
            NodeColumn current = nodeQueue.poll();
            // Process currentNode and update columnToNodeValues map
            columnToNodeValues.putIfAbsent(current.column, new ArrayList<>());
            columnToNodeValues.get(current.column).add(current.node.val);

            // Add left and right children to the queue with updated column indices
            if (current.node.left != null) {
                nodeQueue.offer(new NodeColumn(current.node.left, current.column - 1));
                minColumn = Math.min(minColumn, current.column - 1);
            }
            if (current.node.right != null) {
                nodeQueue.offer(new NodeColumn(current.node.right, current.column + 1));
            }
        }

        // Iterate through Map to build the result list
        List<List<Integer>> result = new ArrayList<>();
        while (columnToNodeValues.containsKey(minColumn)) {
            result.add(columnToNodeValues.get(minColumn));
            minColumn++;
        }

        return result;
    }

    class NodeColumn {
        TreeNode node;
        int column;

        public NodeColumn(TreeNode node, int column) {
            this.node = node;
            this.column = column;
        }
    }
}
