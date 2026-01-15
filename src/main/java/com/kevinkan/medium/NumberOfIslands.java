package com.kevinkan.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* Given an mxn 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
* An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
* 
* Constraints:
* m == grid.length
* n == grid[i].length
* 1 <= m, n <= 300
* grid[i][j] is '0' or '1'.
*/
public class NumberOfIslands {
    /**
     * Depth-First Search (DFS) approach
     * Time Complexity: O(m * n) where m is the number of rows and n is the number of columns in the grid.
     * Space Complexity: O(m * n) in the worst case due to the recursion stack.
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    sinkIsland(grid, r, c);
                    numIslands++;
                }

            }
        }

        return numIslands;
    }

    private void sinkIsland(char[][] grid, int r, int c) {
        // Check if it's a valid space first
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
            // If it's an island:
            if (grid[r][c] == '1') {
                grid[r][c] = '0';
                sinkIsland(grid, r+1, c);
                sinkIsland(grid, r-1, c);
                sinkIsland(grid, r, c+1);
                sinkIsland(grid, r, c-1);
            }

        }        
    }
}
