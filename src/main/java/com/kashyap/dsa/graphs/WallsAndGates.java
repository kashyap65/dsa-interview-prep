package com.kashyap.dsa.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * PATTERN   : Graph — Multi-Source BFS (Reverse Thinking)
 * DIFFICULTY: Medium
 * DATE      : May 10, 2026
 * LEETCODE  : 286
 *
 * PROBLEM:
 * Grid of -1 (wall), 0 (gate), INF (empty room).
 * Fill each empty room with distance to nearest gate.
 * Modify in-place. No return value.
 *
 * TRICK SENTENCE:
 * "Same as 01 Matrix — seed ALL gates (0s) at once.
 *  BFS outward. rooms[nr][nc]==INF means unvisited.
 *  rooms[nr][nc] = rooms[r][c] + 1. In-place, no return."
 *
 * KEY INSIGHT — REVERSE THINKING:
 * Don't BFS from each room to find nearest gate — too slow O(M²N²).
 * Flip: BFS from ALL gates simultaneously toward rooms.
 * Each room gets shortest distance naturally — BFS guarantee.
 *
 * SAME REVERSE THINKING PATTERN:
 *   01 Matrix      → BFS from all 0s  → dist==-1 means unvisited
 *   Rotting Oranges→ BFS from all 2s  → cell==1  means unvisited
 *   Walls & Gates  → BFS from all 0s  → cell==INF means unvisited
 * Unvisited condition always = "not yet assigned a distance"
 *
 * COMMON BUG:
 *   nc == Integer.MAX_VALUE   → wrong, nc is column index
 *   rooms[nr][nc] == Integer.MAX_VALUE → correct ✅
 *
 * IN-PLACE MODIFICATION:
 *   rooms[][] IS the distance tracker.
 *   INF = unvisited room.
 *   -1  = wall — never update.
 *   0   = gate — never update.
 *   Only INF cells get updated → rooms[nr][nc]==INF check handles this.
 *
 * DRY RUN:
 *   Input (simplified 2×2):
 *   INF  0
 *    0  INF
 *
 *   Seed queue: (0,1)=0, (1,0)=0
 *
 *   Poll(0,1):
 *     up   (-1,1) OOB
 *     down (1,1)=INF ✅ → rooms[1][1]=0+1=1, queue+=(1,1)
 *     left (0,0)=INF ✅ → rooms[0][0]=0+1=1, queue+=(0,0)
 *     right(0,2) OOB
 *
 *   Poll(1,0):
 *     up   (0,0)=1 already set → skip
 *     down (2,0) OOB
 *     left (-1,0) OOB  (wait, wrong — left is nc-1)
 *     right(1,1)=1 already set → skip
 *
 *   Poll(1,1): all neighbours already set → nothing added
 *   Poll(0,0): all neighbours already set → nothing added
 *
 *   Result:
 *   1  0
 *   0  1  ✅
 *
 * TIME : O(M × N) — every cell visited exactly once
 * SPACE: O(M × N) — queue worst case all gates
 *
 * SIMILAR PROBLEMS:
 * - 01 Matrix (542)
 * - Rotting Oranges (994)
 * - As Far from Land as Possible (1162)
 */
public class WallsAndGates {

    private static final int INF = Integer.MAX_VALUE;

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

        int m = rooms.length;
        int n = rooms[0].length;

        // Seed queue with ALL gates simultaneously — multi-source
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0)
                    queue.offer(new int[]{i, j});
            }
        }

        int[] dr = {-1, 1,  0, 0};
        int[] dc = { 0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] rc = queue.poll();
            int r = rc[0];
            int c = rc[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // INF = unvisited room (walls=-1 and gates=0 never satisfy this)
                if (nr >= 0 && nr < m
                        && nc >= 0 && nc < n
                        && rooms[nr][nc] == INF) {        // ← rooms[nr][nc] not nc

                    rooms[nr][nc] = rooms[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}