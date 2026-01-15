package com.kevinkan.medium;

/**
* There is an integer array nums sorted in ascending order (with distinct values).
* Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
* Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
* You must write an algorithm with O(log n) runtime complexity.
* 
* Constraints:
* 1 <= nums.length <= 5000
* -10^4 <= nums[i] <= 10^4
* All values of nums are unique.
* nums is an ascending array that is possibly rotated.
* -10^4 <= target <= 10^4
*/
public class SearchInRotatedSortedArray {

    /**
     * Modified Binary Search approach
     * Time Complexity: O(log n) where n is the length of the nums array.
     * Space Complexity: O(1) as we are using constant space.
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            // 1. Target is the middle
            if (nums[mid] == target) {
                return mid;
            } 
            // If left side is sorted:
            else if (nums[mid] >= nums[left]) {
                // 2. Target falls in the sorted left side
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                }
                // 3. Target falls in the rotated right side
                else {
                    left = mid + 1;
                }
            } 
            // If the right side is sorted:
            else {
                // 4. Target falls in the sorted right side
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                }
                // 5. Target falls in the rotated left side
                else {
                    right = mid - 1;
                }
            }
        }

        return -1; // Target not found
    }

}
