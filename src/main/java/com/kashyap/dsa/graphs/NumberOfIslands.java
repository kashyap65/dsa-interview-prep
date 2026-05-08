package com.kashyap.dsa.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * PATTERN   : Graph — BFS Flood Fill
 * DIFFICULTY: Medium
 * DATE      : May 4, 2026
 * LEETCODE  : 200
 *
 * PROBLEM:
 * Given a 2D grid of '1' (land) and '0' (water),
 * count the number of islands. An island is surrounded
 * by water and formed by connecting adjacent lands
 * horizontally or vertically.
 *
 * TRICK SENTENCE:
 * "Scan grid — unvisited land = new island — BFS flood-fills
 *  entire island before moving on. Mark visited = add to queue.
 *  Never split them. dr for row, dc for col. Never mix."
 *
 * KEY INSIGHT:
 * Every unvisited '1' = start of a new island.
 * BFS from that cell marks all connected land as visited.
 * Outer scan continues — next unvisited '1' = another island.
 * visited[] prevents counting same land twice.
 *
 * 4 DIRECTIONS PAIR — tattoo this:
 *   int[] dr = {-1, 1,  0, 0};  row:  up down left right
 *   int[] dc = { 0, 0, -1, 1};  col:  up down left right
 *   dr moves rows. dc moves cols. Married pair. Never separate.
 *
 * 3 CHECKS BEFORE ADDING NEIGHBOUR (always in this order):
 *   1. In bounds?   nr>=0 && nr<rows && nc>=0 && nc<cols
 *   2. Is land?     grid[nr][nc] == '1'  (char '1' not int 1)
 *   3. Not visited? !visited[nr][nc]
 *
 * MARK VISITED RULE:
 *   Mark visited + add to queue = ONE operation. Never split.
 *   If you mark on poll instead of offer → same cell added
 *   multiple times → duplicate processing → wrong count.
 *
 * DRY RUN:
 *   Grid:        1 1 0
 *                0 1 0
 *                0 0 1
 *
 *   (0,0)='1' unvisited → count=1, BFS:
 *     Poll(0,0) → right(0,1)='1' ✅ → visited, enqueue
 *     Poll(0,1) → down(1,1)='1' ✅ → visited, enqueue
 *     Poll(1,1) → all neighbours OOB/'0'/visited → queue empty
 *   Island 1 fully marked ✅
 *
 *   Scan continues → (2,2)='1' unvisited → count=2, BFS:
 *     Poll(2,2) → all neighbours OOB or '0' → queue empty
 *   Island 2 fully marked ✅
 *
 *   Final answer: 2 ✅
 *
 * TIME : O(R × C) — every cell visited at most once
 * SPACE: O(R × C) — visited array + queue (worst case all land)
 *
 * SIMILAR PROBLEMS:
 * - Max Area of Island (695)
 * - Flood Fill (733)
 * - Number of Provinces (547)
 * - Rotting Oranges (994)
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        int[] dr = {-1, 1,  0, 0};
        int[] dc = { 0, 0, -1, 1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    bfs(grid, i, j, visited, rows, cols, dr, dc);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j,
                     boolean[][] visited, int rows, int cols,
                     int[] dr, int[] dc) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;              // mark + add — always together

        while (!queue.isEmpty()) {
            int[] rc = queue.poll();
            int r = rc[0];
            int c = rc[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];        // dr = row direction
                int nc = c + dc[d];        // dc = col direction

                if (nr >= 0 && nr < rows
                        && nc >= 0 && nc < cols
                        && grid[nr][nc] == '1'
                        && !visited[nr][nc]) {

                    visited[nr][nc] = true; // mark + add — always together
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}