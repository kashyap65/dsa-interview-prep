package com.kashyap.dsa.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * PATTERN   : Graph — Multi-Source BFS (Level by Level)
 * DIFFICULTY: Medium
 * DATE      : May 8, 2026
 * LEETCODE  : 994
 *
 * PROBLEM:
 * Grid of 0 (empty), 1 (fresh), 2 (rotten).
 * Every minute, rotten oranges spread to adjacent fresh ones.
 * Return minutes until no fresh remain. -1 if impossible.
 *
 * TRICK SENTENCE:
 * "Seed queue with ALL rotten oranges at minute 0 — multi-source.
 *  Level = 1 minute. Grid IS the visited array (mark 1→2 on spread).
 *  fresh==0 before BFS → return 0. After BFS → minutes-1 or -1."
 *
 * KEY INSIGHT:
 * Multiple rotten oranges spread simultaneously — not one after another.
 * Multi-source BFS models this perfectly:
 *   All rotten oranges in queue at start = minute 0.
 *   Each BFS level = 1 minute of spreading.
 *   minutes++ before processing level, return minutes-1 at end
 *   because last level increments minutes but spreads nothing new.
 * No separate visited[] needed — change grid[nr][nc] 1→2 directly.
 *
 * MULTI-SOURCE vs SINGLE-SOURCE:
 *   Single-source: one node in queue at start.
 *   Multi-source:  ALL starting nodes in queue at start.
 *   BFS loop is IDENTICAL — only seeding differs.
 *   You already used this in Kahn's — all indegree-0 nodes at once.
 *
 * WHY minutes-1 AT END:
 *   Last BFS level processes nodes but spreads to nothing new.
 *   minutes++ still fires for that level → one extra count.
 *   Fix: return minutes-1 when fresh==0.
 *   Alternative: only increment if spread actually happened.
 *
 * DRY RUN:
 *   Grid: 2 1 1        fresh=3
 *         0 1 1        queue=[(0,0)]
 *         1 0 2        queue+=[(2,2)] → fresh=4? No:
 *                      fresh=4, queue=[(0,0),(2,2)]
 *
 *   Minute 1 (size=2):
 *     Poll(0,0): right(0,1)=1→2, fresh=3, queue+=[(0,1)]
 *     Poll(2,2): up(1,2)=1→2,   fresh=2, queue+=[(1,2)]
 *     minutes=1
 *
 *   Minute 2 (size=2):
 *     Poll(0,1): right(0,2)=1→2 fresh=1, down(1,1)=1→2 fresh=0
 *     Poll(1,2): left(1,1) already 2, up(0,2) already 2
 *     minutes=2
 *
 *   Minute 3 (size=2):
 *     Poll(0,2): no fresh neighbours
 *     Poll(1,1): no fresh neighbours
 *     minutes=3
 *
 *   fresh==0 → return minutes-1 = 2 ✅
 *
 * TIME : O(M × N) — every cell visited at most once
 * SPACE: O(M × N) — queue worst case all cells rotten
 *
 * SIMILAR PROBLEMS:
 * - Number of Islands (200)
 * - Walls and Gates (286)
 * - 01 Matrix (542)
 * - Pacific Atlantic Water Flow (417)
 */
public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        int minutes = 0;

        // Seed queue with ALL rotten oranges — multi-source
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
                else if (grid[i][j] == 1) fresh++;
            }
        }

        // No fresh oranges — already done
        if (fresh == 0) return 0;

        int[] dr = {-1, 1,  0, 0};
        int[] dc = { 0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int size = queue.size();  // snapshot level
            minutes++;                // this level = 1 minute

            for (int i = 0; i < size; i++) {
                int[] rc = queue.poll();
                int r = rc[0];
                int c = rc[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr >= 0 && nr < m
                            && nc >= 0 && nc < n
                            && grid[nr][nc] == 1) {

                        grid[nr][nc] = 2;           // grid IS visited array
                        fresh--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        // minutes-1 because last level fired minutes++ but spread nothing
        return fresh == 0 ? minutes - 1 : -1;
    }
}