package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for ValidParentheses class.
 */
public class ValidParenthesesTest {

    private final ValidParentheses validParentheses = new ValidParentheses();

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("()", true),
            Arguments.of("()[]{}", true),
            Arguments.of("(]", false),
            Arguments.of("([])", true),
            Arguments.of("([)]", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testValidParentheses(String s, boolean expected) {
        assertEquals(expected, validParentheses.isValid(s));
    }
}
