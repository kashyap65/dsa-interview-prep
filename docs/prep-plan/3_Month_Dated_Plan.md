# 🎯 3-Month Principal/Staff SDE Interview Prep Plan
### Kashyap Pandey | Target: MAANG, Amex, eBay, Visa, Uber | Lead/Principal SDE

---

## 📐 Your Weekly Schedule

| Day | Time | Hours | Focus |
|-----|------|-------|-------|
| Mon–Fri | Evening (2 hrs) | 2 | DSA / Coding / System Design |
| Saturday | Dedicated | 5 | Deep Topic (System Design or AI or Project) |
| Sunday | Dedicated | 5 | Mock Interviews + Review + Weak Areas |
| **Total/week** | | **20 hrs** | |
| **Total 3 months** | | **~240 hrs** | |

---

## 🗓️ MONTH 1: FOUNDATIONS + DSA MASTERY
**Theme: Build the muscle. Lock in patterns.**

### Week 1–2 | DSA Core Patterns
**Weekday sessions (2 hrs each):**
- Mon: Arrays, Sliding Window, Two Pointers
- Tue: Linked Lists (reverse, detect cycle, merge)
- Wed: Stacks and Queues (monotonic stack, bracket problems)
- Thu: Binary Search and variants
- Fri: Practice session — solve 4 problems mixing above

**Weekend (5+5 hrs):**
- Sat: System Design intro — What is a distributed system? CAP theorem, consistency models
- Sun: Mock coding interview (45 min) + System Design discussion (45 min) + review

**Target problems this fortnight:** 30–40 (LeetCode Easy + Medium)

---

### Week 3–4 | DSA Intermediate + Trees/Graphs
**Weekday sessions:**
- Mon: Binary Trees (traversals, height, LCA)
- Tue: BST (insert, delete, validate, kth smallest)
- Wed: Graph traversal (BFS, DFS, topological sort)
- Thu: Dynamic Programming — memoization fundamentals (Fibonacci, climbing stairs, knapsack)
- Fri: Mixed practice — 4 problems

**Weekend:**
- Sat: System Design Deep Dive — URL Shortener design (HLD + LLD)
- Sun: Mock — Code (graph/tree problem) + System Design (discuss your design)

**Target problems this fortnight:** 40–50

**Month 1 Milestone:** Comfortable solving Easy in <10 min, Medium in <25 min. Can explain URL Shortener, Rate Limiter design end-to-end.

---

## 🗓️ MONTH 2: ADVANCED DSA + SYSTEM DESIGN DEEP DIVE
**Theme: Go wide and deep. Think at scale.**

### Week 5–6 | Advanced DSA
**Weekday sessions:**
- Mon: DP — Subsequences (LCS, LIS, Edit Distance)
- Tue: DP — Grids and Paths (Unique Paths, Min Path Sum)
- Wed: Heaps and Priority Queues (K largest, merge K sorted lists, median stream)
- Thu: Tries (word search, autocomplete)
- Fri: Practice — 4 problems, focus on medium/hard

**Weekend:**
- Sat: System Design — Design Uber/Lyft (geolocation, ride matching, surge pricing, real-time tracking)
- Sun: Mock full loop — Coding (45 min) + System Design (45 min) + Behavioral (15 min)

---

### Week 7–8 | Concurrency + Distributed Systems DSA
**Weekday sessions:**
- Mon: Backtracking (permutations, combinations, N-Queens, Sudoku)
- Tue: Intervals (merge, insert, meeting rooms)
- Wed: Bit Manipulation + Math tricks
- Thu: Advanced Graphs — Dijkstra, Bellman-Ford, Union-Find
- Fri: Mixed hard problems — simulate interview conditions

**Weekend:**
- Sat: System Design — Design Kafka / Message Queue from scratch (your strength — go deep)
- Sun: Mock — code 2 problems (35+35 min) + behavioral + system design review

**Month 2 Milestone:** Solving 60–70% of Mediums confidently. Can design Uber, Kafka, Twitter Feed systems with component reasoning.

---

## 🗓️ MONTH 3: AI SKILLS + MOCK LOOPS + COMPANY-SPECIFIC PREP
**Theme: Company-specific. Polish. Perform.**

### Week 9–10 | Spring AI + LLM Engineering
**Weekday sessions:**
- Mon: Spring AI fundamentals — ChatClient, Models API, Prompt Templates
- Tue: RAG (Retrieval-Augmented Generation) — Vector stores, embeddings, chunking strategies
- Wed: Tool/Function calling with Spring AI, structured output parsing
- Thu: Build mini project — Intelligent Document Classifier using Spring AI (tie to ICMP experience)
- Fri: LLM System Design — Design an AI Copilot for internal banking queries

**Weekend:**
- Sat: Full AI project build — Document Q&A system with Spring AI + pgvector
- Sun: Mock — AI/ML system design interview + behavioral interview

---

### Week 11–12 | Company-Specific Prep + Final Mocks
**Focus split per company:**

#### Google / Meta
- Coding: Hard DP, Graph, Trie problems
- System Design: Google Drive, Instagram Feed, WhatsApp
- Behavioral: Leadership principles, impact at scale

#### Amazon
- 16 Leadership Principles — prepare 2 STAR stories per principle
- System Design: Amazon Cart, Order Management, Notification System
- Coding: Medium DP, Trees, Sliding Window

#### Uber / eBay / Visa / Amex
- System Design: Payments system (Visa/Amex), Fraud detection, Marketplace (eBay), Real-time pricing (Uber)
- Coding: Medium difficulty with strong correctness
- Behavioral: Cross-team collaboration, owning large initiatives

**Weekend (Week 11):**
- Sat: Full 4-hour mock loop — exactly like a real interview day
- Sun: Debrief + reinforce weak areas

**Weekend (Week 12):**
- Sat: Final system design marathon — 3 full designs back to back
- Sun: Final behavioral prep + resume walkthrough mock

---

## 📚 TOPIC REFERENCE GUIDE

### DSA Topics by Priority

| Priority | Topic | Key Patterns |
|----------|-------|-------------|
| P0 | Arrays / Strings | Sliding window, two pointers, prefix sum |
| P0 | Trees | BFS/DFS, LCA, level order, path sum |
| P0 | Dynamic Programming | Memo, tabulation, state transitions |
| P0 | Graphs | BFS/DFS, topological sort, Union-Find, Dijkstra |
| P1 | Heaps | K-th element, merge streams, median |
| P1 | Backtracking | Permutation, combination, constraints |
| P1 | Binary Search | Search in rotated, answer search (on value space) |
| P2 | Tries | Autocomplete, word search |
| P2 | Bit Manipulation | XOR tricks, single number, power of 2 |

---

### System Design Topics (Your Designs)

| System | Key Components |
|--------|----------------|
| URL Shortener | Hash, DB, Cache, CDN, Rate limiting |
| Messaging Queue (Kafka) | Partitioning, replication, consumer groups, offsets |
| Ride Sharing (Uber) | Geo-index (QuadTree/H3), WebSockets, surge, ETA |
| Payment System (Visa/Amex) | Idempotency, 2-phase commit, fraud, reconciliation |
| E-Commerce (eBay) | Product catalog, inventory, cart, order saga |
| Feed System (Twitter) | Fan-out on write vs read, ranking, CDN |
| Document Platform (your ICMP) | Async pipeline, Kafka, OCR services, metadata store |
| Notification System | Multi-channel, retry, dedup, priority queues |
| Fraud Detection | Real-time ML scoring, feature store, rules engine |
| AI Copilot / RAG System | Embedding, vector store, retrieval, LLM, caching |

---

### Spring AI Skill Set Plan

**Level 1 — Core API:**
- ChatClient and ChatModel
- Prompt / PromptTemplate
- Message types (System, User, Assistant)
- Streaming responses

**Level 2 — RAG:**
- EmbeddingModel
- VectorStore (PGVector, Chroma, Pinecone)
- QuestionAnswerAdvisor
- DocumentReader + TextSplitter + ETL pipeline

**Level 3 — Agentic / Tool Use:**
- @Tool annotation (function calling)
- ToolCallback and ToolCallbackProvider
- Multi-step reasoning chains
- Structured output (BeanOutputConverter)

**Level 4 — Production:**
- Caching (prompt + response)
- Observability with Micrometer
- Model evaluation and testing
- Retry, timeout, fallback patterns

---

## 💬 BEHAVIORAL INTERVIEW FRAMEWORK

Use the **STAR-I** format: Situation → Task → Action → Result → **Impact at Scale**

### Must-Prepare Stories (map to your experience):
1. **Led a complex technical initiative** → ICMP 19-service architecture
2. **Drove AI/ML integration** → Spring AI integration at Wells Fargo
3. **Resolved a critical production incident** → any incident story with metrics
4. **Disagreed with technical direction and advocated for change** → your design decisions
5. **Mentored or grew team members** → your 2017–present mentoring
6. **Delivered under tight deadlines / regulatory pressure** → Morgan Stanley regulatory audits
7. **Made a data-driven technical decision** → Kafka pipeline design choice
8. **Cross-team collaboration** → liaison between Business and Technology (ICMP)
9. **Took ownership of a failing system** → any stabilization story
10. **Innovation that had business impact** → Test Harness Tool (Barclays, award-winning)

---

## 📋 HOW WE WORK TOGETHER (DAILY SESSIONS)

### Structure for each session:

**Weekday (2 hrs):**
- 0:00–0:05 → Warm-up: I give you 1 concept quiz
- 0:05–0:45 → Solve 2 coding problems with me (I give problems, you solve, I give feedback)
- 0:45–1:30 → Concept deep dive (theory, patterns, code)
- 1:30–2:00 → Review + summary notes

**Weekend (5 hrs):**
- 0:00–1:30 → System Design session (I present problem, you design, I give feedback)
- 1:30–3:00 → Coding (3–4 problems, increasing difficulty)
- 3:00–4:00 → Topic deep dive (Spring AI / specific concept)
- 4:00–5:00 → Mock interview OR behavioral practice OR project work

### To start a session, just say:
> "Let's start today's session — [topic]" or "Mock interview time"

I will:
- Give you problems one at a time
- Wait for your solution
- Give structured feedback (correctness, time complexity, code quality, edge cases)
- Explain optimal approaches if you miss them
- Track your weak areas across sessions

---

## 🎯 SUCCESS METRICS (CHECK AT END OF EACH MONTH)

**Month 1:**
- [ ] 80+ problems solved (Easy + Medium)
- [ ] Can explain 5 core DSA patterns cold
- [ ] Can design URL Shortener and Rate Limiter

**Month 2:**
- [ ] 160+ total problems solved
- [ ] 60% Medium success rate under time pressure
- [ ] Can design Kafka, Uber, Payment System with trade-off reasoning

**Month 3:**
- [ ] 220+ total problems, 10+ Hard attempted
- [ ] 10 behavioral STAR stories polished
- [ ] Spring AI project built and explainable
- [ ] 3+ full mock loops completed
- [ ] Resume tailored per company — ready to apply

---

## 🚀 QUICK START — TODAY

To begin right now, just say one of:
- **"Start DSA Session — Arrays"** → I'll give you 2 problems to solve
- **"System Design: URL Shortener"** → I'll play interviewer
- **"Review my resume changes"** → We discuss the new resume
- **"Behavioral mock — Amazon LP"** → I'll conduct the interview
- **"Spring AI: Explain RAG to me"** → We start the AI curriculum

Let's go! 💪
