package com.kevinkan.medium;

import com.kevinkan.utility.TreeNode;

/**
* Given a binary search tree (BST) where all node values are unique, and two nodes from the tree p and q, return the lowest common ancestor (LCA) of the two nodes.
* The lowest common ancestor between two nodes p and q is the lowest node in a tree T such that both p and q as descendants. The ancestor is allowed to be a descendant of itself.
* 
* Constraints:
* 2 <= The number of nodes in the tree <= 100.
* -100 <= Node.val <= 100
* p != q
* p and q will both exist in the BST.
*/
public class LowestCommonAncestorInBST {

    /**
     * LCA is looking for the first root that falls between the two values. 
     * 
     * Time Complexity: O(h)
     * Space Complexity: O(h)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base cases when you can't find an LCA and hit the end of the tree
        if (root == null || p == null || q == null)
            return null;
        
        // If they're both smaller than the root, try again in the left half of the tree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // If they're both greater than the root, try again in the right half of the tree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // If they're on either side (one greater, one less), return

        return root;
    }
}
