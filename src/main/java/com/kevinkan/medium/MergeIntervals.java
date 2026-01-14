package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
* 
* Constraints:
* 1 <= intervals.length <= 10^4
* intervals[i].length == 2
* 0 <= starti <= endi <= 10^4
*/
public class MergeIntervals {
    /**
     * Sorting and Merging approach
     * Time Complexity: O(n log n) where n is the number of intervals.
     * Space Complexity: O(n) in the worst case, when all intervals are non-overlapping.
     */
    public int[][] merge(int[][] intervals) {
        // Sort the intervals by i1, then by i2 if i1 match
        Arrays.sort(intervals, (i1, i2) -> {
            if (i1[0] != i2[0]) {
                return Integer.compare(i1[0], i2[0]);
            } else {
                return Integer.compare(i1[1], i2[1]);
            }
        });
        List<int[]> merged = new ArrayList<>(); 
        int[] prev = intervals[0];
        // Iterate through the intervals
        for (int i = 1; i < intervals.length; i++) {
            // If there is an overlap (new one starts before the previous ends), then merge
            if (intervals[i][0] <= prev[1]) {
                prev[1] = Math.max(intervals[i][1], prev[1]);
            }
            // Otherwise add prev to output arr and update previous to current interval
            else {
                merged.add(prev);
                prev = intervals[i];
            }
        }
        merged.add(prev);

        return merged.toArray(new int[merged.size()][]);
    }

}
