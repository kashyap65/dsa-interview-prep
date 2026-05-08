package com.kashyap.dsa.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * PATTERN   : Graph — BFS/DFS Flood Fill + Area Count
 * DIFFICULTY: Medium
 * DATE      : May 5, 2026
 * LEETCODE  : 695
 *
 * PROBLEM:
 * Given a 2D grid of 1s (land) and 0s (water), return the
 * maximum area of an island. An island is a group of 1s
 * connected horizontally or vertically.
 *
 * TRICK SENTENCE:
 * "Same as Number of Islands — but DFS/BFS returns area not count.
 *  Every cell = 1 + sum of valid neighbours.
 *  Mark visited + add to queue = always one operation."
 *
 * KEY INSIGHT:
 * LC#200 counted how many BFS calls were made (islands).
 * LC#695 counts how many cells each BFS visits (area).
 * Only difference: track size per island, keep running max.
 * Everything else — visited[], 4 directions, 3 checks — identical.
 *
 * 3 BUGS TO AVOID (all from same root cause — visited discipline):
 *   1. Mark starting cell visited before BFS loop starts
 *   2. Mark neighbour visited ON OFFER not on poll
 *   3. dc[] last element must be +1 (right), not -1
 *      dr={-1,1,0,0} dc={0,0,-1,1} — tattoo this pair
 *
 * DRY RUN:
 *   Grid: 1 1 0
 *         1 0 0
 *         0 0 1
 *
 *   (0,0)=1 unvisited → BFS:
 *     offer(0,0), visited[0][0]=true, cnt=1
 *     poll(0,0):
 *       right(0,1)=1 ✅ → visited, offer, cnt=2
 *       down(1,0)=1  ✅ → visited, offer, cnt=3
 *     poll(0,1):
 *       all neighbours 0 or visited → nothing added
 *     poll(1,0):
 *       all neighbours 0 or visited → nothing added
 *     area=3, maxArea=3
 *
 *   (2,2)=1 unvisited → BFS:
 *     offer(2,2), visited[2][2]=true, cnt=1
 *     poll(2,2): all OOB or 0 → nothing added
 *     area=1, maxArea stays 3
 *
 *   Answer: 3 ✅
 *
 * TIME : O(R × C) — every cell visited at most once
 * SPACE: O(R × C) — visited array + queue worst case
 *
 * SIMILAR PROBLEMS:
 * - Number of Islands (200)
 * - Flood Fill (733)
 * - Number of Provinces (547)
 * - Surrounded Regions (130)
 */
public class MaxAreaOfIslandBFS {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxArea = 0;

        int[] dr = {-1, 1,  0, 0};
        int[] dc = { 0, 0, -1, 1};  // ← last is +1 (right). never -1.

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = bfs(grid, i, j, visited, rows, cols, dr, dc);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int bfs(int[][] grid, int i, int j,
                    boolean[][] visited, int rows, int cols,
                    int[] dr, int[] dc) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;    // Bug 3 fix: mark starting cell immediately
        int count = 1;           // current cell counts as 1

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < rows
                        && nc >= 0 && nc < cols
                        && grid[nr][nc] == 1
                        && !visited[nr][nc]) {

                    visited[nr][nc] = true;  // Bug 2 fix: mark on offer not poll
                    queue.offer(new int[]{nr, nc});
                    count++;
                }
            }
        }
        return count;
    }
}