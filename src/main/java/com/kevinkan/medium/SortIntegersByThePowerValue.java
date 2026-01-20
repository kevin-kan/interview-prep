package com.kevinkan.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
* The power of an integer x is defined as the number of steps needed to transform x into 1 using the following steps:
* If x is even, divide it by 2.
* If x is odd, multiply it by 3 and add 1.
* For example, the power of x = 3 is 7 because 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1.
* Given three integers lo, hi and k, return the k-th integer in the range [lo, hi] when sorted by the power value.
* If two integers have the same power value, sort them in ascending order.
* Return the k-th integer after sorting.
* Notice that for any integer x (lo <= x <= hi) it is guaranteed that x will transform into 1 using these steps and the power of x is will fit in a 32-bit signed integer.
* 
* Constraints:
* 1 <= lo <= hi <= 1000
* 1 <= k <= hi - lo + 1
*/
public class SortIntegersByThePowerValue {
    HashMap<Integer, Integer> cache; // Hold a cache of power values that have already been calculated

    /**
     * Priority Queue approach
     * Time Complexity: O(N log N) where N is the number of integers in the range [lo, hi].
     * Space Complexity: O(N) where N is the number of integers in the range [lo, hi].
     */
    public int getKth(int lo, int hi, int k) {
        cache = new HashMap<>();
        Comparator<XPower> comparator = Comparator.comparing(XPower::getPower).thenComparing(XPower::getX);

        PriorityQueue<XPower> maxHeap = new PriorityQueue<XPower>(comparator.reversed());

        for (int i = lo; i <= hi; i++) {
            maxHeap.add(new XPower(i, getPowerValue(i)));

            if (maxHeap.size() > k) { 
                maxHeap.remove(); // keep the maxHeap at the smallest k values by removing the max value
            }
        }

        return maxHeap.remove().x; // return the top x of the maxHeap.
    }

    /**
     * Helper function to get power value
     */
    private int getPowerValue(int x) {
        if (x == 1) return 0;

        if (cache.containsKey(x)) return cache.get(x);

        int power = 1 + ((x % 2 == 0) ? getPowerValue(x / 2) : getPowerValue((x * 3) + 1));
        cache.put(x, power);

        return power;
    }



    class XPower {
        int x;
        int power;

        public XPower(int x, int power) {
            this.x = x;
            this.power = power;
        }

        public int getX() {
            return x;
        }

        public int getPower() {
            return power;
        }
    }


    // Custom Print for debugging
    /*
    private String printMaxHeap(PriorityQueue<XPower> maxHeap) {
        StringBuilder sb = new StringBuilder();
        for (XPower item : maxHeap) {
            sb.append("{" + item.x + ", " + item.power + "},");
        }
        return sb.toString();
    }
    */

}
