package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for InsertDeleteGetRandomO1 class.
 */
public class InsertDeleteGetRandomO1Test {

    private final InsertDeleteGetRandomO1 randomizedSet = new InsertDeleteGetRandomO1();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new String[] {"insert", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"}, 
                         new int[][] {{1}, {2}, {1}, {2}, {}, {2}, {1}, {}},
                         new boolean[] {true, true, true, false, true, true, true, true})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testRandomizedSet(String[] operations, int[][] values, boolean[] expected) {
        // Test the operations
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            boolean result = false;
            switch (operation) {
                case "insert":
                    result = randomizedSet.insert(values[i][0]);
                    break;
                case "remove":
                    result = randomizedSet.remove(values[i][0]);
                    break;
                case "getRandom":
                    randomizedSet.getRandom();
                    break;
            }
            if (operation.equals("getRandom")) {
                // getRandom doesn't return a value, so we skip checking its result
                continue;
            }
            assertEquals(expected[i], result);
        }
    }
}