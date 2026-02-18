package com.kevinkan.utility;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for <Template> class.
 */
public class TemplateTest {

    private final Template template = new Template();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(true, true),
            Arguments.of(false, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testTemplate(boolean input, boolean expected) {
        assertEquals(expected, template.templateMethod(input));
    }

}
