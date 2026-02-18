package com.kevinkan.utility;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;
        TreeNode other = (TreeNode) o;
        return val == other.val
                && Objects.equals(left, other.left)
                && Objects.equals(right, other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    /**
     * Static factory to build a tree from a level-order ArrayList.
     * Example: [1, 2, 3, 4, 5] creates a tree like this:
     *    1
     *  2   3
     * 4 5
     */
    public static TreeNode fromLevelOrder(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) return null;

        TreeNode root = new TreeNode(nums.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < nums.size()) {
            TreeNode current = queue.poll();

            // Assign left child
            if (i < nums.size() && nums.get(i) != null) {
                current.left = new TreeNode(nums.get(i));
                queue.add(current.left);
            }
            i++;

            // Assign right child
            if (i < nums.size() && nums.get(i) != null) {
                current.right = new TreeNode(nums.get(i));
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    /**
     * Static factory to build a tree from a pre-order ArrayList.
     * Example: [1, 2, 3, 4, 5] creates a tree like this:
     *    1
     *  2   5
     * 3 4
     */
    public static TreeNode fromPreOrder(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) return null;

        return fromPreOrderHelper(nums, new int[]{0});
    }

    private static TreeNode fromPreOrderHelper(List<Integer> nums, int[] index) {
        // Base case: check if we've reached the end of the list
        if (index[0] >= nums.size()) {
            return null;
        }

        // Get the current value and increment the shared index
        Integer currentVal = nums.get(index[0]++);

        // If the current value is null, this branch is empty
        if (currentVal == null) {
            return null;
        }

        // Create the root for this subtree
        TreeNode root = new TreeNode(currentVal);

        // Pre-order logic: Root -> Left -> Right
        root.left = fromPreOrderHelper(nums, index);
        root.right = fromPreOrderHelper(nums, index);

        return root;
    }


}
