package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for ContainsDuplicate class.
 */
public class ContainsDuplicateTest {

    private final ContainsDuplicate containsDuplicate = new ContainsDuplicate();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4, 5}, false),
            Arguments.of(new int[]{1, 2, 3, 4, 5, 1}, true),
            Arguments.of(new int[]{}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testContainsDuplicate(int[] nums, boolean expected) {
        assertEquals(expected, containsDuplicate.containsDuplicate(nums));
    }

}
