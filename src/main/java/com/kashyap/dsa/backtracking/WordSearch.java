package com.kashyap.dsa.backtracking;

/**
 * PATTERN   : Backtracking — 2D Grid DFS
 * DIFFICULTY: Medium
 * DATE      : Apr 15, 2026
 * LEETCODE  : 79
 *
 * TRICK SENTENCE:
 * "DFS from every cell. Match char → mark '#' → explore 4 dirs.
 *  Restore char after. Return OR of all 4 directions.
 *  index==word.length() = SUCCESS."
 *
 * BACKTRACKING 3 STEPS:
 *   CHOOSE:   board[r][c] = '#'           (mark visited)
 *   EXPLORE:  dfs all 4 directions
 *   UNCHOOSE: board[r][c] = word.charAt(index) (restore)
 *
 * CRITICAL BUGS TO AVOID:
 *   r>m not r>=m → ArrayIndexOutOfBounds
 *   r++ in dfs call → post-increment corrupts r
 *   Missing UNCHOOSE → cell stays '#' → breaks future paths
 *   return true always → never returns false
 *
 * BASE CASES:
 *   index==word.length() → SUCCESS (matched all chars)
 *   out of bounds / visited / mismatch → FAILURE
 *
 * TIME : O(m×n × 4^L) — L = word length
 * SPACE: O(L) — recursion stack depth
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // try starting DFS from every cell
                if (dfs(board, r, c, word, 0)) {
                    return true;  // found the word starting from (r,c)
                }
                // if dfs returns false → this cell didn't work
                // try next cell
            }
        }

        return false;  // no starting cell led to a complete match
    }

    private boolean dfs(char[][] board, int r, int c,
                        String word, int index) {
        // SUCCESS — matched all characters
        if (index == word.length()) return true;

        int m = board.length, n = board[0].length;

        // FAILURE cases
        if (r < 0 || r >= m || c < 0 || c >= n) return false;
        if (board[r][c] == '#') return false;
        if (board[r][c] != word.charAt(index)) return false;

        // CHOOSE — mark visited
        board[r][c] = '#';

        // EXPLORE — 4 directions only (no diagonals)
        boolean found = dfs(board, r+1, c,   word, index+1) ||
                dfs(board, r-1, c,   word, index+1) ||
                dfs(board, r,   c+1, word, index+1) ||
                dfs(board, r,   c-1, word, index+1);

        // UNCHOOSE — restore original char
        board[r][c] = word.charAt(index);

        return found;
    }
}