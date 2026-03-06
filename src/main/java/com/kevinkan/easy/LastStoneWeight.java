package com.kevinkan.easy;

import java.util.Collections;
import java.util.PriorityQueue;

/**
* You are given an array of integers stones where stones[i] represents the weight of the ith stone.
* We want to run a simulation on the stones as follows:
*
* At each step we choose the two heaviest stones, with weight x and y and smash them togethers
*  If x == y, both stones are destroyed
*  If x < y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
*  Continue the simulation until there is no more than one stone remaining.
* Return the weight of the last remaining stone or return 0 if none remain.
*
* Constraints:
* 1 <= stones.length <= 20
* 1 <= stones[i] <= 100
*/
public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {
            Integer stone1 = maxHeap.poll();
            Integer stone2 = maxHeap.poll();

            if (stone1 > stone2) {
                maxHeap.offer(stone1 - stone2);
            }
            else if (stone2 > stone1) {
                maxHeap.offer(stone2 - stone1);
            }
        }

        return (maxHeap.size() == 1) ? maxHeap.poll() : 0;
        
    }
}
