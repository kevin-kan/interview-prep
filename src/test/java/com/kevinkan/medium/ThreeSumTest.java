package com.kevinkan.medium;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for ThreeSum class.
 */
public class ThreeSumTest {

    private final ThreeSum threeSum = new ThreeSum();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {-1,0,1,2,-1,-4}, 
                         List.of(List.of(-1, -1, 2), List.of(-1, 0, 1))),
            Arguments.of(new int[] {0,1,1}, 
                         List.of()),
            Arguments.of(new int[] {0,0,0}, 
                         List.of(List.of(0, 0, 0))),
            Arguments.of(new int[] {-2,0,1,1,2}, 
                         List.of(List.of(-2, 0, 2), List.of(-2, 1, 1))),
            Arguments.of(new int[] {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}, 
                         List.of(List.of(-4,-2,6), List.of(-4,0,4), List.of(-4,1,3), 
                                  List.of(-4,2,2), List.of(-2,-2,4), List.of(-2,0,2)))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testThreeSum(int[] nums, List<List<Integer>> expected) {
        assertEquals(expected, threeSum.threeSum(nums));
    }
}
