package com.kevinkan.medium;

import java.util.HashSet;
import java.util.Set;


/**
* Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
* You must write an algorithm that runs in O(n) time.
*
* Constraints:
* 0 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
*/
public class LongestConsecutiveSequence {

    /**
     * HashSet approach
     * Time Complexity: O(n) where n is the number of elements in the array.
     * Space Complexity: O(n) where n is the number of elements in the array.
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        // Add all numbers to the set for O(1) lookups
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // Iterate through each number in the array
        for (int num : numSet) {
            // Only start a new sequence if 'num - 1' is not in the set in order to find the start of a sequence
            if (!numSet.contains(num - 1)) {
                int currentNum = num;

                // Count up the sequence until it no longer exists in the set
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                }

                // Update the longest streak found
                longestStreak = Math.max(longestStreak, currentNum - num + 1);
            }
        }

        return longestStreak;
    }
}
