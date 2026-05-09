package com.kashyap.dsa.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * PATTERN   : Graph — Topological Sort BFS (Kahn's Algorithm)
 * DIFFICULTY: Medium
 * DATE      : May 7, 2026
 * LEETCODE  : 210
 *
 * PROBLEM:
 * Given numCourses and prerequisites [a,b] meaning
 * "finish b before a", return a valid course order.
 * If impossible (cycle exists), return empty array.
 *
 * TRICK SENTENCE:
 * "Indegree = prerequisites count. Start with indegree-0 nodes.
 *  Poll → add to order → reduce neighbours' indegree.
 *  Indegree hits 0 → add to queue. order.size()==n → valid."
 *
 * KEY INSIGHT:
 * Indegree-0 = no prerequisites = safe to take right now.
 * Kahn's = always pick indegree-0 node, remove it,
 * update neighbours — exactly how you'd schedule real tasks.
 * Cycle detection is FREE — if cycle exists, some nodes
 * never reach indegree-0 → never processed → order.size() < n.
 *
 * KAHN'S vs DFS CYCLE DETECTION (LC#207):
 *   LC#207 DFS  → yes/no answer, 3 states, recursive
 *   LC#210 BFS  → returns order, indegree array, iterative
 *   Both O(V+E). Kahn's preferred when ORDER is needed.
 *   DFS preferred when just checking cycle existence.
 *
 * COMMON BUG:
 *   queue.offer(indegree[i]) → adds the COUNT (always 0) not the node
 *   queue.offer(i)           → adds the node index ✅
 *
 * EDGE DIRECTION:
 *   prerequisites [a,b] means b→a (do b before a)
 *   graph.get(p[1]).add(p[0])  ← p[1] is source
 *   indegree[p[0]]++           ← p[0] gains a prerequisite
 *
 * DRY RUN:
 *   numCourses=4, prerequisites=[[1,0],[2,0],[3,1],[3,2]]
 *   Graph: 0→1, 0→2, 1→3, 2→3
 *   Indegree: [0, 1, 1, 2]
 *
 *   Init queue: [0]  (only indegree-0 node)
 *   Poll 0 → order=[0]
 *     neighbour 1: indegree 1→0 → queue=[1]
 *     neighbour 2: indegree 1→0 → queue=[1,2]
 *   Poll 1 → order=[0,1]
 *     neighbour 3: indegree 2→1 → not 0, skip
 *   Poll 2 → order=[0,1,2]
 *     neighbour 3: indegree 1→0 → queue=[3]
 *   Poll 3 → order=[0,1,2,3]
 *   order.size()==4 → return [0,1,2,3] ✅
 *
 * TIME : O(V + E) — every node and edge processed once
 * SPACE: O(V + E) — adjacency list + indegree array + queue
 *
 * SIMILAR PROBLEMS:
 * - Course Schedule (207)
 * - Alien Dictionary (269)
 * - Sequence Reconstruction (444)
 * - Minimum Height Trees (310)
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        // Build indegree array
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);  // p[1] → p[0]
            indegree[p[0]]++;           // p[0] has one more prerequisite
        }

        // Seed queue with all indegree-0 nodes
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);         // offer i (node), not indegree[i] (count)
        }

        // Kahn's BFS
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order.add(course);

            for (int neighbour : graph.get(course)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0)
                    queue.offer(neighbour);
            }
        }

        // Cycle check — if cycle exists, some nodes never reached indegree-0
        return order.size() == numCourses
                ? order.stream().mapToInt(Integer::intValue).toArray()
                : new int[]{};
    }

    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] p : prerequisites)
            graph.get(p[1]).add(p[0]);

        int[] states = new int[numCourses];
        LinkedList<Integer> result = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (states[i] == 0) {
                if (dfsHasCycle(i, states, graph, result))
                    return new int[]{};  // cycle found
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean dfsHasCycle(int course, int[] states,
                                List<List<Integer>> graph,
                                LinkedList<Integer> result) {

        states[course] = 1;  // VISITING

        for (int neighbour : graph.get(course)) {
            if (states[neighbour] == 1) return true;  // cycle
            if (states[neighbour] == 0) {
                if (dfsHasCycle(neighbour, states, graph, result))
                    return true;
            }
        }

        states[course] = 2;           // VISITED
        result.addFirst(course);      // postorder → add to FRONT
        return false;
    }
}