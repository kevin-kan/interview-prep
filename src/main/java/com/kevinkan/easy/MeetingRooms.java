package com.kevinkan.easy;

import java.util.Arrays;

/**
* Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), determine if a person could add all meetings to their schedule without any conflicts.
* 
* Constraints:
* 1 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
* 
*/
public class MeetingRooms {

    /**
     * Sorting Approach
     * Time Complexity: O(n log n) due to sorting 
     * Space Complexity: O(n) for the sorting
     */
    public boolean canAttendMeetings(int[][] intervals) {
        // Sort intervals by start time then by end time
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(a[1], b[1]);
            }
        });

        for (int i = 1; i < intervals.length; i++) {
            // If the current meeting starts before the previous one ends, there is a conflict
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
