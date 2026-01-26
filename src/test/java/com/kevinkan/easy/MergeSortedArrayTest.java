package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for MergeSortedArray class.
 */
public class MergeSortedArrayTest {

    private final MergeSortedArray mergeSortedArray = new MergeSortedArray();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6}),
            Arguments.of(new int[]{1}, 1, new int[]{}, 0, new int[]{1}),
            Arguments.of(new int[]{0}, 0, new int[]{1}, 1, new int[]{1})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMergeSortedArray(int[] nums1, int m, int[] nums2, int n, int[] expected) {
        mergeSortedArray.merge(nums1, m, nums2, n);
        assertArrayEquals(expected, nums1);
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMergeWithSort(int[] nums1, int m, int[] nums2, int n, int[] expected) {
        mergeSortedArray.mergeWithSort(nums1, m, nums2, n);
        assertArrayEquals(expected, nums1);
    }

}
