package com.kevinkan.hard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.kevinkan.utility.TreeNode;

/**
* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment. 
* Design an algorithm to serialize and deserialize a binary tree. 
* There is no restriction on how your serialization/deserialization algorithm should work. 
* You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
* 
* Constraints:
* The number of nodes in the tree is in the range [0, 10^4].
* -1000 <= Node.val <= 1000
*/
public class Codec { // Serialize and Deserialize Binary Tree


    /**
     * Encodes a tree to a single string
     * @param root root of the Tree
     * @return comma-separated LevelOrder traversal of the tree. ie. "1,2,3"
     */
    public static String serialize_levelOrder(TreeNode root) {
        Stack<String> levelOrder = new Stack<>();
        // Fast Return for empty tree
        if (root == null) return "";

        // Use Queue instead of Deque to allow null values. 
        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);
        while (!bfsQueue.isEmpty()) {
            // Pop the current node for processing
            TreeNode curr = bfsQueue.poll();
            if (curr != null) {
                // Add the current node value to the output stack
                levelOrder.add(String.valueOf(curr.val));
                // Add the child nodes to the processing queue 
                bfsQueue.offer(curr.left);
                bfsQueue.offer(curr.right);
            } else {
                // Add "null" to the output stack if the node is null.
                levelOrder.add("null");
            }
        }

        // Clear any trailing "null" values
        while(levelOrder.peek().equals("null")) {
            levelOrder.pop();
        }
        // Join the stack with a comma and return
        return String.join(",", levelOrder);
    }


    /**
     * Decodes your encoded data to tree.
     * @param data "1,2,3"
     * @return root of the Tree
     */
    public static TreeNode deserialize_levelOrder(String data) {
        // Quick return if we've got no data to deserialize
        if (data.isBlank()) return null;
        
        // Break the string into individual values
        String[] vals = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        int valIndex = 1;

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.offer(root);
        while (!bfsQueue.isEmpty()) {
            // Pop the current node for processing
            TreeNode curr = bfsQueue.poll();
            if (valIndex < vals.length && !vals[valIndex].equals("null")) {
                // Create a new node for the non-null object
                curr.left = new TreeNode(Integer.parseInt(vals[valIndex]));
                // Add it to the queue
                bfsQueue.add(curr.left);
            }
            valIndex++;
            if (valIndex < vals.length && !vals[valIndex].equals("null")) {
                // Create a new node for the non-null object
                curr.right = new TreeNode(Integer.parseInt(vals[valIndex]));
                // Add it to the queue
                bfsQueue.add(curr.right);
            }
            valIndex++;
        }
        return root;
    }

    /**
     * Encodes a tree to a single string
     * @param root root of the Tree
     * @return comma-separated PreOrder traversal of the tree. ie. "1,2,3"
     */
    public static String serialize_preOrder(TreeNode root) {
        Stack<String> preOrder = new Stack<>();
        // Fast Return for empty tree
        if (root == null) return "";
        serialize_preOrder_helper(root, preOrder);

        // Clear any trailing "null" values
        while(preOrder.peek().equals("null")) {
            preOrder.pop();
        }

        return String.join(",", preOrder);
    }

    private static void serialize_preOrder_helper(TreeNode node, Stack<String> stack) {
        if (node != null) {
            stack.add(String.valueOf(node.val));
            serialize_preOrder_helper(node.left, stack);
            serialize_preOrder_helper(node.right, stack);
        }
        else {
            stack.add("null");
        }
    }


    /**
     * Decodes your encoded data to tree.
     * @param data "1,2,3"
     * @return root of the Tree
     */
    public static TreeNode deserialize_preOrder(String data) {
        // Quick return if we've got no data to deserialize
        if (data.isBlank()) return null;
        
        // Break the string into individual values
        String[] vals = data.split(",");
        
        return deserialize_preOrder_helper(vals, new int[]{0});
    }

    // We need an int[] index in order to save and pass it properly.
    private static TreeNode deserialize_preOrder_helper(String[] vals, int[] index) {
        // Base case: check if we've reached the end of the list
        if (index[0] >= vals.length) return null;

        // Base case 2: if the value is "null" return null.
        String val = vals[index[0]++];
        if (val.equals("null")) return null;

        // Add this node and recurse
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserialize_preOrder_helper(vals, index);
        node.right = deserialize_preOrder_helper(vals, index);
        
        return node;
    }
}
