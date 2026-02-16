# System Design Cheat Sheet

## 🎯 Interview Framework (Use Every Time)

1. **Clarify Requirements** (5 min)
   - Functional: What features? (read, write, search, etc.)
   - Non-functional: Scale? Latency? Consistency? Availability?
   - Users? Traffic? Data size?

2. **Back-of-Envelope Calculations** (5 min)
   - QPS (Queries Per Second) = Daily Active Users × Avg queries per user / 86400
   - Storage = Items × Size per item × Retention period
   - Bandwidth = QPS × Avg request/response size

3. **High-Level Design** (10 min)
   - Draw boxes: Client → Load Balancer → App Servers → Database → Cache
   - Identify data flow

4. **Deep Dive** (15 min)
   - Pick 2-3 components to detail (database schema, caching strategy, etc.)
   - Discuss trade-offs

5. **Scale & Optimize** (5 min)
   - Bottlenecks? How to handle 10x traffic?

---

## 📊 CAP Theorem

**Pick 2 of 3:**
- **C**onsistency: All nodes see the same data at the same time
- **A**vailability: Every request gets a response (success or failure)
- **P**artition Tolerance: System continues despite network splits

**Common Choices:**
- CP: Banking systems (MongoDB, HBase)
- AP: Social media feeds (Cassandra, DynamoDB)
- CA: Traditional RDBMS (not realistic in distributed systems)

---

## ⚖️ Load Balancing

**Algorithms:**
- **Round Robin**: Distribute evenly (simple, no server state)
- **Least Connections**: Send to server with fewest active connections
- **IP Hash**: Same user → same server (session persistence)
- **Weighted Round Robin**: Distribute based on server capacity

**Layers:**
- L4 (Transport): Fast, IP/port-based
- L7 (Application): Slower, can route based on URL/headers

---

## 💾 Caching Strategies

**Cache Invalidation:**
- **Write-through**: Write to cache + DB simultaneously (slower writes, always consistent)
- **Write-back**: Write to cache, async to DB (fast writes, risk of data loss)
- **Write-around**: Write to DB, cache on read (avoids cache pollution)

**Eviction Policies:**
- **LRU** (Least Recently Used): Most common
- **LFU** (Least Frequently Used): Good for consistent access patterns
- **FIFO**: Simplest, rarely used

**CDN**: Cache static content (images, videos, CSS/JS) close to users

---

## 🗄️ Database Design

### SQL vs NoSQL

| SQL | NoSQL |
|-----|-------|
| ACID guarantees | Eventual consistency |
| Fixed schema | Flexible schema |
| Vertical scaling | Horizontal scaling |
| Relational data | Denormalized data |
| **Use:** Banking, transactions | **Use:** Social graphs, logs, big data |

### Scaling Strategies

**Replication:**
- **Master-Slave**: Writes to master, reads from slaves (read-heavy systems)
- **Master-Master**: Multiple masters (conflict resolution needed)

**Sharding** (Horizontal Partitioning):
- **Hash-based**: `shard = hash(user_id) % num_shards` (even distribution)
- **Range-based**: `user_id 1-1M → Shard 1` (easy range queries, risk of hotspots)
- **Directory-based**: Lookup table maps keys to shards (flexible, single point of failure)

**Indexing:**
- B-tree: Range queries, sorted data
- Hash: Fast equality lookups
- Trade-off: Faster reads, slower writes

---

## 📨 Message Queues & Async Processing

**When to Use:**
- Decouple services (sender doesn't wait for receiver)
- Handle traffic spikes (buffer requests)
- Retry failed operations

**Patterns:**
- **Pub/Sub**: 1 publisher → N subscribers (Kafka, RabbitMQ)
- **Point-to-Point**: 1 sender → 1 receiver (SQS)

**Kafka Basics:**
- Topics: Categories of messages
- Partitions: Parallelism (each consumer reads from partition)
- Consumer Groups: Scale consumers

---

## 🚦 Rate Limiting

**Algorithms:**

1. **Token Bucket**
   ```
   Every second: add N tokens to bucket (max capacity)
   On request: consume 1 token
   If no tokens: reject request
   ```
   ✅ Handles bursts

2. **Leaky Bucket**
   ```
   Requests enter queue at any rate
   Process at fixed rate
   If queue full: reject
   ```
   ✅ Smooth traffic

3. **Fixed Window**
   ```
   Allow N requests per minute
   Reset counter every minute
   ```
   ❌ Burst at window boundaries

4. **Sliding Window Log**
   ```
   Track timestamp of each request
   Count requests in last 60 seconds
   ```
   ✅ Most accurate, 💾 memory-intensive

---

## 🔍 Common System Design Questions

### URL Shortener (Bit.ly)
- **Functional**: Shorten URL, redirect to original
- **Key Decision**: How to generate short URL?
  - Base62 encoding of auto-increment ID (predictable)
  - Hash + collision handling (random)
- **Scale**: 1B URLs, 100:1 read:write ratio
- **Cache**: Popular URLs in Redis
- **DB**: NoSQL (key-value store like DynamoDB)

### Instagram Feed
- **Functional**: Post images, follow users, view feed
- **Key Decision**: Feed generation?
  - **Push (fanout-on-write)**: Pre-compute feed when user posts (fast reads, slow writes for celebrities)
  - **Pull (fanout-on-read)**: Compute feed when user requests (slow reads, fast writes)
  - **Hybrid**: Push for most, pull for celebrities
- **Storage**: S3 for images, Cassandra for posts/social graph
- **Cache**: Redis for hot feeds

### Design Twitter
- **Functional**: Tweet, follow, timeline
- **Key Components**:
  - User Service (profiles, followers)
  - Tweet Service (create, read tweets)
  - Timeline Service (home timeline, user timeline)
  - Fanout Service (push tweets to followers' timelines)
- **DB**: Tweets in Cassandra (time-series data), Redis for timelines
- **Scale**: Read-heavy (cache aggressively)

### Design YouTube
- **Functional**: Upload video, stream video
- **Key Decisions**:
  - Video processing: Transcode to multiple resolutions (queue-based)
  - Storage: Blob storage (S3/GCS) for raw videos
  - CDN: Cache popular videos globally
  - Metadata: SQL (video title, uploader, views)
- **Scale**: Write-heavy during upload, read-heavy during streaming

---

## 📐 Back-of-Envelope Math

**Common Numbers to Remember:**
- 1 million = 10^6
- 1 billion = 10^9
- 1 day = 86,400 seconds ≈ 10^5 seconds
- 1 char = 1 byte
- UUID = 36 bytes
- Timestamp = 8 bytes

**Example: Twitter**
- 300M daily active users
- Each user tweets 2 times/day, views 100 tweets/day
- **Write QPS**: 300M × 2 / 86400 ≈ 7,000 QPS
- **Read QPS**: 300M × 100 / 86400 ≈ 350,000 QPS
- **Storage (5 years)**: 300M × 2 × 365 × 5 × 200 bytes ≈ 200 TB

---

## 🎨 Quick Diagram Template

```
[Client/Browser]
       |
       v
[Load Balancer]
       |
   ----+----
   |       |
   v       v
[App     [App
Server]  Server]
   |       |
   ----+----
       |
       v
   [Cache (Redis)]
       |
       v
   [Database]
       |
   [Replica]
```

---

## ✅ Key Trade-offs to Mention

- **Consistency vs Availability**: Strong consistency = slower, eventual consistency = faster
- **SQL vs NoSQL**: Structured data + ACID vs scalability + flexibility
- **Normalization vs Denormalization**: Less redundancy vs faster reads
- **Caching**: Faster reads vs stale data
- **Vertical vs Horizontal Scaling**: Simpler vs more scalable
- **Sync vs Async**: Immediate feedback vs better performance

---

## 💡 Pro Tips

1. **Always ask clarifying questions first** (don't jump into design)
2. **Talk through your thought process** (interviewer wants to see how you think)
3. **Start simple, then optimize** (don't over-engineer upfront)
4. **Acknowledge trade-offs** (there's no perfect solution)
5. **Use numbers** (back-of-envelope estimates show you understand scale)
6. **Draw diagrams** (visual >>> verbal)
7. **Time management**: Don't get stuck on one component

Good luck! 🚀
