package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for NumberOfIslands class.
 */
public class NumberOfIslandsTest {

    private final NumberOfIslands numberOfIslands = new NumberOfIslands();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new char[][] { {'1','1','1','1','0'}, 
                                        {'1','1','0','1','0'}, 
                                        {'1','1','0','0','0'}, 
                                        {'0','0','0','0','0'}}, 1),
            Arguments.of(new char[][] { {'1','1','0','0','0'}, 
                                        {'1','1','0','0','0'}, 
                                        {'0','0','1','0','0'}, 
                                        {'0','0','0','1','1'}}, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testNumberOfIslands(char[][] grid, int expected) {
        assertEquals(expected, numberOfIslands.numIslands(grid));
    }
}
