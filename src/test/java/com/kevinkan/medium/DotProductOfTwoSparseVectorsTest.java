package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for DotProductOfTwoSparseVectors class.
 */
public class DotProductOfTwoSparseVectorsTest {

    private final DotProductOfTwoSparseVectors dotProductOfTwoSparseVectors = new DotProductOfTwoSparseVectors(new int[] {1, 0, 0, 2, 3});
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {0, 3, 0, 4, 0}, 8),
            Arguments.of(new int[] {0, 0, 0, 0, 0}, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testDotProductOfTwoSparseVectors(int[] nums, int expected) {
        DotProductOfTwoSparseVectors vec = new DotProductOfTwoSparseVectors(nums);
        assertEquals(expected, dotProductOfTwoSparseVectors.dotProduct(vec));
    }

}
