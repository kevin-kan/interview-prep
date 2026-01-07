package com.kevinkan.easy;

import com.kevinkan.utility.TreeNode;

/**
* Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
* A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
*
* Constraints:
* The number of nodes in the root tree is in the range [1, 2000].
* The number of nodes in the subRoot tree is in the range [1, 1000].
* -10^4 <= Node.val <= 10^4
* -10^4 <= subRoot.val <= 10^4
*/
public class SubtreeOfAnotherTree {

    /*
    * Time Complexity: O(N * M) in the worst case, where N is the number of nodes in the root tree and M is the number of nodes in the subRoot tree.
    * In the worst case, for each of the N nodes in the root tree, we may need to compare it with all M nodes in the subRoot tree.
    * Space Complexity: O(H1 + H2) where H1 and H2 are the heights of the root and subRoot trees respectively.
    * The space complexity is determined by the recursion stack, which in the worst case can go as deep as the height of both trees.
    */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base Cases
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;

        // Recursively check left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // Copied from SameTree class
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base Cases
        if (p == null && q == null) return true;
        if (p == null ^ q == null) return false;

        // Recursion until base case are met
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
