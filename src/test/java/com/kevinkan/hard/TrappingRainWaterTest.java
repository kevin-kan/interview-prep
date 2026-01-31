package com.kevinkan.hard;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for TrappingRainWater class.
 */
public class TrappingRainWaterTest {

    private TrappingRainWater trappingRainWater= new TrappingRainWater();;
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}, 6),
            Arguments.of(new int[] {4,2,0,3,2,5}, 9),
            Arguments.of(new int[] {1,0,2,1,0,1,3}, 5),
            Arguments.of(new int[] {5,4,1,2}, 1),
            Arguments.of(new int[] {2,0,2}, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testTrappingRainWater(int[] height, int expected) {
        int result = trappingRainWater.trap(height);
        assertEquals(expected, result);
    }

}
