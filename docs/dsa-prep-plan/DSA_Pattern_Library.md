# 🧠 DSA Pattern Library
> All 18 patterns with mental hooks, Java templates, and trick sentences.
> Every problem solved during prep links back to one of these patterns.

---

## How to Use This File
1. Before solving any problem — find the pattern here first
2. Re-read the **trick sentence** before you write a single line of code
3. Use the **template** as your starting skeleton
4. After solving — annotate what variant you used

---

## Pattern 1 — Sliding Window

### 🪝 Mental Hook
> Imagine a train window. As the train moves, something new enters from the right, something old exits from the left. You never re-examine what you already passed.

### 🎯 Spot It When You See
- "subarray / substring"
- "consecutive elements"
- "maximum/minimum of a window of size K"
- "longest/shortest substring that satisfies condition X"

### 💡 Trick Sentence
> **Right always expands. Left only moves when the window breaks a rule.**

### 📐 Template — Fixed Window (size K given)
```java
int left = 0, result = 0, windowSum = 0;
for (int right = 0; right < arr.length; right++) {
    windowSum += arr[right];                    // ADD right element

    if (right - left + 1 == K) {               // WINDOW IS FULL
        result = Math.max(result, windowSum);
        windowSum -= arr[left];                // REMOVE left element
        left++;                                // SHRINK from left
    }
}
```

### 📐 Template — Variable Window (find longest/shortest)
```java
int left = 0, result = 0;
// state: Map / Set / counter depending on problem

for (int right = 0; right < arr.length; right++) {
    // 1. EXPAND: add arr[right] to state

    while (/* condition VIOLATED */) {
        // 2. SHRINK: remove arr[left] from state
        left++;
    }
    // 3. RECORD: window [left..right] is valid
    result = Math.max(result, right - left + 1);
}
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Maximum Average Subarray I | ⏳ |
| Easy | Best Time to Buy and Sell Stock | ⏳ |
| Medium | Longest Substring Without Repeating Characters | ⏳ |
| Medium | Permutation in String | ⏳ |
| Medium | Fruit Into Baskets | ⏳ |
| Hard | Minimum Window Substring | ⏳ |
| Hard | Sliding Window Maximum | ⏳ |
| Hard | Substring with Concatenation of All Words | ⏳ |

---

## Pattern 2 — Two Pointers

### 🪝 Mental Hook
> Two people walking toward each other from opposite ends of a corridor. They meet in the middle. Neither needs to backtrack.

### 🎯 Spot It When You See
- Sorted array + find pair/triplet with target sum
- Palindrome check
- Remove duplicates in-place
- "closest to target"

### 💡 Trick Sentence
> **One pointer from each end. They only move inward. They never cross.**

### 📐 Template — Opposite Ends
```java
int left = 0, right = arr.length - 1;
while (left < right) {
    int sum = arr[left] + arr[right];
    if (sum == target) {
        // found
    } else if (sum < target) {
        left++;
    } else {
        right--;
    }
}
```

### 📐 Template — Fast & Slow (Cycle / Middle finding)
```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) // cycle detected
}
// When fast reaches end, slow is at middle
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Valid Palindrome | ⏳ |
| Easy | Move Zeroes | ⏳ |
| Easy | Reverse String | ⏳ |
| Medium | 3Sum | ⏳ |
| Medium | Container With Most Water | ⏳ |
| Medium | Sort Colors | ⏳ |
| Hard | Trapping Rain Water | ⏳ |
| Hard | 4Sum | ⏳ |

---

## Pattern 3 — Binary Search

### 🪝 Mental Hook
> You're looking for a name in a phonebook. You open to the middle. Too early? Tear off the left half. Too late? Tear off the right half. You never look at the same page twice.

### 🎯 Spot It When You See
- Sorted array + find element
- "minimum/maximum that satisfies condition" (binary search on answer)
- Rotated sorted array
- "Find first/last position"

### 💡 Trick Sentence
> **Every iteration eliminates HALF. Define your invariant before you write the loop.**

### 📐 Template — Classic
```java
int left = 0, right = arr.length - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;  // avoid overflow
    if (arr[mid] == target) return mid;
    else if (arr[mid] < target) left = mid + 1;
    else right = mid - 1;
}
return -1;
```

### 📐 Template — Binary Search on Answer Space
```java
int left = MIN_POSSIBLE, right = MAX_POSSIBLE;
while (left < right) {
    int mid = left + (right - left) / 2;
    if (canAchieve(mid)) {
        right = mid;      // try smaller (for minimization)
    } else {
        left = mid + 1;
    }
}
return left;
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Binary Search | ⏳ |
| Easy | First Bad Version | ⏳ |
| Medium | Search in Rotated Sorted Array | ⏳ |
| Medium | Find Peak Element | ⏳ |
| Medium | Koko Eating Bananas | ⏳ |
| Hard | Median of Two Sorted Arrays | ⏳ |
| Hard | Find in Mountain Array | ⏳ |

---

## Pattern 4 — Prefix Sum

### 🪝 Mental Hook
> Pre-cook your sums once at the start. Then any "what's the sum from index i to j?" is just two lookups and a subtraction — O(1) instead of O(n).

### 🎯 Spot It When You See
- Range sum queries
- "subarray sum equals K"
- 2D grid sum queries

### 💡 Trick Sentence
> **Build prefix once. Query in O(1). sum(i,j) = prefix[j+1] - prefix[i]**

### 📐 Template
```java
int[] prefix = new int[arr.length + 1];
for (int i = 0; i < arr.length; i++) {
    prefix[i + 1] = prefix[i] + arr[i];
}
// Range sum [left, right] inclusive:
int rangeSum = prefix[right + 1] - prefix[left];
```

---

## Pattern 5 — Monotonic Stack

### 🪝 Mental Hook
> You're at a concert. Taller people entering from the right make everyone shorter behind them irrelevant. Pop the shorter ones — they'll never be the answer again.

### 🎯 Spot It When You See
- "Next greater element"
- "Previous smaller element"
- "Largest rectangle / area"
- Stock span problems

### 💡 Trick Sentence
> **Pop everything that's been beaten. Only useful future candidates stay on the stack.**

### 📐 Template — Next Greater Element
```java
Stack<Integer> stack = new Stack<>(); // stores indices
int[] result = new int[arr.length];
Arrays.fill(result, -1);

for (int i = 0; i < arr.length; i++) {
    while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
        result[stack.pop()] = arr[i];  // arr[i] is next greater
    }
    stack.push(i);
}
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Valid Parentheses | ⏳ |
| Easy | Min Stack | ⏳ |
| Medium | Daily Temperatures | ⏳ |
| Medium | Decode String | ⏳ |
| Medium | Asteroid Collision | ⏳ |
| Hard | Largest Rectangle in Histogram | ⏳ |
| Hard | Basic Calculator II | ⏳ |

---

## Pattern 6 — BFS (Breadth-First Search)

### 🪝 Mental Hook
> You drop a stone in water. The ripples spread outward in rings — one ring at a time. BFS processes nodes ring by ring (level by level). This guarantees the shortest path to any node.

### 🎯 Spot It When You See
- Shortest path in unweighted graph
- "minimum steps / minimum turns"
- Level-order traversal
- Multi-source spread (rotting oranges, walls)

### 💡 Trick Sentence
> **Use a Queue. Process level by level. First time you reach a node = shortest path.**

### 📐 Template
```java
Queue<Integer> queue = new LinkedList<>();
Set<Integer> visited = new HashSet<>();
queue.offer(start);
visited.add(start);
int steps = 0;

while (!queue.isEmpty()) {
    int size = queue.size();  // process one full level
    for (int i = 0; i < size; i++) {
        int node = queue.poll();
        if (node == target) return steps;
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
    }
    steps++;
}
```

---

## Pattern 7 — DFS + Backtracking

### 🪝 Mental Hook
> You're in a maze. You go as deep as possible down one path. Hit a dead end? Backtrack to the last junction and try the next turn. Every path gets explored exactly once.

### 🎯 Spot It When You See
- "All combinations / permutations / subsets"
- "Find all paths"
- "Is there any valid configuration?"

### 💡 Trick Sentence
> **Choose. Explore. Unchoose. (Make choice → recurse → undo choice)**

### 📐 Template
```java
void backtrack(List<List<Integer>> result, List<Integer> current, int start, int[] nums) {
    result.add(new ArrayList<>(current));  // record state

    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);              // CHOOSE
        backtrack(result, current, i + 1, nums); // EXPLORE
        current.remove(current.size() - 1); // UNCHOOSE
    }
}
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Letter Case Permutation | ⏳ |
| Medium | Combination Sum | ⏳ |
| Medium | Permutations | ⏳ |
| Medium | Subsets | ⏳ |
| Medium | Word Search | ⏳ |
| Hard | N-Queens | ⏳ |
| Hard | Sudoku Solver | ⏳ |
| Hard | Palindrome Partitioning | ⏳ |

---

## Pattern 8 — Union-Find (Disjoint Set Union)

### 🪝 Mental Hook
> Every person belongs to a group. Each group has a leader. To check if two people are in the same group — find both their leaders. Same leader = same group. To merge two groups — make one leader point to the other.

### 🎯 Spot It When You See
- "Connected components"
- "Are X and Y in the same group?"
- "Number of groups after merges"
- Cycle detection in undirected graph

### 💡 Trick Sentence
> **Find your root. Merge roots. Path-compress on every find.**

### 📐 Template
```java
int[] parent, rank;

void init(int n) {
    parent = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; i++) parent[i] = i;
}

int find(int x) {
    if (parent[x] != x) parent[x] = find(parent[x]); // path compression
    return parent[x];
}

boolean union(int x, int y) {
    int px = find(x), py = find(y);
    if (px == py) return false; // already connected
    if (rank[px] < rank[py]) parent[px] = py;
    else if (rank[px] > rank[py]) parent[py] = px;
    else { parent[py] = px; rank[px]++; }
    return true;
}
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Number of Connected Components | ⏳ |
| Medium | Accounts Merge | ⏳ |
| Medium | Redundant Connection | ⏳ |
| Hard | Swim in Rising Water | ⏳ |

---

## Pattern 9 — Topological Sort

### 🪝 Mental Hook
> You're getting dressed. Underwear before pants. Socks before shoes. Some tasks depend on others being done first. Topological sort finds an order where every dependency is satisfied.

### 🎯 Spot It When You See
- "Prerequisites" / "dependencies"
- "Is there a valid order?"
- Directed graph + cycle detection

### 💡 Trick Sentence
> **In-degree 0 = no dependencies = safe to process. Remove it, reduce neighbors' in-degree, repeat.**

### 📐 Template — Kahn's Algorithm (BFS)
```java
int[] inDegree = new int[n];
List<List<Integer>> graph = new ArrayList<>();
// build graph and inDegree...

Queue<Integer> queue = new LinkedList<>();
for (int i = 0; i < n; i++)
    if (inDegree[i] == 0) queue.offer(i);

List<Integer> order = new ArrayList<>();
while (!queue.isEmpty()) {
    int node = queue.poll();
    order.add(node);
    for (int neighbor : graph.get(node)) {
        if (--inDegree[neighbor] == 0) queue.offer(neighbor);
    }
}
return order.size() == n ? order : new ArrayList<>(); // empty = cycle exists
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Medium | Course Schedule | ⏳ |
| Medium | Course Schedule II | ⏳ |
| Hard | Alien Dictionary | ⏳ |
| Hard | Sequence Reconstruction | ⏳ |

---

## Pattern 10 — Heap / Top-K

### 🪝 Mental Hook
> You're a talent scout watching 1000 auditions. You only need to remember the top 3. Keep a "worst of the best" list of size 3. If someone new is better than your worst — swap them in, kick the worst out.

### 🎯 Spot It When You See
- "K largest / K smallest / K most frequent"
- "Median of a stream"
- "Merge K sorted lists"
- Scheduling problems

### 💡 Trick Sentence
> **For K largest: min-heap of size K. For K smallest: max-heap of size K. Counterintuitive but correct.**

### 📐 Template — K Largest Elements
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // min-heap

for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) minHeap.poll(); // remove smallest
}
// minHeap now contains K largest elements
// minHeap.peek() = Kth largest
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Last Stone Weight | ⏳ |
| Easy | Kth Largest Element in Stream | ⏳ |
| Medium | Kth Largest Element in Array | ⏳ |
| Medium | K Closest Points to Origin | ⏳ |
| Medium | Task Scheduler | ⏳ |
| Hard | Find Median from Data Stream | ⏳ |
| Hard | Merge K Sorted Lists | ⏳ |
| Hard | IPO | ⏳ |

---

## Pattern 11 — Dynamic Programming (1D)

### 🪝 Mental Hook
> You're climbing stairs. To know how many ways to reach step 10, you need step 9 and step 8. Those needed step 8 and 7. But step 8 is shared — compute it once, store it, never recompute.

### 🎯 Spot It When You See
- "How many ways..."
- "Maximum/minimum cost to reach..."
- "Can you achieve...?"
- Overlapping subproblems (you keep computing the same thing)

### 💡 Trick Sentence
> **dp[i] = best answer considering the first i elements. Define base case, then transition.**

### 📐 Template
```java
int[] dp = new int[n + 1];
dp[0] = BASE_CASE;
dp[1] = BASE_CASE;

for (int i = 2; i <= n; i++) {
    dp[i] = /* transition using dp[i-1], dp[i-2], etc. */;
}
return dp[n];
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Climbing Stairs | ⏳ |
| Easy | House Robber | ⏳ |
| Easy | Maximum Subarray | ⏳ |
| Medium | Coin Change | ⏳ |
| Medium | Longest Increasing Subsequence | ⏳ |
| Medium | Word Break | ⏳ |
| Hard | Edit Distance | ⏳ |
| Hard | Regular Expression Matching | ⏳ |

---

## Pattern 12 — Dynamic Programming (2D)

### 🪝 Mental Hook
> Two strings facing each other. You build a table where cell (i,j) = best answer using first i characters of string1 and first j characters of string2. Fill row by row.

### 🎯 Spot It When You See
- Two sequences (strings, arrays)
- "Longest common subsequence / substring"
- "Edit distance"
- Grid traversal with min/max cost

### 💡 Trick Sentence
> **dp[i][j] = answer for first i of one input, first j of another. Transition comes from neighbors.**

### 📐 Template
```java
int[][] dp = new int[m + 1][n + 1];
// base cases: dp[0][j] = 0, dp[i][0] = 0

for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (match condition) {
            dp[i][j] = dp[i-1][j-1] + 1;
        } else {
            dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
    }
}
return dp[m][n];
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Medium | Unique Paths | ⏳ |
| Medium | Minimum Path Sum | ⏳ |
| Medium | Longest Common Subsequence | ⏳ |
| Hard | Edit Distance | ⏳ |
| Hard | Burst Balloons | ⏳ |

---

## Pattern 13 — DP: State Machine

### 🪝 Mental Hook
> A traffic light. Red → Green → Yellow → Red. You're always in exactly one state. Your action depends on your current state. DP tracks the best value for each state at each step.

### 🎯 Spot It When You See
- Stock buy/sell with constraints (cooldown, fee, at most K transactions)
- "You can be in state A or state B at each step"

### 💡 Trick Sentence
> **Draw your states first. Transitions are your recurrence. One variable per state.**

### 📐 Template — Stock with Cooldown
```java
int hold = Integer.MIN_VALUE; // max profit while holding stock
int sold = 0;                 // max profit on day just sold
int rest = 0;                 // max profit on cooldown/rest day

for (int price : prices) {
    int prevHold = hold, prevSold = sold, prevRest = rest;
    hold = Math.max(prevHold, prevRest - price); // keep holding OR buy
    sold = prevHold + price;                      // sell today
    rest = Math.max(prevRest, prevSold);          // rest or continue resting
}
return Math.max(sold, rest);
```

---

## Pattern 14 — Trie

### 🪝 Mental Hook
> A filing cabinet organized by first letter, then second letter, then third... Every path from root to a marked node spells out a word. Prefix search = just walk down the path.

### 🎯 Spot It When You See
- Autocomplete / search suggestions
- "Words starting with prefix..."
- Dictionary word problems
- Multiple string searches at once

### 💡 Trick Sentence
> **When prefix matching matters, grow a tree — not a HashMap.**

### 📐 Template
```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) return false;
            node = node.children[idx];
        }
        return node.isEnd;
    }
}
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Implement Trie | ⏳ |
| Medium | Replace Words | ⏳ |
| Medium | Map Sum Pairs | ⏳ |
| Hard | Word Search II | ⏳ |
| Hard | Design Search Autocomplete System | ⏳ |

---

## Pattern 15 — Intervals

### 🪝 Mental Hook
> You have a list of meetings on a calendar. Sort them by start time. To merge two meetings — check if one starts before the other ends. If yes, extend. If no, they're separate.

### 🎯 Spot It When You See
- "Merge overlapping intervals"
- "Minimum number of meeting rooms"
- "Insert interval into sorted list"
- "Maximum overlap"

### 💡 Trick Sentence
> **Sort by start. Two intervals overlap if next.start <= current.end.**

### 📐 Template — Merge Intervals
```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
List<int[]> result = new ArrayList<>();
int[] current = intervals[0];

for (int i = 1; i < intervals.length; i++) {
    if (intervals[i][0] <= current[1]) {           // overlap
        current[1] = Math.max(current[1], intervals[i][1]); // merge
    } else {
        result.add(current);
        current = intervals[i];
    }
}
result.add(current);
```

### 📋 Problems
| Level | Problem | Status |
|-------|---------|--------|
| Easy | Meeting Rooms I | ⏳ |
| Medium | Merge Intervals | ⏳ |
| Medium | Insert Interval | ⏳ |
| Medium | Meeting Rooms II | ⏳ |
| Hard | Non-overlapping Intervals | ⏳ |
| Hard | Employee Free Time | ⏳ |

---

## Pattern 16 — Greedy

### 🪝 Mental Hook
> You're collecting coins on a path. At each step, grab the biggest coin in reach. Trust that grabbing locally best choices leads to the globally best result — but you must be able to PROVE it.

### 🎯 Spot It When You See
- Scheduling / activity selection
- "Minimum number of X to cover Y"
- Jump game variants
- Huffman coding style problems

### 💡 Trick Sentence
> **Make the locally best choice. If you can prove it never hurts globally — it's greedy. Otherwise it's DP.**

---

## Pattern 17 — Divide & Conquer

### 🪝 Mental Hook
> Merge sort. Split the array in half. Sort each half. Merge the two sorted halves. The merge is where the magic (and often the answer) happens.

### 💡 Trick Sentence
> **Split in half. Solve each half recursively. Merge results. Master Theorem gives you complexity.**

---

## Pattern 18 — Fast & Slow Pointers

### 🪝 Mental Hook
> Two runners on a circular track. The fast one runs at 2x speed. If there's a loop, they will eventually meet. If there's no loop, the fast one falls off the end.

### 💡 Trick Sentence
> **If there's a cycle, fast and slow WILL meet. When they do, reset one to head — they meet again at cycle start.**

### 📐 Template — Find Cycle Start
```java
ListNode slow = head, fast = head;
// Phase 1: detect cycle
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) break;
}
if (fast == null || fast.next == null) return null; // no cycle

// Phase 2: find cycle start
slow = head;
while (slow != fast) {
    slow = slow.next;
    fast = fast.next;
}
return slow; // cycle start node
```

---

*Last updated: March 2026 | Problems update as they are solved and approved*
