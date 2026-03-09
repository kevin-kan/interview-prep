package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.List;

/**
* You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
* Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
* Return intervals after the insertion.
* Note that you don't need to modify intervals in-place. You can make a new array and return it.
* 
* Constraints:
* 0 <= intervals.length <= 10^4
* intervals[i].length == 2
* 0 <= starti <= endi <= 10^5
* intervals is sorted by starti in ascending order.
* newInterval.length == 2
* 0 <= start <= end <= 10^5
*/
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> newIntervals = new ArrayList<>();

        int i = 0; // Interval Index Num  
        // Add all the intervals up to the new interval (ends before new interval starts)
        while(i < intervals.length && intervals[i][1] < newInterval[0]) {
            newIntervals.add(intervals[i]);
            i++;
        }

        // Merge all the overlapping intervals:
        // ends after new interval starts and up to 
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        newIntervals.add(newInterval);

        // Add all the intervals after the new interval (starts after new interval ends)
        while (i < intervals.length) {
            newIntervals.add(intervals[i]);
            i++;
        }

        return newIntervals.toArray(new int[newIntervals.size()][]);
    }
}
