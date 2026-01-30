package com.kevinkan.medium;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for CountUnhappyFriends class.
 */
public class CountUnhappyFriendsTest {

    private final CountUnhappyFriends countUnhappyFriends = new CountUnhappyFriends();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(4, new int[][] { {1,2,3}, {3,2,0}, {3,1,0}, {1,2,0} }, new int[][] { {0,1}, {2,3} }, 2),
            Arguments.of(2, new int[][] { {1}, {0} }, new int[][] { {0,1} }, 0),
            Arguments.of(4, new int[][] { {1,3,2}, {2,3,0}, {1,3,0}, {0,2,1} }, new int[][] { {1,3}, {0,2} }, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCountUnhappyFriends(int n, int[][] preferences, int[][] pairs, int expected) {
        assertEquals(expected, countUnhappyFriends.unhappyFriends(n, preferences, pairs));
    }

}
