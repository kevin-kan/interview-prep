package com.kevinkan.medium;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.TreeNode;

/**
 * Unit tests for BinaryTreeVerticalOrderTraversal class.
 */
public class BinaryTreeVerticalOrderTraversalTest {

    private final BinaryTreeVerticalOrderTraversal verticalOrderTraversal = new BinaryTreeVerticalOrderTraversal();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(
                new TreeNode(3,
                    new TreeNode(9),
                    new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)
                    )
                ),
                List.of(
                    List.of(9),
                    List.of(3, 15),
                    List.of(20),
                    List.of(7)
                )
            ),
            Arguments.of(
                new TreeNode(1,
                    new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)
                    ),
                    new TreeNode(3,
                        new TreeNode(6),
                        new TreeNode(7)
                    )
                ),
                List.of(
                    List.of(4),
                    List.of(2),
                    List.of(1, 5, 6),
                    List.of(3),
                    List.of(7)
                )
            ),
            Arguments.of(
                null,
                List.of()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testBinaryTreeVerticalOrderTraversal(TreeNode root, List<List<Integer>> expected) {
        assertEquals(expected, verticalOrderTraversal.verticalOrder(root));
    }
}
