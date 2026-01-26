package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for MaximumNestingDepthOfParentheses class.
 */
public class MaximumNestingDepthOfParenthesesTest {

    private final MaximumNestingDepthOfParentheses maximumNestingDepthOfParentheses = new MaximumNestingDepthOfParentheses();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("(1+(2*3)+((8)/4))+1", 3),
            Arguments.of("(1)+((2))+(((3)))", 3),
            Arguments.of("1+(2*3)/(2-1)", 1),
            Arguments.of("1", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMaximumNestingDepthOfParentheses(String s, int expected) {
        assertEquals(expected, maximumNestingDepthOfParentheses.maxDepth(s));
    }

}
