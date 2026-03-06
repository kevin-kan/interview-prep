package com.kevinkan.easy;

import java.util.PriorityQueue;

/**
* Design a class to find the kth largest integer in a stream of values, including duplicates. 
* E.g. the 2nd largest from [1, 2, 3, 3] is 3. The stream is not necessarily sorted.
* 
* Implement the following methods:
* constructor(int k, int[] nums) Initializes the object given an integer k and the stream of integers nums.
* int add(int val) Adds the integer val to the stream and returns the kth largest integer in the stream.
*
* Constraints:
* 1 <= k <= 1000
* 0 <= nums.length <= 1000
* -1000 <= nums[i] <= 1000
* -1000 <= val <= 1000
* There will always be at least k integers in the stream when you search for the kth integer.
*/
public class KthLargestElementInAStream {
    PriorityQueue<Integer> minHeap;
    int cap;

    public KthLargestElementInAStream(int k, int[] nums) {
        this.minHeap = new PriorityQueue<>();
        this.cap = k;
        
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > cap) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}
