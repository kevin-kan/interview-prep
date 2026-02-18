package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.TreeNode;

/**
 * Unit tests for ValidBinarySearchTree class.
 */
public class ValidateBinarySearchTreeTest {

    private final ValidateBinarySearchTree validBinarySearchTree = new ValidateBinarySearchTree();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(2, 1, 3))), true), // standard true case
            Arguments.of(TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(1, 2, 3))), false), // standard false case
            Arguments.of(TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(2, 2, 2))), false), // edge case
            Arguments.of(new TreeNode(1), true), // edge case
            Arguments.of(null, true), // null case
            Arguments.of(new TreeNode(Integer.MAX_VALUE), true)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testValidBinarySearchTree(TreeNode input, boolean expected) {
        assertEquals(expected, validBinarySearchTree.isValidBST(input));
    }

}
