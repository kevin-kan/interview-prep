package com.kevinkan.hard;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for NumberOfShipsInARectangle class.
 */
public class NumberOfShipsInARectangleTest {

    private NumberOfShipsInARectangle numberOfShipsInARectangle;
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[][] { {1,1}, {2,2}, {3,3} }, new int[] {3,3}, new int[] {1,1}, 3),
            Arguments.of(new int[][] { {0,0}, {0,1}, {1,0}, {1,1} }, new int[] {1,1}, new int[] {0,0}, 4),
            Arguments.of(new int[][] { {5,5}, {6,6}, {7,7} }, new int[] {4,4}, new int[] {0,0}, 0),
            Arguments.of(new int[][] { {10,10}, {20,20}, {30,30}, {40,40}, {50,50} }, new int[] {50,50}, new int[] {10,10}, 5),
            Arguments.of(new int[][] {}, new int[] {1000,1000}, new int[] {0,0}, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testNumberOfShipsInARectangle(int[][] setup, int[] topRight, int[] bottomLeft, int expected) {
        numberOfShipsInARectangle = new NumberOfShipsInARectangle(setup);
        int result = numberOfShipsInARectangle.getNumShips(topRight, bottomLeft);
        assertEquals(expected, result);
    }

}
