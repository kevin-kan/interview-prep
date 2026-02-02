package com.kevinkan.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
* Given an integer array nums and an integer k, return the kth largest element in the array.
* Note that it is the kth largest element in the sorted order, not the kth distinct element.
* 
* Constraints:
* -1000 <= a, b <= 1000
*/
public class KthLargestElementInAnArray {

    /**
     * Sorting Approach
     * Time Complexity: O(nlogn)
     * Space Complexity: O(logn) - space needed for Quicksort
     */
    public int findKthLargestSorting(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * Follow up: Can you solve it without sorting?
     * Time Complexity: O(nlogk)
     * Space Complexity: O(1)
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.poll();
    }

}
