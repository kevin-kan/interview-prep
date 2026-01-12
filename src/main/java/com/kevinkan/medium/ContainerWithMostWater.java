package com.kevinkan.medium;

/**
* You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
* Find two lines that together with the x-axis form a container, such that the container contains the most water.
* Return the maximum amount of water a container can store.
* Note: You may not slant the container.
* 
* Constraints:
* n == height.length
* 2 <= n <= 10^5
* 0 <= height[i] <= 10^4
*/
public class ContainerWithMostWater {

    /**
     * Two Pointer approach
     * Time Complexity: O(n) where n is the length of the height array.
     * Space Complexity: O(1) since we are using only constant extra space.
     */
    public int maxArea(int[] height) {
        // Edge Case of height.length >= 2 confirmed by Constraints
        int left = 0;
        int right = height.length - 1;
        int mostWater = 0;
        // While there is some width to the "container":
        while (left < right) {
            // Update mostWater with the product of the lower of two heights and the current width of the container
            mostWater = Math.max(mostWater, Math.min(height[left], height[right]) * (right - left));
            // Shift whichever pointer had the lower of the two heights
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return mostWater; 
    }
}
