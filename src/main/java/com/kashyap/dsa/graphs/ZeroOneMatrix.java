package com.kashyap.dsa.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * PATTERN   : Graph — Multi-Source BFS (Reverse Thinking)
 * DIFFICULTY: Medium
 * DATE      : May 10, 2026
 * LEETCODE  : 542
 *
 * PROBLEM:
 * Given a binary matrix, return matrix where each cell
 * contains distance to its nearest 0.
 *
 * TRICK SENTENCE:
 * "Don't BFS from 1s to find 0s — flip it.
 *  BFS from ALL 0s simultaneously outward.
 *  dist[nr][nc] = dist[r][c] + 1. dist==-1 means unvisited."
 *
 * KEY INSIGHT — REVERSE THINKING:
 * Brain trap: start from each 1, search for nearest 0.
 * Problem: O(M×N) BFS per cell = O(M²×N²) total. Too slow.
 * Flip: BFS from ALL 0s simultaneously toward 1s.
 * Each 1 gets its distance naturally as BFS expands level by level.
 * BFS guarantees shortest path — first time a cell is reached
 * is always via the shortest route.
 *
 * REVERSE THINKING PATTERN — same flip in 3 problems:
 *   01 Matrix      → BFS from all 0s  outward to 1s
 *   Rotting Oranges→ BFS from all 2s  outward to 1s
 *   Walls & Gates  → BFS from all 0s  outward to INF cells
 * "Distance FROM every cell TO target" →
 * flip to "BFS FROM every target TO all cells"
 *
 * dist[][] DUAL PURPOSE:
 *   1. Stores result distances
 *   2. Acts as visited array — dist==-1 means unvisited
 *   No separate visited[] needed.
 *
 * WHY dist[nr][nc] = dist[r][c] + 1:
 *   Parent's distance is already shortest (BFS guarantee).
 *   Child is one step further → parent + 1.
 *   First assignment is always the shortest — never updated again.
 *
 * DRY RUN:
 *   Input:          dist init:      queue:
 *   0 0 0           0  0  0        (0,0)(0,1)(0,2)
 *   0 1 0           0  -1 0        (1,0)(1,2)
 *   1 1 1           -1 -1 -1
 *
 *   BFS level 1 — process all 0s:
 *     (0,1)→(1,1): dist[1][1]=1, queue+=(1,1)
 *     (1,0)→(2,0): dist[2][0]=1, queue+=(2,0)
 *     (1,2)→(2,2): dist[2][2]=1, queue+=(2,2)
 *   dist: 0 0 0 / 0 1 0 / 1 -1 1
 *
 *   BFS level 2 — process (1,1),(2,0),(2,2):
 *     (1,1)→(2,1): dist[2][1]=2, queue+=(2,1)
 *   dist: 0 0 0 / 0 1 0 / 1 2 1 ✅
 *
 * TIME : O(M × N) — every cell visited exactly once
 * SPACE: O(M × N) — dist array + queue
 *
 * SIMILAR PROBLEMS:
 * - Rotting Oranges (994)
 * - Walls and Gates (286)
 * - Pacific Atlantic Water Flow (417)
 * - As Far from Land as Possible (1162)
 */
public class ZeroOneMatrix {

    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return mat;

        int m = mat.length;
        int n = mat[0].length;

        // dist[][] — result + visited tracker (-1 = unvisited)
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, -1);

        // Seed queue with ALL 0s — multi-source
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
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

                // dist==-1 means unvisited — first reach = shortest path
                if (nr >= 0 && nr < m
                        && nc >= 0 && nc < n
                        && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return dist;
    }
}