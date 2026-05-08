package com.kashyap.dsa.graphs;

/**
 * PATTERN   : Graph — DFS Flood Fill + Area Count
 * DIFFICULTY: Medium
 * DATE      : May 5, 2026
 * LEETCODE  : 695
 *
 * DFS VERSION — same problem, recursive approach
 *
 * WHY DFS IS MORE ELEGANT HERE:
 * Each call returns 1 + area of all connected cells naturally.
 * No queue needed. No explicit counter.
 * Return value bubbles up the call stack automatically.
 *
 * DFS vs BFS FOR AREA PROBLEMS:
 *   BFS → needs explicit counter variable, mark on offer
 *   DFS → return value IS the counter, cleaner code
 *   Both are O(R×C). DFS preferred for "measure size" problems.
 *
 * TRICK SENTENCE:
 * "DFS returns 1 + sum of 4 directions.
 *  Mark visited BEFORE recursing — same rule as BFS offer.
 *  Out of bounds or water or visited → return 0."
 *
 * DRY RUN:
 *   Grid: 1 1 0
 *         1 0 0
 *
 *   dfs(0,0):
 *     visited[0][0]=true
 *     return 1
 *       + dfs(-1,0) → OOB → 0
 *       + dfs(1,0)  → visited[1][0]=true
 *           return 1
 *             + dfs(2,0) → OOB → 0
 *             + dfs(0,0) → visited → 0
 *             + dfs(1,1) → '0' → 0
 *             + dfs(1,-1)→ OOB → 0
 *           = 1
 *       + dfs(0,1)  → visited[0][1]=true
 *           return 1
 *             + dfs(-1,1)→ OOB → 0
 *             + dfs(1,1) → '0' → 0
 *             + dfs(0,2) → '0' → 0
 *             + dfs(0,0) → visited → 0
 *           = 1
 *       + dfs(0,-1) → OOB → 0
 *     = 1 + 1 + 1 + 0 = 3 ✅
 *
 * TIME : O(R × C) — every cell visited at most once
 * SPACE: O(R × C) — recursion stack worst case (all land, snake shape)
 *
 * SIMILAR PROBLEMS:
 * - Number of Islands (200)
 * - Flood Fill (733)
 * - Number of Provinces (547)
 * - Surrounded Regions (130)
 */
public class MaxAreaOfIslandDFS{

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(grid, i, j, visited, rows, cols);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c,
                    boolean[][] visited, int rows, int cols) {

        // Base cases — return 0 (contributes nothing to area)
        if (r < 0 || r >= rows || c < 0 || c >= cols) return 0; // out of bounds
        if (grid[r][c] == 0) return 0;                           // water
        if (visited[r][c]) return 0;                             // already counted

        visited[r][c] = true;   // mark BEFORE recursing — same rule as BFS offer

        return 1                          // this cell
                + dfs(grid, r - 1, c, visited, rows, cols)  // up
                + dfs(grid, r + 1, c, visited, rows, cols)  // down
                + dfs(grid, r, c - 1, visited, rows, cols)  // left
                + dfs(grid, r, c + 1, visited, rows, cols); // right
    }
}