# 🚀 DSA Interview Prep — Kashyap Pandey

> **Target:** Principal / Staff SDE at MAANG · Amex · eBay · Visa · Uber  
> **Timeline:** March 1 – May 31, 2026 (~246 hours of focused prep)  
> **Stack:** Java · Spring Boot · Microservices · Spring AI

---

## 👨‍💻 About

Senior Software Engineer with **11+ years** in Java/J2EE, Spring Boot, Microservices across **Wells Fargo, Morgan Stanley, DBS Bank, Barclays**. This repository documents my structured 3-month preparation journey toward Principal/Staff Engineer roles.

Every solution here was:
1. Solved from scratch
2. Reviewed for correctness, time/space complexity, and code quality
3. Approved before being committed

---

## 📁 Repository Structure

```
dsa-interview-prep/
│
├── docs/
│   ├── prep-plan/
│   │   ├── 3_Month_Dated_Plan.md       ← Full day-by-day plan (Mar–May 2026)
│   │   ├── DSA_Pattern_Library.md      ← 18 patterns with templates + trick sentences
│   │   └── Behavioral_Stories.md       ← STAR-I story bank (10 stories)
│   │
│   └── system-design/
│       ├── 01_Rate_Limiter.md
│       ├── 02_URL_Shortener.md
│       └── ...                         ← One file per design session
│
└── src/main/java/com/kashyap/dsa/
    ├── sliding_window/
    ├── two_pointers/
    ├── binary_search/
    ├── linked_list/
    ├── stacks/
    ├── trees/
    ├── graphs/
    ├── dynamic_programming/
    ├── backtracking/
    ├── heaps/
    ├── intervals/
    ├── tries/
    └── union_find/
```

---

## 📊 Progress Tracker

### DSA Problems

| Pattern | Easy | Medium | Hard | Total |
|---------|------|--------|------|-------|
| Sliding Window | 0/2 | 0/3 | 0/3 | 0/8 |
| Two Pointers | 0/3 | 0/3 | 0/2 | 0/8 |
| Binary Search | 0/2 | 0/3 | 0/2 | 0/7 |
| Linked List | 0/3 | 0/3 | 0/2 | 0/8 |
| Stacks | 0/2 | 0/3 | 0/2 | 0/7 |
| Trees | 0/3 | 0/3 | 0/2 | 0/8 |
| Graphs | 0/1 | 0/4 | 0/3 | 0/8 |
| Dynamic Programming | 0/3 | 0/4 | 0/4 | 0/11 |
| Backtracking | 0/1 | 0/4 | 0/3 | 0/8 |
| Heaps | 0/2 | 0/3 | 0/3 | 0/8 |
| Intervals | 0/1 | 0/3 | 0/2 | 0/6 |
| Tries | 0/1 | 0/2 | 0/2 | 0/5 |
| Union Find | 0/1 | 0/2 | 0/1 | 0/4 |
| **TOTAL** | **0/25** | **0/40** | **0/31** | **0/96** |

### System Designs

| # | Topic | Company Focus | Status |
|---|-------|--------------|--------|
| 1 | Rate Limiter | All | ⏳ Pending |
| 2 | URL Shortener | All | ⏳ Pending |
| 3 | Notification System | Amazon | ⏳ Pending |
| 4 | Key-Value Store | All | ⏳ Pending |
| 5 | Kafka / Message Queue | All | ⏳ Pending |
| 6 | Uber / Ride Sharing | Uber | ⏳ Pending |
| 7 | Payment System | Visa · Amex | ⏳ Pending |
| 8 | E-Commerce Marketplace | eBay | ⏳ Pending |
| 9 | Fraud Detection | Visa · Amex | ⏳ Pending |
| 10 | AI Copilot / RAG System | All | ⏳ Pending |

---

## 🧠 DSA Pattern Quick Reference

| # | Pattern | One-Line Trick |
|---|---------|----------------|
| 1 | Sliding Window | Right expands always. Left shrinks only when window breaks a rule. |
| 2 | Two Pointers | One from each end — meet in the middle. |
| 3 | Binary Search | Every step, eliminate HALF. Never re-examine the eliminated side. |
| 4 | Fast & Slow Pointers | If there's a cycle, they will meet. |
| 5 | Monotonic Stack | Pop everything that's been beaten. Only useful candidates stay. |
| 6 | BFS | Level by level. Use a Queue. Guarantees shortest path (unweighted). |
| 7 | DFS + Backtracking | Go deep. Undo your choice. Try the next branch. |
| 8 | Union-Find | Find your leader. Merge leaders. Path compress everything. |
| 9 | Topological Sort | No incoming edges = ready to process. Remove and repeat. |
| 10 | Heap / Top-K | When you need the K best at any moment, keep a heap of size K. |
| 11 | DP 1D | dp[i] = best answer considering elements up to index i. |
| 12 | DP 2D | dp[i][j] = best answer using first i of one thing, first j of another. |
| 13 | DP State Machine | Draw your states. Transitions between states are your recurrence. |
| 14 | Trie | When prefix matching matters, grow a tree — not a HashMap. |
| 15 | Intervals | Sort by start time. Overlap if next.start <= current.end. |
| 16 | Prefix Sum | Pre-cook sums once so any range query is O(1). |
| 17 | Greedy | Make the locally best choice and prove it's globally optimal. |
| 18 | Divide & Conquer | Split in half. Solve each half. Merge the results. |

---

## 🗓️ Prep Schedule

| Month | Theme | Hours |
|-------|-------|-------|
| March 2026 | DSA Foundations + System Design Intro | ~85 hrs |
| April 2026 | Advanced DSA + System Design at Scale | ~85 hrs |
| May 2026 | AI Skills + Company-Specific + Mock Loops | ~76 hrs |
| **Total** | | **~246 hrs** |

Weekdays: 2 hrs/day · Saturdays: 5 hrs · Sundays: 5 hrs

---

## 📌 Commit Convention

Every approved solution is committed as:
```
✅ [pattern] ClassName — Difficulty — Date
```
Example:
```
✅ [sliding_window] LongestSubstringNoRepeat — Medium — Mar 3
✅ [system_design] URL_Shortener.md — Mar 14
```

---

*Prepared with structured AI-assisted mentorship | 2026*
