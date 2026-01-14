package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.TreeNode;

/**
 * Unit tests for MaximumDepthOfBinaryTree class.
 */
public class MaximumDepthOfBinaryTreeTest {

    private final MaximumDepthOfBinaryTree maximumDepthOfBinaryTree = new MaximumDepthOfBinaryTree();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(
                new TreeNode(3, 
                    new TreeNode(9), 
                    new TreeNode(20, new TreeNode(15), new TreeNode(7))
                ),
                3
            ),
            Arguments.of(
                new TreeNode(1, null, new TreeNode(2)),
                2
            ),
            Arguments.of(
                null,
                0
            ),
            Arguments.of(
                new TreeNode(0),
                1
            )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMaxDepth(TreeNode root, int expected) {
        assertEquals(expected, maximumDepthOfBinaryTree.maxDepth(root));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMaxDepthIterative(TreeNode root, int expected) {
        assertEquals(expected, maximumDepthOfBinaryTree.maxDepthIterative(root));
    }

}
