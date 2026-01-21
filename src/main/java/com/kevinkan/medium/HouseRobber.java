package com.kevinkan.medium;

/**
* You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
* Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
* 
* Constraints:
* 1 <= nums.length <= 100
* 0 <= nums[i] <= 400
*/
public class HouseRobber {
    
    /**
     * Dynamic Programming Approach
     * Time Complexity: O(n) where n is the number of houses.
     * Space Complexity: O(1) since we are using only a constant amount of extra space.
     */
    public int rob(int[] nums) {
        int prev1 = 0; // Max amount robbed up to the previous house
        int prev2 = 0; // Max amount robbed up to the house before the previous house
        int temp;

        for (int num : nums) {
            temp = prev1;
            // Decide whether to rob the current house or not
            prev1 = Math.max(prev1, prev2 + num);
            prev2 = temp;
        }

        return prev1;
    }

}
