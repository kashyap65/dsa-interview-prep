package com.kashyap.dsa.sliding_window;

import java.util.HashMap;

/**
 * PATTERN   : Sliding Window — Variable Size (Minimum Window)
 * DIFFICULTY: Hard
 * DATE      : Mar 2, 2026
 *
 * PROBLEM:
 * Given strings s and t, return the minimum window
 * substring of s containing all characters of t.
 *
 * TRICK SENTENCE:
 * "Expand right until all VIPs are inside.
 *  Shrink left to make venue as small as possible.
 *  Record smallest valid venue. Repeat."
 *
 * THE STORY:
 * Security guard at concert. VIPs = chars in t.
 * Expand guest list until all VIPs inside (formed==required).
 * Kick non-VIPs from entrance (shrink left).
 * The moment a VIP leaves → expand again.
 * Record smallest venue where all VIPs were present.
 *
 * THE 4 CORE LINES:
 * 1. Add s[right] to windowFreq (always expand)
 * 2. If tFreq char just became satisfied → formed++
 * 3. While formed==required → record, shrink left
 * 4. If removing left char breaks satisfaction → formed--
 *
 * KEY INSIGHT 1 — formed uses <=  not ==:
 *   formed++ only when count goes from "not enough" to
 *   "just enough". Never from "enough" to "excess".
 *   windowFreq.get(ch) <= tFreq.get(ch) catches exactly this.
 *
 * KEY INSIGHT 2 — formed-- uses < tFreq:
 *   formed-- only when count drops BELOW what t needs.
 *   Not when count hits 0 — t might need 2 of a char.
 *   windowFreq.get(ch) < tFreq.get(ch) catches exactly this.
 *
 * KEY INSIGHT 3 — shrink when VALID (opposite of usual):
 *   Normal sliding window: shrink when window BREAKS.
 *   Minimum window: shrink when window is VALID.
 *   We want the SMALLEST valid window → compress as tight
 *   as possible before it breaks.
 *
 * TIME : O(|s| + |t|) — each char added/removed at most once
 * SPACE: O(|s| + |t|) — two frequency maps
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length())
            return "";

        // Build frequency map for t
        HashMap<Character, Integer> tFreq = new HashMap<>();
        for (char ch : t.toCharArray()) {
            tFreq.put(ch, tFreq.getOrDefault(ch, 0) + 1);
        }

        int required = tFreq.size();  // unique chars needed
        int formed = 0;               // unique chars currently satisfied

        HashMap<Character, Integer> windowFreq = new HashMap<>();
        int left = 0, minLen = Integer.MAX_VALUE;
        String result = "";

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            // Expand: add right char to window
            windowFreq.put(ch, windowFreq.getOrDefault(ch, 0) + 1);

            // Check if this char just became satisfied
            if (tFreq.containsKey(ch) &&
                    windowFreq.get(ch).equals(tFreq.get(ch))) {
                formed++;
            }

            // Shrink from left while window is valid
            while (formed == required) {

                // Record if this is smallest window so far
                if ((right - left + 1) < minLen) {
                    minLen = right - left + 1;
                    result = s.substring(left, right + 1);
                }

                // Remove leftmost character
                char remove = s.charAt(left);
                windowFreq.put(remove, windowFreq.get(remove) - 1);

                // Check if removal broke satisfaction
                if (tFreq.containsKey(remove) &&
                        windowFreq.get(remove) < tFreq.get(remove)) {
                    formed--;
                }

                left++;
            }
        }

        return result;
    }
}