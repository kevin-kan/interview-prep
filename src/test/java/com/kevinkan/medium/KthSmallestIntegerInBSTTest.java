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
 * Unit tests for KthSmallestIntegerInBST class.
 */
public class KthSmallestIntegerInBSTTest {

    private final KthSmallestIntegerInBST kthSmallestIntegerInBST = new KthSmallestIntegerInBST();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(3,1,4,null,2))), 1, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testKthSmallestIntegerInBST(TreeNode root, int k, int expected) {
        assertEquals(expected, kthSmallestIntegerInBST.kthSmallest(root, k));
    }

}
