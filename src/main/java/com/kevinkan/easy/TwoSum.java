package com.kevinkan.easy;

import java.util.HashMap;
import java.util.Map;

/**
* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
* You may assume that each input would have exactly one solution, and you may not use the same element twice.
* You can return the answer in any order.
* 
* Constraints:
* 2 <= nums.length <= 10^4
* -10^9 <= nums[i] <= 10^9
* -10^9 <= target <= 10^9
* Only one valid answer exists.
* 
*/
public class TwoSum {

    /**
     * Brute Force Approach 
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int[] twoSum_bruteForce(int[] nums, int target) {
        if (nums.length < 2) {
            return new int[] {};
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }

    /**
     * Hash Map Approach
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] twoSum_hashMap(int[] nums, int target) {
        if (nums.length < 2) {
            return new int[] {};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
