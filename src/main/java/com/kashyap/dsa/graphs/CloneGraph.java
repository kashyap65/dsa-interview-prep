package com.kashyap.dsa.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PATTERN   : Graph — DFS + HashMap (Deep Copy)
 * DIFFICULTY: Medium
 * DATE      : May 5, 2026
 * LEETCODE  : 133
 *
 * PROBLEM:
 * Given a reference to a node in a connected undirected graph,
 * return a deep copy (clone) of the graph.
 *
 * TRICK SENTENCE:
 * "HashMap<Node,Node> original→clone. Register BEFORE recursing.
 *  Already in map? Return existing clone. Prevents infinite loop on cycles."
 *
 * KEY INSIGHT:
 * Graph has cycles → naive DFS loops forever.
 * HashMap serves dual purpose:
 *   1. visited check   → if in map, already cloned, skip
 *   2. clone registry  → map.get(original) = its clone
 * Key = node REFERENCE not node.val → works even with duplicate values.
 * Register clone in map BEFORE recursing into neighbours —
 * same reason we mark visited before adding to BFS queue.
 *
 * WHY NODE REFERENCE NOT NODE.VAL AS KEY:
 *   node.val as key → breaks if two nodes share same value.
 *   Node reference as key → always unique. Interview standard.
 *
 * DRY RUN:
 *   Graph: 1—2
 *          |  |
 *          4—3
 *
 *   clone(1): map={}, Create Clone1, map={1→Clone1}
 *     clone(2): Create Clone2, map={1→Clone1, 2→Clone2}
 *       clone(1): in map → return Clone1 ✅
 *       clone(3): Create Clone3, map={..., 3→Clone3}
 *         clone(2): in map → return Clone2 ✅
 *         clone(4): Create Clone4, map={..., 4→Clone4}
 *           clone(3): in map → return Clone3 ✅
 *           clone(1): in map → return Clone1 ✅
 *         Clone4.neighbors=[Clone3,Clone1] ✅
 *       Clone3.neighbors=[Clone2,Clone4] ✅
 *     Clone2.neighbors=[Clone1,Clone3] ✅
 *   Clone1.neighbors=[Clone2,Clone4] ✅
 *   return Clone1 ✅
 *
 * TIME : O(V + E) — every node and edge visited once
 * SPACE: O(V)     — HashMap stores one entry per node
 *
 * SIMILAR PROBLEMS:
 * - Copy List with Random Pointer (138)
 * - Number of Islands (200)
 * - Number of Connected Components (323)
 */
public class CloneGraph {

    // Key = original node reference (not val) → works even with duplicate values
    private Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // Already cloned → return existing clone (prevents infinite loop)
        if (map.containsKey(node)) return map.get(node);

        // Create clone and register BEFORE recursing into neighbours
        Node cloneNode = new Node(node.val);
        map.put(node, cloneNode);

        // Recurse into every neighbour — add their clones to our clone's list
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}

class Node {
    int val;
    List<Node> neighbors;

    public Node(int val) {
        this.val = val;
    }
}