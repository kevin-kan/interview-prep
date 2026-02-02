package com.kevinkan.easy;

import java.util.Arrays;

/**
* You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
* Merge nums1 and nums2 into a single array sorted in non-decreasing order.
* The final sorted array should not be returned by the function, but instead be stored inside the array nums1. 
* To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
* 
* Constraints:
* nums1.length == m + n
* nums2.length == n
* 0 <= m, n <= 200
* 1 <= m + n <= 200
* -10^9 <= nums1[i], nums2[j] <= 10^9
*/
public class MergeSortedArray {
    /**
     * Two Pointers approach
     * Time Complexity: O(m + n) where m and n are the lengths of nums1 and nums2 respectively.
     * Space Complexity: O(1) since we are modifying nums1 in place.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Define 3 pointers to track ends of nums1, nums2 and merged array
        int p1 = m - 1; 
        int p2 = n - 1; 
        int p = m + n - 1;

        // Merge, filling from the end of nums1
        while (p2 >= 0) {
            if (p1 >= 0  && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
    }

    /**
     * Java Arrays.sort approach
     * Time Complexity: O((m + n) log(m + n)) due to the sorting step.
     * Space Complexity: O(1) if we ignore the space required for sorting. O(log(m +n)) with QuickSort space
     */
    public void mergeWithSort(int[] nums1, int m, int[] nums2, int n) {
        // Copy elements from nums2 to nums1
        System.arraycopy(nums2, 0, nums1, m, n);
        // Sort the merged array
        Arrays.sort(nums1);
    }
}
