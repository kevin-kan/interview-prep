package com.kevinkan.easy;

/**
* Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
* You must write an algorithm with O(log n) runtime complexity.
*
* Constraints:
* 1 <= nums.length <= 10^4
* -10^4 <= nums[i] <= 10^4
* nums contains distinct values sorted in ascending order.
* -10^4 <= target <= 10^4
*/
public class SearchInsertPosition {

    /**
     * Binary Search
     * Time Complexity: O(logn)
     * Space Complexity: O(1)
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle;
        int middleVal;

        while (left <= right) {
            middle = (left + right) / 2;
            middleVal = nums[middle];
            if (middleVal == target) {
                return middle; 
            }
            else if (middleVal < target) {
                left = middle + 1;
            } 
            else {
                right = middle - 1;
            }
        }

        // Standard Binary Search except for this last part:
        // return one of the pointers to get where target should've been
        return left;
    }

}
