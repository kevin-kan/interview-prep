package com.kevinkan.medium;

/**
* You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
* All the houses are arranged in a circle, meaning the first house is adjacent to the last house.
* Meanwhile, adjacent houses have security systems connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
* Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
* 
* Constraints:
* 1 <= nums.length <= 100
* 0 <= nums[i] <= 1000
*/
public class HouseRobberII {
    
    /**
     * Dynamic Programming Approach
     * Time Complexity: O(n) where n is the number of houses.
     * Space Complexity: O(1) since we are using only a constant amount of extra space.
     */
    public int rob(int[] nums) {
        // Handle edge case where there is only one house
        if (nums.length == 1) {
            return nums[0];
        }
        // Rob houses from index 0 to n-2
        int robFirst = robMax(nums, 0, nums.length - 2);
        // Rob houses from index 1 to n-1
        int robLast = robMax(nums, 1, nums.length - 1);
        
        return Math.max(robFirst, robLast);
    }

    public int robMax(int[] nums, int start, int end) {
        int prev1 = 0; // Max amount robbed up to the previous house
        int prev2 = 0; // Max amount robbed up to the house before the previous house
        int temp;

        for (int i = start; i <= end; i++) {
            temp = prev1;
            // Decide whether to rob the current house or not
            prev1 = Math.max(prev1, prev2 + nums[i]);
            prev2 = temp;
        }

        return prev1;
    }

}
