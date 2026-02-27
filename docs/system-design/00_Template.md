# System Design Template
> Use this structure for every design session. Fill it out as we discuss.

---

## Problem Statement
*(What are we designing? One clear sentence.)*

## Step 1 — Clarify Requirements (5 min)

### Functional Requirements
- *(What must the system DO? Core features only.)*

### Non-Functional Requirements
- **Scale:** X requests/sec, Y users, Z data size
- **Latency:** p99 < X ms
- **Availability:** 99.9% / 99.99%
- **Consistency:** Strong / Eventual
- **Durability:** Data must survive X failures

### Out of Scope
- *(What are we explicitly NOT building?)*

---

## Step 2 — Capacity Estimation (5 min)

| Metric | Estimate |
|--------|----------|
| Daily Active Users | |
| Requests per second (peak) | |
| Read : Write ratio | |
| Data stored per day | |
| Total data (5 years) | |
| Bandwidth (ingress/egress) | |

---

## Step 3 — High-Level Design (10 min)

```
[Client] → [Load Balancer] → [API Gateway] → [Services] → [DB/Cache]
```

*(Draw the boxes and arrows here as ASCII or describe components)*

### Core Components
1. **Client / API Layer:** 
2. **Core Service(s):**
3. **Data Store:**
4. **Cache:**
5. **Message Queue (if async):**
6. **CDN (if static content):**

---

## Step 4 — Data Model (5 min)

### Primary Tables / Collections
```
Table: <name>
- id (PK)
- field1
- field2
- created_at
- Indexes: (field1), (field2, field3)
```

### DB Choice & Justification
- **Chosen:** SQL / NoSQL / NewSQL
- **Why:** *(Justify based on access patterns, scale, consistency needs)*

---

## Step 5 — Deep Dive Key Components (15 min)

### Component 1: *(Most critical/interesting part)*
*(Explain the detailed design — algorithms, data structures, logic)*

### Component 2: *(Second most interesting)*
*(Detail)*

---

## Step 6 — Scale & Bottlenecks (10 min)

| Bottleneck | Solution |
|------------|----------|
| DB read overload | Add read replicas + Redis cache |
| Single region failure | Multi-region active-active / active-passive |
| Hot partition / hot key | Consistent hashing, key salting |
| Service overload | Horizontal scaling + load balancer |

---

## Step 7 — Trade-offs Discussed

| Decision | Option A | Option B | We Chose | Why |
|----------|----------|----------|----------|-----|
| | | | | |

---

## Step 8 — Interview Q&A Log
*(Record all follow-up questions asked during the mock and answers given)*

**Q:**  
**A:**  

---

## Key Learnings from This Design
- *(What was the hardest part?)*
- *(What would you do differently next time?)*
- *(What new concept did you learn?)*
