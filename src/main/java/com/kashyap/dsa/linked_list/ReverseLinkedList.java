package com.kashyap.dsa.linked_list;

/**
 * PATTERN   : Linked List — Iterative + Recursive Reverse
 * DIFFICULTY: Easy
 * DATE      : Mar 6, 2026
 *
 * PROBLEM:
 * Reverse a singly linked list.
 * Return the new head.
 *
 * TRICK SENTENCE:
 * "Save before you flip. Flip the arrow. Move forward.
 *  Recursive: flip on the way BACK using 3 slots."
 *
 * ITERATIVE — 3 POINTERS:
 * prev=null, curr=head
 * At each step: save next, flip arrow, move both forward.
 * When curr==null → prev is new head.
 *
 * RECURSIVE — 3 SLOTS:
 * Slot 1: base case  → null or single node → return head
 * Slot 2: trust call → reverseList(head.next) reverses the rest
 * Slot 3: work back  → head.next.next=head (flip)
 *                    → head.next=null (cut, become new tail)
 * newHead bubbles up unchanged — always the last node.
 *
 * KEY INSIGHT — ORDER IN SLOT 3:
 * head.next.next = head  FIRST  (flip before cut)
 * head.next = null       SECOND (cut after flip)
 * Reversed order → lose reference to head.next → NullPointerException
 *
 * BEFORE vs AFTER recursive call:
 * Work BEFORE → processes front to back (print forward)
 * Work AFTER  → processes back to front (reverse, print backward)
 * Reverse needs tail first → work goes AFTER recursive call.
 *
 * TIME : O(n) — both versions visit each node once
 * SPACE: O(1) iterative — only 3 pointer variables
 *        O(n) recursive — call stack depth = list length
 *
 * SIMILAR PROBLEMS:
 * - Palindrome Linked List (reverse second half)
 * - Reverse Linked List II (reverse a subrange)
 * - Reverse Nodes in K-Group (Hard)
 */
public class ReverseLinkedList {

    // VERSION 1 — Iterative
    public ListNode reverseIterative(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;  // save
            curr.next = prev;           // flip
            prev = curr;               // move prev
            curr = next;               // move curr
        }

        return prev;  // prev = new head
    }

    // VERSION 2 — Recursive
    public ListNode reverseRecursive(ListNode head) {
        // SLOT 1: base case
        if (head == null || head.next == null) return head;

        // SLOT 2: trust — reverses everything after head
        ListNode newHead = reverseRecursive(head.next);

        // SLOT 3: work on the way back
        head.next.next = head;  // flip: tail of reversed points to head
        head.next = null;       // cut: head becomes new tail

        return newHead;         // bubbles up unchanged
    }
}