package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for TwoCityScheduling class.
 */
public class TwoCitySchedulingTest {

    private final TwoCityScheduling twoCityScheduling = new TwoCityScheduling();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[][] { {10,20},{30,200},{400,50},{30,20} }, 110),
            Arguments.of(new int[][] { {259,770},{448,54},{926,667},{184,139},{840,118},{577,469} }, 1859)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testTwoCityScheduling(int[][] costs, int expected) {
        assertEquals(expected, twoCityScheduling.twoCitySchedCost(costs));
    }

}
