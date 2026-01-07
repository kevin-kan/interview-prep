package com.kevinkan.utility;

import org.junit.jupiter.api.Test;

/*
 * Unit tests for TreeNode class.
 */
public class TreeNodeTest {
    
    @Test
    public void testToString() {
        TreeNode node = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        String expected = "TreeNode{val=1, left=TreeNode{val=2, left=null, right=null}, right=TreeNode{val=3, left=null, right=null}}";
        assert node.toString().equals(expected) : "Expected " + expected + " but got " + node.toString();
    }

}
