package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for ContainerWithMostWater class.
 */
public class ContainerWithMostWaterTest {

    private final ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {1,8,6,2,5,4,8,3,7}, 49),
            Arguments.of(new int[] {1,1}, 1),
            Arguments.of(new int[] {4,3,2,1,4}, 16),
            Arguments.of(new int[] {1,2,1}, 2),
            Arguments.of(new int[] {1,3,2,5,25,24,5}, 24)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMaxArea(int[] height, int expected) {
        assertEquals(expected, containerWithMostWater.maxArea(height));
    }
}
