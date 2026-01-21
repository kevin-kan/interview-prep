package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for SearchA2DMatrix class.
 */
public class SearchA2DMatrixTest {

    private final SearchA2DMatrix searchA2DMatrix = new SearchA2DMatrix();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[][] { {1,3,5,7}, 
                                       {10,11,16,20}, 
                                       {23,30,34,60} }, 3, true),
            Arguments.of(new int[][] { {1,3,5,7}, 
                                       {10,11,16,20}, 
                                       {23,30,34,60} }, 13, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testSearchMatrix(int[][] matrix, int target, boolean expected) {
        assertEquals(expected, searchA2DMatrix.searchMatrix(matrix, target));
    }
}
