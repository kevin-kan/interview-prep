package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for OrderedStream class.
 */
public class OrderedStreamTest {

    private final OrderedStream orderedStream = new OrderedStream();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new Object[][] {{3, "ccccc"}, {1, "aaaaa"}, {2, "bbbbb"}, {5, "eeeee"}, {4, "ddddd"}},
                         new String[][] { {}, {"aaaaa"}, {"bbbbb","ccccc"}, {}, {"ddddd","eeeee"}})
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testOrderedStream(Object[][] values, String[][] expected) {
        // Test the operations
        for (int i = 0; i < values.length; i++) {
            Object[] val = values[i];
            String result = String.join(",", orderedStream.insert((int)val[0], (String)val[1]));
            String expectedResult = String.join(",", expected[i]);
            assertEquals(expectedResult, result);
        }
    }
}