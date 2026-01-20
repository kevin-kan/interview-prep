package com.kevinkan.medium;

/**
* Given an m x n grid of characters board and a string word, return true if word exists in the grid.
* The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
* 
* Constraints:
* m == board.length
* n == board[i].length
* 1 <= m, n <= 6
* 1 <= word.length <= 15
* board and word consists of only lowercase and uppercase English letters.
*/
public class WordSearch {

    /**
     * DFS with Backtracking approach
     * Time Complexity: O(m * n * 4^L) where m is the number of rows, n is the number of columns, and L is the length of the word.
     * Space Complexity: O(L) where L is the length of the word, due to the recursion stack.
     */
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        char[] wordChars = word.toCharArray();
        boolean result = false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // At each cell we will try to match the start of the word
                if (board[r][c] == wordChars[0]) {
                    result = dfs(board, visited, r, c, wordChars, 0);
                    if (result) return result;
                }
            }
        }

        return result;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int row, int col, char[] wordChars, int charIndex) {
        // If we've completed the word, return true
        if (charIndex == wordChars.length) {
            return true;
        }
        // If we're out of bounds or the character has already been visited, return false
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) {
            return false;
        }
        // If the cell doesn't match the character of the word, return false
        if ( wordChars[charIndex] != board[row][col]) {
            return false;
        }
        // Otherwise, the character matches and we continue our search
        visited[row][col] = true;

        if (dfs(board, visited, row+1, col, wordChars, charIndex + 1) ||
            dfs(board, visited, row-1, col, wordChars, charIndex + 1) ||
            dfs(board, visited, row, col+1, wordChars, charIndex + 1) ||
            dfs(board, visited, row, col-1, wordChars, charIndex + 1)) {
            return true;
        }

        visited[row][col] = false;
        return false;
    }

}
