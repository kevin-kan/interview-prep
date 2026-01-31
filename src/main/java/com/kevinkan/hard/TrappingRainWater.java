package com.kevinkan.hard;

/**
* Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
* 
* Constraints:
* n == height.length
* 1 <= n <= 2 * 10^4
* 0 <= height[i] <= 10^5
*/
public class TrappingRainWater {

    /**
     * Two Pointer Approach
     * The key insight here is: the height of water at any index = minimum of the maximum heights at its left and right - current height (rounded up to 0 if negative).
     * Time Complexity: O(n) where n is the length of the height array.
     * Space Complexity: O(1) as we are using only a constant amount of extra space.
     */
    public int trap(int[] height) {
        int totalWater = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                // By updating leftMax first, the totalWater will never add a negative number 
                // (if the height is above original leftMax, it will update to difference of 0 with this call)
                // No risk when height[left] was already less than leftMax, because leftMax would have no change. 
                leftMax = Math.max(leftMax, height[left]); 
                totalWater += leftMax - height[left];
            }
            else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                totalWater += rightMax - height[right];
            }
        }

        return totalWater;
    }
}
