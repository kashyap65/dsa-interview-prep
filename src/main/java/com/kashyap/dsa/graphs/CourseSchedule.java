package com.kashyap.dsa.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * PATTERN   : Graph — DFS Cycle Detection (Directed Graph)
 * DIFFICULTY: Medium
 * DATE      : May 6, 2026
 * LEETCODE  : 207
 *
 * PROBLEM:
 * Given numCourses and prerequisites pairs [a,b] meaning
 * "to take a, must finish b first", return true if you can
 * finish all courses (i.e. no cycle exists in the graph).
 *
 * TRICK SENTENCE:
 * "3 states: 0=unvisited, 1=visiting, 2=visited.
 *  Seeing 1 during DFS = back edge = cycle.
 *  Seeing 2 = already safe = skip.
 *  Mark 2 only AFTER all neighbours done (postorder)."
 *
 * KEY INSIGHT:
 * Plain visited[] fails on directed graphs —
 * a node reachable via two paths looks like a cycle but isn't.
 * 3 states solve this:
 *   VISITING(1) = node is in current DFS path → seeing it again = real cycle
 *   VISITED(2)  = node fully processed from another path → safe, skip
 *
 * DIRECTED vs UNDIRECTED:
 *   Undirected: add edge BOTH ways. Cycle check needs parent tracking.
 *   Directed:   add edge ONE way only. Cycle check uses 3 states.
 *   Traversal code identical — only storage + cycle logic differs.
 *
 * EDGE DIRECTION:
 *   prerequisites[i] = [a, b] means b → a (finish b before a)
 *   So: graph.get(pre[1]).add(pre[0])  ← pre[1] is the source
 *
 * WHY MARK VISITED(2) AFTER ALL NEIGHBOURS:
 *   Postorder — children must fully report before parent is done.
 *   If you mark 2 before recursing, you'd incorrectly skip
 *   unfinished paths and miss cycles.
 *
 * DRY RUN:
 *   numCourses=3, prerequisites=[[1,0],[2,1],[0,2]]
 *   Graph: 0→1→2→0 (cycle)
 *
 *   states = [0,0,0]
 *   i=0: states[0]=0 → hasCycle(0):
 *     states[0]=1  → [1,0,0]
 *     neighbour=1: states[1]=0 → hasCycle(1):
 *       states[1]=1 → [1,1,0]
 *       neighbour=2: states[2]=0 → hasCycle(2):
 *         states[2]=1 → [1,1,1]
 *         neighbour=0: states[0]==1 → CYCLE! return true ✅
 *       hasCycle(2)=true → return true
 *     hasCycle(1)=true → return true
 *   hasCycle(0)=true → return false ✅
 *
 *   Answer: false (cannot finish) ✅
 *
 * TIME : O(V + E) — every node and edge visited once
 * SPACE: O(V + E) — adjacency list + recursion stack
 *
 * SIMILAR PROBLEMS:
 * - Course Schedule II (210) — return the order, not just yes/no
 * - Find Eventual Safe States (802)
 * - Redundant Connection (684)
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0)
            return true;

        // Build directed adjacency list: pre[1] → pre[0]
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] pre : prerequisites)
            graph.get(pre[1]).add(pre[0]);

        // 0 = unvisited, 1 = visiting, 2 = visited
        int[] states = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (states[i] == 0) {
                if (hasCycle(i, states, graph))
                    return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int course, int[] states,
                             List<List<Integer>> graph) {

        states[course] = 1;  // VISITING — now part of current path

        for (int neighbour : graph.get(course)) {
            if (states[neighbour] == 1) return true;  // back edge → cycle

            if (states[neighbour] == 0) {             // unvisited → explore
                if (hasCycle(neighbour, states, graph))
                    return true;
            }
            // states[neighbour] == 2 → already safe → skip
        }

        states[course] = 2;  // VISITED — fully processed, no cycle here
        return false;
    }
}