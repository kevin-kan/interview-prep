package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.TreeNode;

/**
 * Unit tests for InvertBinaryTree class.
 */
public class InvertBinaryTreeTest {

    private final InvertBinaryTree invertBinaryTree = new InvertBinaryTree();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(
                new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7, new TreeNode(6), new TreeNode(9))),
                new TreeNode(4, new TreeNode(7, new TreeNode(9), new TreeNode(6)), new TreeNode(2, new TreeNode(3), new TreeNode(1)))
            ),
            Arguments.of(
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(2, new TreeNode(3), new TreeNode(1))
            ),
            Arguments.of(
                null,
                null
            )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testInvertBinaryTree(TreeNode root, TreeNode expected) {
        assertEquals(expected, invertBinaryTree.invertTree(root));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testInvertBinaryTreeBFS(TreeNode root, TreeNode expected) {
        assertEquals(expected, invertBinaryTree.invertTreeBFS(root));
    }
    
}
