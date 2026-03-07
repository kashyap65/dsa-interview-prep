# 🧠 The 3 Questions That Drive Every Design

Before touching any component in a system design interview, ask these 3 questions. They force you to think at Principal level.

```
Question 1: "What is the READ to WRITE ratio?"
─────────────────────────────────────────────────
Instagram feed → 100 reads per 1 write
  → Heavy cache. Optimise for reads.

Payment transaction → 1 read per 1 write
  → Strong consistency. Optimise for accuracy.

Logging system → 1 read per 1000 writes
  → Optimise for write throughput. Append-only storage.


Question 2: "Does the user need to WAIT for this?"
─────────────────────────────────────────────────
Posting a tweet → User needs confirmation → Synchronous
Sending notification → User doesn't wait → Message Queue
Updating analytics → Nobody waits → Background job


Question 3: "What breaks FIRST under 10x load?"
─────────────────────────────────────────────────
Usually it's the database.
Next is the application server.
Cache and message queues scale easily.
This tells you where to invest in scaling first.
```

---

# 🎬 The Mental Movie — Your Chai Shop

Imagine you open a chai shop. Day 1 — everything works perfectly.

```
Customer → You (take order) → Kitchen (make chai) → Customer gets chai
```

Simple. Fast. No problems.

Now imagine your shop goes viral on Instagram. **10x more customers suddenly show up.**

Let's see what breaks — and in what order.

---

## 🔴 What Breaks First — The Kitchen (Database)

```
You (the waiter) are fast.           ← Application Server
The counter is fine.                 ← Cache / Queue
But the KITCHEN has only 1 stove.   ← Database
```

10x customers. Same 1 stove. Same 1 chef.

```
Normal day:   10 customers → kitchen makes 10 chais → no queue
10x day:      100 customers → kitchen can only make 10/hour
              → 90 customers waiting
              → queue builds up
              → shop effectively stops working
```

**The kitchen is your bottleneck — not the waiter, not the counter.**

This is exactly what happens to a database under 10x load.

---

## 🧠 Why the Database Breaks First — Technically

```
Your system under normal load:

User Request
    ↓
App Server (handles request in RAM — very fast, no bottleneck)
    ↓
Database (reads/writes to DISK — this is the slow part)
    ↓
Response back to user
```

Now 10x traffic hits:

```
App Server:
  → Runs in RAM (memory)
  → Just executes code
  → Add more servers in minutes (horizontal scaling — easy)
  → NOT the bottleneck

Cache:
  → Runs in RAM
  → Just key-value lookups
  → Scales horizontally very easily
  → NOT the bottleneck

Message Queue:
  → Just appends messages to a log
  → Designed to handle massive throughput
  → NOT the bottleneck

Database:
  → Must write to DISK (permanent storage)
  → Must maintain ACID guarantees (no shortcuts)
  → Must manage locks (two users can't update same row simultaneously)
  → Must handle complex queries (joins, aggregations)
  → All of this requires real I/O, real CPU, real memory
  → THIS is the bottleneck
```

---

## 📊 The Actual Numbers — Why DB Hits the Wall First

```
Component        | Handles comfortably | Why it's limited
─────────────────────────────────────────────────────────────
App Server       | 10,000 req/sec      | Just runs code in RAM
                 |                     | Add more servers = solved
─────────────────────────────────────────────────────────────
Cache (Redis)    | 100,000 req/sec     | Pure RAM operations
                 |                     | Designed for this exact purpose
─────────────────────────────────────────────────────────────
Message Queue    | 1,000,000 msg/sec   | Append-only log — trivially fast
(Kafka)          |                     | Designed for massive throughput
─────────────────────────────────────────────────────────────
Database         | 1,000-5,000 req/sec | Disk I/O + locking + ACID
(PostgreSQL)     |                     | Cannot simply add more servers
                 |                     | This hits the wall FIRST
```

**The database saturates at 1,000-5,000 queries/sec on a typical server.**
Everything else can handle 10x-100x more than that.

---

## 🏗️ Why Is DB Hard to Scale — But App Servers Are Easy?

```
SCALING APP SERVERS — Easy ✅

Normal load: 1 app server
10x load:    10 app servers behind a load balancer

Every app server is IDENTICAL.
Stateless — they don't store anything.
Load balancer just spreads requests across them.
Done in minutes.


SCALING DATABASE — Hard ❌

Normal load: 1 database
10x load:    You can't just add 10 databases.

WHY?
  → Data must stay CONSISTENT across all DB nodes
  → If User A updates their balance on DB1
     and User B reads it from DB2 — they might
     see stale data. That's a bug.
  → Coordinating data across multiple DBs is a
     complex distributed systems problem.
  → This is why DB is the hard bottleneck.
```

---

## 💡 What Breaks SECOND — Application Server

After you fix the DB (by adding caching, read replicas, sharding) — the app server becomes the next bottleneck.

```
WHEN does App Server break?
  → When each request becomes expensive
  → Complex business logic
  → Large payloads to process
  → Memory-intensive operations

FIX: Add more app servers behind load balancer.
     (Much easier than fixing DB.)
```

---

## 🎯 The Full Picture — Order of Breaking

```
10x load hits your system:

Step 1: DB saturates first
        → Queries pile up → responses slow down → timeouts
        FIX: Add cache → reduces DB load by 80%
             Add read replicas → spreads read queries

Step 2: App servers struggle next
        → Too many concurrent connections → memory pressure
        FIX: Add more app servers → load balancer distributes

Step 3: Cache might become hot
        → Very specific keys hammered by everyone (celebrity post)
        FIX: Cache replication, local in-memory cache on app server

Step 4: Network / bandwidth
        → Pure volume of data in/out
        FIX: CDN offloads static content, compression

Step 5: Message queues — rarely break
        → They just grow their backlog
        → Add more consumers to drain the queue faster
```

---

## 🔑 Why This Matters in an Interview

When an interviewer asks **"How would you scale this system?"** — most candidates say random things like *"add more servers"* or *"use microservices"*.

A Principal Engineer says:

> *"At 10x load, the database will hit its I/O ceiling first — typically around 5,000 queries per second on a single PostgreSQL instance. My first action is to add Redis caching to absorb the read traffic — this immediately reduces DB load by 70-80%. Second, I add read replicas for the remaining read queries. Writes still go to the primary DB — but now it only handles writes, which are typically 1-5% of total traffic. Application servers are stateless so horizontal scaling there is trivial — I just add instances behind the load balancer."*

**Same answer. Completely different signal. That's what these numbers give you.**

---

## 🧠 The One-Line Memory Anchor

> **"Disk is slow. RAM is fast. Database lives on disk. Everything else lives in RAM. Therefore — database breaks first."**

Every time you hear "10x load" in an interview — your brain should immediately go:

```
10x load
  → Database I/O saturates first
  → Fix with cache + read replicas
  → Then worry about app servers
  → Queues almost never break
```

---

Does this make it clear now Kashyap? The key insight is that **disk I/O is the physical constraint** — everything else runs in RAM and scales horizontally with ease. The database is the only component that is genuinely hard to scale because of the consistency requirement + disk dependency. 💪