package com.kevinkan.medium;

/**
* You are given an m x n integer matrix matrix with the following two properties:
* Each row is sorted in non-decreasing order.
* The first integer of each row is greater than the last integer of the previous row.
* Given an integer target, return true if target is in matrix or false otherwise.
* You must write a solution in O(log(m * n)) time complexity.
* 
* Constraints:
* m == matrix.length
* n == matrix[i].length
* 1 <= m, n <= 100
* -10^4 <= matrix[i][j], target <= 10^4
*/
public class SearchA2DMatrix {

    /**
     * Binary Search 
     * Essentially treat the 2D matrix as a 1D sorted array, and use base n conversion to map indices
     * Time Complexity: O(log(m * n)) where m is the number of rows and n is the number of columns in the matrix.
     * Space Complexity: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
