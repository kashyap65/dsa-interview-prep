package com.kashyap.dsa.graphs;

/**
 * PATTERN   : Graph — DFS Flood Fill
 * DIFFICULTY: Easy
 * DATE      : May 8, 2026
 * LEETCODE  : 733
 *
 * PROBLEM:
 * Given a 2D grid, starting cell (sr,sc) and newColor,
 * fill all 4-directionally connected cells of the same
 * original color with newColor. Return modified grid.
 *
 * TRICK SENTENCE:
 * "Paint bucket tool in MS Paint.
 *  originalColor = grid[sr][sc]. Edge case: originalColor==newColor → return.
 *  DFS spreads only to same originalColor cells.
 *  Changing color IS the visited mark — no visited[] needed."
 *
 * KEY INSIGHT:
 * No separate visited[] needed — unlike Number of Islands.
 * Changing grid[sr][sc] to newColor prevents revisiting:
 *   Cell starts as originalColor → gets filled → newColor.
 *   Next DFS call checks grid[nr][nc]==originalColor → fails → stops.
 * Spread condition: grid[nr][nc]==originalColor (not ==1 like islands).
 *
 * EDGE CASE — must handle:
 *   originalColor == newColor → return immediately.
 *   Without this: grid never changes → condition always true → infinite loop.
 *
 * DFS vs BFS HERE:
 *   Both work. DFS cleaner — no queue, no counter needed.
 *   Base case check at entry: if inBounds AND same color → fill + recurse.
 *   Bounds check INSIDE dfs (not before call) → cleaner call site.
 *
 * DRY RUN:
 *   image: 1 1 1    sr=1, sc=1, color=2
 *          1 1 0    originalColor=1
 *          1 0 1
 *
 *   dfs(1,1): grid[1][1]=1==original → fill→2, recurse 4 dirs
 *     dfs(2,1): grid[2][1]=0≠original → return
 *     dfs(0,1): grid[0][1]=1==original → fill→2, recurse
 *       dfs(0,0): grid[0][0]=1==original → fill→2, recurse
 *         dfs(1,0): grid[1][0]=1==original → fill→2, recurse
 *           dfs(2,0): grid[2][0]=1==original → fill→2, recurse
 *             all neighbours OOB/0/already2 → return
 *           dfs(1,1): grid[1][1]=2≠original → return ✅ (no infinite loop)
 *         dfs(0,0): already 2 → return
 *       dfs(0,2): grid[0][2]=1==original → fill→2, recurse
 *         all neighbours OOB/0/already2 → return
 *     dfs(1,0): already 2 → return
 *     dfs(1,2): grid[1][2]=0≠original → return
 *
 *   Final: 2 2 2
 *          2 2 0
 *          2 0 1  ✅ (2,2) never touched — not connected
 *
 * TIME : O(M × N) — every cell visited at most once
 * SPACE: O(M × N) — recursion stack worst case all same color
 *
 * SIMILAR PROBLEMS:
 * - Number of Islands (200)
 * - Max Area of Island (695)
 * - Surrounded Regions (130)
 * - Pacific Atlantic Water Flow (417)
 */
public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null || image.length == 0 || image[0].length == 0)
            return image;

        int originalColor = image[sr][sc];

        // Edge case: already target color → no-op, avoid infinite loop
        if (originalColor == color) return image;

        dfs(sr, sc, image, color, originalColor);
        return image;
    }

    private void dfs(int r, int c, int[][] grid,
                     int color, int originalColor) {
        int m = grid.length;
        int n = grid[0].length;

        // Base case: out of bounds OR different color (boundary or already filled)
        if (r < 0 || r >= m || c < 0 || c >= n) return;
        if (grid[r][c] != originalColor) return;

        grid[r][c] = color;          // fill — this IS the visited mark

        dfs(r + 1, c, grid, color, originalColor);  // down
        dfs(r - 1, c, grid, color, originalColor);  // up
        dfs(r, c + 1, grid, color, originalColor);  // right
        dfs(r, c - 1, grid, color, originalColor);  // left
    }
}