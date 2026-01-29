package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for RemoveLetterToEqualizeFrequency class.
 */
public class RemoveLetterToEqualizeFrequencyTest {

    private final RemoveLetterToEqualizeFrequency removeLetterToEqualizeFrequency = new RemoveLetterToEqualizeFrequency();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of("abcc", true),
            Arguments.of("aazz", false),
            Arguments.of("abc", true),
            Arguments.of("aabbccddeef", true),
            Arguments.of("aaaa", true)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testEqualFrequency(String s, boolean expected) {
        assertEquals(expected, removeLetterToEqualizeFrequency.equalFrequency(s));
    }

}
