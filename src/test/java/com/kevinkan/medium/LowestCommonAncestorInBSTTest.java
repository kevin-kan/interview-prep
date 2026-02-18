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
 * Unit tests for <Template> class.
 */
public class LowestCommonAncestorInBSTTest {

    private final LowestCommonAncestorInBST lowestCommonAncestorInBST = new LowestCommonAncestorInBST();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(5, 3, 8, 1, 4, 7, 9, null, 2))),
                        new TreeNode(3), new TreeNode(8), new TreeNode(5)),
            Arguments.of(TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(5, 3, 8, 1, 4, 7, 9, null, 2))),
                        new TreeNode(3), new TreeNode(4), new TreeNode(3))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testTemplate(TreeNode root, TreeNode p, TreeNode q, TreeNode expected) {
        // Compare the values rather than the object's hashcode
        assertEquals(expected.val, lowestCommonAncestorInBST.lowestCommonAncestor(root, p, q).val);
    }

}
