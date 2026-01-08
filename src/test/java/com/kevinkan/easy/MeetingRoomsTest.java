package com.kevinkan.easy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for MeetingRooms class.
 */
public class MeetingRoomsTest {

    private final MeetingRooms meetingRooms = new MeetingRooms();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[][]{{0, 30}, {5, 10}, {15, 20}}, false),
            Arguments.of(new int[][]{{7, 10}, {2, 4}}, true),
            Arguments.of(new int[][]{{1, 5}, {6, 10}, {11, 15}}, true),
            Arguments.of(new int[][]{{1, 10}, {2, 3}, {4, 5}}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCanAttendMeetings(int[][] intervals, boolean expected) {
        assertEquals(expected, meetingRooms.canAttendMeetings(intervals));
    }

}
