package com.f1v3.urlshortener.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 정승조
 * @version 2024. 12. 09.
 */
@Configuration
public class CacheConfig {

    @Bean
    public List<CaffeineCache> caffeineConfig() {
        return Arrays.stream(
                        CacheType.values())
                .map(cache -> new CaffeineCache(
                                cache.getUrl(),
                                Caffeine.newBuilder()
                                        .recordStats()
                                        .expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.SECONDS)
                                        .maximumSize(cache.getMaximumSize())
                                        .build()
                        )
                ).toList();
    }

    @Bean
    public CacheManager cacheManager(List<CaffeineCache> caffeineCaches) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caffeineCaches);
        return cacheManager;
    }

}
