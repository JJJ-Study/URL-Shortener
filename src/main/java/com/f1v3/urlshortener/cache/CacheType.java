package com.f1v3.urlshortener.cache;

import lombok.Getter;

/**
 * @author 정승조
 * @version 2024. 12. 09.
 */
@Getter
public enum CacheType {

    URL_CACHE("urlCache");

    private final String url;
    private final int expireAfterWrite;
    private final int maximumSize;

    CacheType(String urlCache) {
        this.url = urlCache;
        this.expireAfterWrite = 60 * 5;
        this.maximumSize = 10_000;
    }
}
