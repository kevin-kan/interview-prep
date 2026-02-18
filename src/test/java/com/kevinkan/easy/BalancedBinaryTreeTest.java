package com.kevinkan.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.TreeNode;

/**
 * Unit tests for BalancedBinaryTree class.
 */
public class BalancedBinaryTreeTest {

    private final BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(
                TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, null, null, 4, 4))),
                false
            ),
            Arguments.of(
                TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(2, 1, 3))),
                true
            ),
            Arguments.of(
                null,
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testBalancedBinaryTree(TreeNode root, boolean expected) {
        assertEquals(expected, balancedBinaryTree.isBalanced(root));
    }
    
}
