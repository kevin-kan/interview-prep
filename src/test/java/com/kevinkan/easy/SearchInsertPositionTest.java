package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for SearchInsertPosition class.
 */
public class SearchInsertPositionTest {

    private final SearchInsertPosition searchInsertPosition = new SearchInsertPosition();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {1, 3, 5, 6}, 5, 2 ),
            Arguments.of(new int[] {1, 3, 5, 6}, 2, 1 ),
            Arguments.of(new int[] {1, 3, 5, 6}, 7, 4 )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSearchInsertPosition(int[] nums, int target, int expected) {
        assertEquals(expected, searchInsertPosition.searchInsert(nums, target));
    }

}
