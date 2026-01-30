package com.kevinkan.medium;

import java.util.HashMap;
import java.util.Map;

/**
* Given two sparse vectors, compute their dot product.
* Implement the SparseVector class:
* - SparseVector(nums) Initializes the object with the vector nums
* - dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
* A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector instances.
* The dot product is the sum of the products of the corresponding entries of the two sequences of numbers.
* 
* Constraints:
* 1 <= nums1.length, nums2.length <= 10^5
* nums1.length == nums2.length
* -100 <= nums1[i], nums2[i] <= 100
*/
public class DotProductOfTwoSparseVectors { // SparseVector
    /**
     * Brute Force Solution
     */
    // private final int[] nums;

    // public DotProductOfTwoSparseVectors(int[] nums) {
    //     this.nums = nums;
    // }

    // /**
    //  * Time Complexity: O(n) where n is the length of the vectors.
    //  * Space Complexity: O(1) as we are using only a constant amount of extra space.
    //  */
    // public int dotProduct_BruteForce(DotProductOfTwoSparseVectors vec) {
    //     int result = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         result += this.nums[i] * vec.nums[i];
    //     }
    //     return result;
    // }

    /**
     * Optimized Solution for *Sparse* Vectors using HashMap
     */
    Map<Integer, Integer> indexValueMap;
    public DotProductOfTwoSparseVectors(int[] nums) {
        this.indexValueMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                this.indexValueMap.put(i, nums[i]);
            }
        }
    }

    /**
     * Time Complexity: O(min(m, n)) where m and n are the number of non-zero elements in the two vectors.
     * Space Complexity: O(m + n) for storing the non-zero elements in the hash maps.
     */
    public int dotProduct(DotProductOfTwoSparseVectors vec) {
        // Ensure we iterate over the smaller map for efficiency
        if (vec.indexValueMap.size() < this.indexValueMap.size()) {
            return vec.dotProduct(this);
        }
        
        int result = 0, index;
        for (Map.Entry<Integer, Integer> entry : this.indexValueMap.entrySet()) {
            index = entry.getKey();
            if (vec.indexValueMap.containsKey(index)) {
                result += entry.getValue() * vec.indexValueMap.get(index);
            }
        }
        return result;
    }

}