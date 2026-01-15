package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for SearchInRotatedSortedArray class.
 */
public class SearchInRotatedSortedArrayTest {

    private final SearchInRotatedSortedArray searchInRotatedSortedArray = new SearchInRotatedSortedArray();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {4,5,6,7,0,1,2}, 0, 4),
            Arguments.of(new int[] {4,5,6,7,0,1,2}, 3, -1),
            Arguments.of(new int[] {1}, 0, -1),
            Arguments.of(new int[] {1}, 1, 0),
            Arguments.of(new int[] {5,1,3}, 3, 2),
            Arguments.of(new int[] {6,7,8,1,2,3,4,5}, 8, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSearchInRotatedSortedArray(int[] nums, int target, int expected) {
        assertEquals(expected, searchInRotatedSortedArray.search(nums, target));
    }
}
