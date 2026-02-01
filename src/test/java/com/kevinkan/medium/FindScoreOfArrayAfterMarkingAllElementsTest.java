package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for FindScoreOfArrayAfterMarkingAllElements class.
 */
public class FindScoreOfArrayAfterMarkingAllElementsTest {

    private final FindScoreOfArrayAfterMarkingAllElements findScoreOfArrayAfterMarkingAllElements = new FindScoreOfArrayAfterMarkingAllElements();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {2,1,3,4,5,2}, 7L),
            Arguments.of(new int[] {2,3,5,1,3,2}, 5L),
            Arguments.of(new int[] {1,1,1}, 2L),
            Arguments.of(new int[] {5}, 5L),
            Arguments.of(new int[] {4,2,1,3}, 5L)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testFindScoreSorting(int[] nums, long expected) {
        assertEquals(expected, findScoreOfArrayAfterMarkingAllElements.findScoreSorting(nums));
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testFindScoreSlidingWindow(int[] nums, long expected) {
        assertEquals(expected, findScoreOfArrayAfterMarkingAllElements.findScoreLocalMinima(nums));
    }

}
