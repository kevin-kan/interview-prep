package com.kevinkan.medium;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.Interval;

/**
 * Unit tests for MeetingRoomsII class.
 */
public class MeetingRoomsIITest {

    private final MeetingRoomsII meetingRoomsII = new MeetingRoomsII();
    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(
                new Interval[] {
                    new Interval(0, 30),
                    new Interval(5, 10),
                    new Interval(15, 20)
                },
                2
            ),
            Arguments.of(
                new Interval[] {
                    new Interval(7, 10),
                    new Interval(2, 4)
                },
                1
            ),
            Arguments.of(
                new Interval[] {
                    new Interval(1, 5),
                    new Interval(2, 6),
                    new Interval(3, 7),
                    new Interval(4, 8)
                },
                4
            ),
            Arguments.of(
                new Interval[] {},
                0
            )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testMinMeetingRooms(Interval[] intervals, int expected) {
        assertEquals(expected, meetingRoomsII.minMeetingRooms(Arrays.asList(intervals)));
    }

}
