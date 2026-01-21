package com.kevinkan.medium;

import java.util.Arrays;
import java.util.List;

import com.kevinkan.utility.Interval;

/**
* Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of days/rooms required to schedule all meetings without conflicts.
* Note: If a meeting ends at time X, another meeting starting at time X is allowed.
* 
* Constraints:
* 0 <= intervals.length <= 500
* 0 <= start_i < end_i <= 10^6
* 
*/
public class MeetingRoomsII {

    /**
     * Sorting Approach
     * Time Complexity: O(n log n) due to sorting 
     * Space Complexity: O(n) for the sorting
     */
    public int minMeetingRooms(List<Interval> intervals) {
        // Base Case: any empty list requires no rooms
        if (intervals == null || intervals.isEmpty()) {
            return 0;
        }

        // Create 2 arrays of start times and end times
        // Fill them with the respective times from the intervals
        // Sort both arrays O(n log n)
        int n = intervals.size();
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            startTimes[i] = intervals.get(i).start;
            endTimes[i] = intervals.get(i).end;
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        // Iterate through both arrays to find the max number of overlapping intervals
        int startPointer = 0, endPointer = 0;
        int currentlyUsedRooms = 0, maxUsedRooms = 0;
        while (startPointer < n) {
            if (startTimes[startPointer] < endTimes[endPointer]) {
                startPointer++;
                currentlyUsedRooms++;
                maxUsedRooms = Math.max(maxUsedRooms, currentlyUsedRooms);
            } else {
                endPointer++;
                currentlyUsedRooms--;
            }
        }
        return maxUsedRooms;
    }

}
