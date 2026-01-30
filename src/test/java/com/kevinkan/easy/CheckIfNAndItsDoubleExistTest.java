package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for CheckIfNAndItsDoubleExist class.
 */
public class CheckIfNAndItsDoubleExistTest {

    private final CheckIfNAndItsDoubleExist checkIfNAndItsDoubleExist = new CheckIfNAndItsDoubleExist();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[] {10,2,5,3}, true),
            Arguments.of(new int[] {7,1,14,11}, true),
            Arguments.of(new int[] {3,1,7,11}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCheckIfNAndItsDoubleExist(int[] arr, boolean expected) {
        assertEquals(expected, checkIfNAndItsDoubleExist.checkIfExist(arr));
    }

}
