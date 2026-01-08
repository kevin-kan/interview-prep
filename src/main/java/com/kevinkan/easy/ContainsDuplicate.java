package com.kevinkan.easy;

import java.util.HashSet;
import java.util.Set;

/**
* Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
* 
* Constraints:
* 1 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
* 
*/
public class ContainsDuplicate {

    /**
     * Hash Set Approach
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
