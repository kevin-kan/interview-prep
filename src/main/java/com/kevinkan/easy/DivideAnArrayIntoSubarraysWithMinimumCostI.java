package com.kevinkan.easy;

import java.util.Collections;
import java.util.PriorityQueue;

/**
* You are given an array of integers nums of length n.
* The cost of an array is the value of its first element. For example, the cost of [1,2,3] is 1 while the cost of [3,4,1] is 3.
* You need to divide nums into 3 disjoint contiguous subarrays.
* Return the minimum possible sum of the cost of these subarrays.
*
* Constraints:
* 3 <= n <= 50
* 1 <= nums[i] <= 50
*/
public class DivideAnArrayIntoSubarraysWithMinimumCostI {

    /**
     * Main goal is to add the first number in the array + the two lowest numbers of the remainder of the array
     * Heap approach to keep track of the two lowest numbers
     * Time Complexity: O(N log 2) ~ O(N)
     * Space Complexity: O(1) as the size of the heap is capped at 2
     */
    public int minimumCostHeapApproach(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        // PriorityQueue (Heap) is a MinHeap by default - we need to provide it a reverse order comparator to make it a MaxHeap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 1; i < nums.length; i++) {
            maxHeap.offer(nums[i]);
            if (maxHeap.size() > 2) maxHeap.poll();
        }

        return nums[0] + maxHeap.poll() + maxHeap.poll();
    }

    /**
     * Main goal is to add the first number in the array + the two lowest numbers of the remainder of the array
     * Array approach to track the 2 smallest numbers
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public int minimumCostArrayApproach(int[] nums) {
        // min1 will be held as the absolute smallest, and min2 will be held as the 2nd smallest
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }
        }
        return nums[0] + min1 + min2;
    }

}
