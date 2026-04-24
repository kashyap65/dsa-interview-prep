package com.kashyap.dsa.tries;

/**
 * PATTERN   : Trie — Prefix Matching
 * DIFFICULTY: Medium
 * DATE      : Apr 17, 2026
 * LEETCODE  : 648
 *
 * TRICK SENTENCE:
 * "Build Trie from roots. For each word: traverse char by char.
 *  First isEnd hit = shortest root → replace + break.
 *  No child = no root matches → keep original."
 *
 * WHY TRIE not HashMap:
 *   HashMap: O(words × roots × length) — check every root per word.
 *   Trie:    O(total chars in sentence) — one pass per word.
 *   Shared prefixes ("cat","ca","c") checked once not three times.
 *
 * KEY INSIGHT:
 *   "Shortest root" = FIRST isEnd encountered during traversal.
 *   Stop immediately (break) — don't look for longer roots.
 *
 * TWO STOPPING CONDITIONS in inner loop:
 *   1. node.children[idx]==null → no root with this prefix → break
 *   2. node.isEnd==true → shortest root found → replace + break
 *
 * CRITICAL BUGS:
 *   node.children[idx].isEnd → one level too deep ❌
 *   node.isEnd after moving to child → correct ✅
 *   Missing break → continues after finding root → wrong result ❌
 *
 * TIME : O(N×L) — N=words, L=avg word length
 * SPACE: O(R×L) — R=roots, L=avg root length (trie storage)
 */
public class ReplaceWords {

    public String replaceWords(String sentence, String[] dictionary) {
        TrieNode root = buildTrie(dictionary);

        String[] words = sentence.split(" ");

        for (int i = 0; i < words.length; i++) {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();

            for (char ch : words[i].toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) break; // no root matches

                prefix.append(ch);
                node = node.children[idx];

                if (node.isEnd) {                       // shortest root found
                    words[i] = prefix.toString();
                    break;                              // stop immediately
                }
            }
        }

        return String.join(" ", words);
    }

    private TrieNode buildTrie(String[] dictionary) {
        TrieNode root = new TrieNode();
        for (String word : dictionary) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (curr.children[idx] == null)
                    curr.children[idx] = new TrieNode();
                curr = curr.children[idx];
            }
            curr.isEnd = true;
        }
        return root;
    }
}