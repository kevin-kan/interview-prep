package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.TreeNode;

/**
 * Unit tests for SameTree class.
 */
public class SameTreeTest {

    private final SameTree sameTree = new SameTree();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(
                new TreeNode(1, new TreeNode(2), new TreeNode(3)),
                new TreeNode(1, new TreeNode(2), new TreeNode(3)),
                true
            ),
            Arguments.of(
                new TreeNode(1, new TreeNode(2), null),
                new TreeNode(1, null, new TreeNode(2)),
                false
            ),
            Arguments.of(
                new TreeNode(1, new TreeNode(2), new TreeNode(1)),
                new TreeNode(1, new TreeNode(1), new TreeNode(2)),
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSameTree(TreeNode p, TreeNode q, boolean expected) {
        assertEquals(expected, sameTree.isSameTree(p, q));
    }

}
