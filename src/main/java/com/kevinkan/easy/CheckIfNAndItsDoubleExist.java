package com.kevinkan.easy;

import java.util.HashSet;
import java.util.Set;

/**
* Given an array arr of integers, check if there exist two indices i and j such that :
* i != j (UNIQUE INDICIES)
* 0 <= i, j < arr.length (VALID INDICIES)
* arr[i] == 2 * arr[j] (ONE ELEMENT IS DOUBLE THE OTHER)
*
* Constraints:
* -2^31 <= x <= 2^31 - 1
*/
public class CheckIfNAndItsDoubleExist {

    /**
     * HashSet based solution. (Beats O(N^2) brute force solution)
     * Time Complexity: O(N) where N is the length of the input array.
     * Space Complexity: O(N) as we are using a HashSet to store the elements.
     */
    public boolean checkIfExist(int[] arr) {
        Set<Integer> arrSet = new HashSet<>();
        
        for (int i : arr) {
            // If it's even, check its half as well
            if (i % 2 == 0 && arrSet.contains(i / 2)) return true;
            // Regardless even or odd, check for its double. 
            if (arrSet.contains(i * 2)) return true;
            arrSet.add(i);
        }
        return false;
    }

}
