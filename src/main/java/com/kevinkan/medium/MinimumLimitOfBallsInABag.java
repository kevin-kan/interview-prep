package com.kevinkan.medium;

import java.util.Arrays;


/**
* You are given an integer array nums representing the number of balls in each bag. You are also given an integer maxOperations.
* You can perform the following operation at most maxOperations times: Take any bag of balls and divide it into two new bags with a positive number of balls.
* For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
* Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after performing the operations.
* Return the minimum possible penalty after performing the operations.
*
* Constraints:
* 0 <= nums.length <= 10^5
* -10^9 <= nums[i] <= 10^9
*/
public class MinimumLimitOfBallsInABag {

    /**
     * Binary Search Approach
     * Time: O(N log M) where N is the number of bags and M is the maximum number of balls in a bag
     * Space: O(1)
     */
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1;
        int right = Arrays.stream(nums).max().getAsInt();

        // Binary search to find the minimum possible penalty
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canDivide(nums, maxOperations, mid)) {
                // If possible to divide with this penalty, try for a smaller penalty
                right = mid;
            } else {
                // If not possible, increase the penalty
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * Helper method to check if we can divide the bags such that no bag has more than maxSize balls
     */
    public boolean canDivide(int[] nums, int maxOperations, int maxSize) {
        int necessaryOperations = 0;
        for (int num : nums) {
            if (num > maxSize) {
                // Calculate the number of operations needed to reduce this bag to maxSize
                necessaryOperations += (num - 1) / maxSize;
            }
            if (necessaryOperations > maxOperations) {
                return false;
            }
        }
        return true;
    }
}
