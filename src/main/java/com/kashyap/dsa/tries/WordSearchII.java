package com.kashyap.dsa.tries;

import java.util.*;

/**
 * PATTERN   : Trie + Backtracking (2D Grid)
 * DIFFICULTY: Hard
 * DATE      : Apr 22, 2026
 * LEETCODE  : 212
 *
 * TRICK SENTENCE:
 * "Build Trie from all words. DFS from every cell.
 *  Check trie child not word index. isEnd→add word, continue.
 *  CHOOSE '#', EXPLORE 4 dirs, UNCHOOSE original."
 *
 * KEY DIFFERENCE from Word Search I (79):
 *   WS1: board[r][c] vs word[index]     → one word
 *   WS2: board[r][c] vs trie child      → ALL words at once
 *
 * WHY TRIE:
 *   Shared prefix checked once.
 *   cell 'x' not in trie → skip entire subtree instantly.
 *   O(M×N × 4^L) not O(W × M×N × 4^L)
 *
 * 3 DESIGN DECISIONS:
 *   Set not List   → auto-deduplicates same word from multiple paths
 *   '#' not visited[][] → '#'-'a'=negative → no child match → auto-blocked
 *   store word at node → no StringBuilder → cleaner code
 *
 * CRITICAL: save original BEFORE marking '#'
 *   idx = original-'a' (not '#'-'a')
 *   UNCHOOSE uses original (not word char)
 *
 * TIME : O(M×N × 4^L) — L = max word length
 * SPACE: O(W×L) trie + O(L) stack
 */
public class WordSearchII {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
        String word = null;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (curr.children[idx] == null)
                    curr.children[idx] = new TrieNode();
                curr = curr.children[idx];
            }
            curr.isEnd = true;
            curr.word  = word;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        TrieNode root = buildTrie(words);
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                dfs(board, r, c, root, result);
        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, int r, int c,
                     TrieNode node, Set<String> result) {
        int m = board.length, n = board[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n) return;
        if (board[r][c] == '#') return;

        char original = board[r][c];          // save before '#'
        int idx = original - 'a';
        if (node.children[idx] == null) return;

        TrieNode next = node.children[idx];
        if (next.isEnd) result.add(next.word); // found — don't return

        board[r][c] = '#';                     // CHOOSE
        dfs(board, r+1, c,   next, result);    // EXPLORE
        dfs(board, r-1, c,   next, result);
        dfs(board, r,   c+1, next, result);
        dfs(board, r,   c-1, next, result);
        board[r][c] = original;                // UNCHOOSE
    }
}