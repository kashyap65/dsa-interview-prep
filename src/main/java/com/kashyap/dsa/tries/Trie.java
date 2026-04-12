package com.kashyap.dsa.tries;

/**
 * PATTERN   : Trie — Prefix Tree
 * DIFFICULTY: Medium
 * DATE      : Apr 14, 2026
 * LEETCODE  : 208
 *
 * TRICK SENTENCE:
 * "Each node = one char. Path root→leaf = word.
 *  26 children array. idx = ch-'a'.
 *  insert: create if null, move, mark isEnd.
 *  search: return false if null, return isEnd.
 *  startsWith: same as search but return true (ignore isEnd)."
 *
 * WHY TRIE:
 *   O(L) search regardless of dictionary size.
 *   Shared prefixes stored once → memory efficient.
 *   "cat","car","card" share c→a path.
 *
 * SEARCH vs STARTSWITH — ONE DIFFERENCE:
 *   search:     return curr.isEnd   ← must be complete word
 *   startsWith: return true         ← prefix path enough
 *
 * KEY OPERATIONS:
 *   idx = ch - 'a'                  → array index for char
 *   children[idx] == null → create  → insert path
 *   children[idx] == null → false   → search miss
 *   curr.isEnd = true               → marks complete word
 *
 * REAL WORLD:
 *   Google autocomplete, spell checker,
 *   IP routing, word games (Boggle, Scrabble)
 *
 * TIME : O(L) insert/search/startsWith — L = word length
 * SPACE: O(N × L × 26) — N words, avg length L
 *
 * SIMILAR PROBLEMS:
 * - Word Search II (212) — Trie + Backtracking
 * - Design Add and Search Words (211)
 * - Replace Words (648)
 */
class Trie {
    private final TrieNode root;

    public Trie() { root = new TrieNode(); }

    public void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null)
                curr.children[idx] = new TrieNode();
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return curr.isEnd;  // must be complete word
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) return false;
            curr = curr.children[idx];
        }
        return true;  // prefix path exists — isEnd irrelevant
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}