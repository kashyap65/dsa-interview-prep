package com.kashyap.dsa.lld.urlshortener;

/**
 * LLD   : URL Shortener Service
 * DATE  : Apr 4, 2026
 *
 * DESIGN DECISIONS:
 * → Dependency Inversion: depends on interfaces not implementations
 *   CodeGenerator can swap Base62 ↔ PreGeneratedPool (Strategy pattern)
 * → Cache Aside: check cache first, DB on miss, write back to cache
 * → Single Responsibility: this class only orchestrates
 *   Generation → CodeGenerator
 *   Persistence → URLRepository
 *   Caching → CacheService
 *
 * SOLID APPLIED:
 * S → URLShortenerService only orchestrates — no DB/cache logic here
 * O → New CodeGenerator strategy = new class, not editing this
 * D → Depends on interfaces (URLRepository, CacheService, CodeGenerator)
 *     NOT on DynamoDB, Redis, Base62 classes directly
 */
public class URLShortenerService {

    private final URLRepository urlRepository;
    private final CacheService  cacheService;
    private final CodeGenerator codeGenerator;

    // Dependency Injection via constructor
    public URLShortenerService(URLRepository urlRepository,
                               CacheService  cacheService,
                               CodeGenerator codeGenerator) {
        this.urlRepository = urlRepository;
        this.cacheService  = cacheService;
        this.codeGenerator = codeGenerator;
    }

    public String shorten(String longUrl, String userId) {
        // 1. generate unique short code
        String shortCode = codeGenerator.generate(longUrl);

        // 2. save mapping to DB
        urlRepository.save(shortCode, longUrl, userId);

        // 3. return full short URL
        return "https://bit.ly/" + shortCode;
    }

    public String redirect(String shortCode) throws Exception {
        // 1. check cache first (Cache Aside)
        String longUrl = cacheService.get(shortCode);
        if (longUrl != null) return longUrl;       // cache HIT

        // 2. cache MISS → query DB
        longUrl = urlRepository.findByShortCode(shortCode);
        if (longUrl == null)
            throw new Exception(shortCode); // 404

        // 3. write back to cache for next time
        cacheService.put(shortCode, longUrl);

        // 4. return long URL → caller does HTTP 302
        return longUrl;
    }
}

// ── Supporting interfaces ──────────────────────────────────

interface CodeGenerator {
    String generate(String longUrl);  // Strategy pattern
}

interface URLRepository {
    void   save(String shortCode, String longUrl, String userId);
    String findByShortCode(String shortCode);
}

interface CacheService {
    String get(String key);
    void   put(String key, String value);
}