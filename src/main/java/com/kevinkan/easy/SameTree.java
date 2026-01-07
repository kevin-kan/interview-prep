package com.kevinkan.easy;

import com.kevinkan.utility.TreeNode;

/**
* Given the roots of two binary trees p and q, write a function to check if they are the same or not.
* Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
*
* Constraints:
* The number of nodes in both trees is in the range [0, 100].
* -10^4 <= Node.val <= 10^4
*/
public class SameTree {

    /*
    * Time Complexity: O(min(N, M)) where N and M are the number of nodes in trees p and q respectively.
    * We traverse both trees simultaneously, and in the worst case, we may need to visit all nodes of the smaller tree.
    * Space Complexity: O(min(H1, H2)) where H1 and H2 are the heights of trees p and q respectively.
    * The space complexity is determined by the recursion stack, which in the worst case can go as deep as the height of the smaller tree.
    */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base Cases
        if (p == null && q == null) return true;
        if (p == null ^ q == null) return false;

        // Recursion until base case are met
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
