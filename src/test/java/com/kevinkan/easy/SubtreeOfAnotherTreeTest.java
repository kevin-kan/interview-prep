package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.TreeNode;

/**
 * Unit tests for SubtreeOfAnotherTree class.
 */
public class SubtreeOfAnotherTreeTest {

    private final SubtreeOfAnotherTree subtreeOfAnotherTree = new SubtreeOfAnotherTree();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(
                new TreeNode(3, 
                    new TreeNode(4, 
                        new TreeNode(1), 
                        new TreeNode(2, 
                            new TreeNode(0), 
                            null)), 
                    new TreeNode(5)),
                new TreeNode(4, new TreeNode(1), new TreeNode(2)),
                false
            ),
            Arguments.of(
                new TreeNode(3, 
                    new TreeNode(4, 
                        new TreeNode(1), 
                        new TreeNode(2)), 
                    new TreeNode(5)),
                new TreeNode(4, new TreeNode(1), new TreeNode(2)),
                true
            ),
            Arguments.of(
                new TreeNode(1, new TreeNode(1), null),
                new TreeNode(1),
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSubtreeOfAnotherTree(TreeNode p, TreeNode q, boolean expected) {
        assertEquals(expected, subtreeOfAnotherTree.isSubtree(p, q));
    }

}
