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
        List<int[]> mergedIntervals = new ArrayList<>();
        // Start by sorting the intervals
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        // Add the first interval to the output
        if (intervals.length > 0) {
            mergedIntervals.add(intervals[0]);
        }

        // Iterate through the remainder, updating the previous interval if necessary.
        for (int i = 1; i < intervals.length; i++) {
            int[] prior = mergedIntervals.get(mergedIntervals.size() -1);
            // If this new interval starts after the prior one ends, just add it.
            if (intervals[i][0] > prior[1]) mergedIntervals.add(intervals[i]);
            // Otherwise, update the end of the prior interval to the later of the two. 
            else prior[1] = Math.max(prior[1], intervals[i][1]);
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
    }
}
