package com.kashyap.dsa.tries;

/**
 * PATTERN   : Trie + Recursive Search with Wildcard
 * DIFFICULTY: Medium
 * DATE      : Apr 16, 2026
 * LEETCODE  : 211
 *
 * TRICK SENTENCE:
 * "Trie insert as normal. Search: '.' → try all 26 children.
 *  Normal char → follow one child. Base: return node.isEnd."
 *
 * KEY INSIGHT — WHY RECURSIVE not ITERATIVE:
 *   Normal char: one path → iterative fine.
 *   '.' wildcard: up to 26 paths simultaneously.
 *   Recursion handles branching naturally.
 *   for(i=0..25) → try each non-null child → if any true → return true.
 *
 * BASE CASE: return node.isEnd (not return true)
 *   search("b") after addWord("bad"):
 *   reaches node_b, isEnd=false → correct: "b" not a word ✅
 *
 * TIME : O(L) addWord. O(26^L) worst search (all dots).
 * SPACE: O(L) per word in trie.
 */
class WordDictionary {

    private final TrieNode root;

    public WordDictionary() { root = new TrieNode(); }

    public void addWord(String word) {
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
        return searchFrom(root, word, 0);
    }

    private boolean searchFrom(TrieNode node,
                               String word, int index) {
        if (index == word.length()) return node.isEnd; // ← node.isEnd not true

        char ch = word.charAt(index);

        if (ch == '.') {
            // try ALL 26 children — '.' matches any letter
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null &&
                        searchFrom(node.children[i], word, index + 1))
                    return true;
            }
            return false;
        } else {
            // normal character — follow one child
            int idx = ch - 'a';
            if (node.children[idx] == null) return false;
            return searchFrom(node.children[idx], word, index + 1);
        }
    }
}
