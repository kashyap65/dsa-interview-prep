package com.kashyap.dsa.linked_list;

/**
 * PATTERN   : Linked List — Dummy Node + Two Pointers
 * DIFFICULTY: Easy
 * DATE      : Mar 6, 2026
 *
 * PROBLEM:
 * Merge two sorted linked lists into one sorted list.
 *
 * TRICK SENTENCE:
 * "Dummy node anchors the result.
 *  curr walks forward picking the smaller front.
 *  Attach remainder when one list runs out."
 *
 * ITERATIVE APPROACH:
 * - dummy + curr pointers
 * - while both lists have nodes: pick smaller, advance
 * - attach remaining list directly at end
 * - return dummy.next
 *
 * RECURSIVE APPROACH (3 slots):
 * Slot 1: l1==null → return l2, l2==null → return l1
 * Slot 2: pick smaller node
 * Slot 3: smaller.next = merge(smaller.next, other)
 *         return smaller
 *
 * WHY DUMMY NODE:
 * Don't know which list has the smaller head.
 * Dummy eliminates special case for first node.
 * dummy.next always = correct merged head.
 *
 * DRY RUN [1→3→5] + [2→4→6]:
 * Step1: 1<2 → attach 1, l1=3→5
 * Step2: 3>2 → attach 2, l2=4→6
 * Step3: 3<4 → attach 3, l1=5→null
 * Step4: 5>4 → attach 4, l2=6→null
 * Step5: 5<6 → attach 5, l1=null → loop ends
 * Remainder: attach l2(6→null)
 * Result: 1→2→3→4→5→6 ✅
 *
 * TIME : O(n+m) — visit each node once
 * SPACE: O(1)   iterative — only dummy + curr pointers
 *        O(n+m) recursive — call stack depth
 *
 * SIMILAR PROBLEMS:
 * - Merge K Sorted Lists (Hard) — extends this pattern
 * - Sort List — uses merge as subroutine
 * - Reorder List — uses reverse + merge
 */
public class MergeTwoSortedLists {

    // VERSION 1 — Iterative (Dummy Node)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // VERSION 2 — Recursive (3 Slots)
    public ListNode mergeRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;   // slot 1
        if (l2 == null) return l1;   // slot 1

        if (l1.val <= l2.val) {
            l1.next = mergeRecursive(l1.next, l2);  // slot 2+3
            return l1;
        } else {
            l2.next = mergeRecursive(l1, l2.next);  // slot 2+3
            return l2;
        }
    }
}