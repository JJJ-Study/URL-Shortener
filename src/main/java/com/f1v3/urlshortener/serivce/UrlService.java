package com.f1v3.urlshortener.serivce;

import com.f1v3.urlshortener.domain.Url;
import com.f1v3.urlshortener.dto.ShortenRequest;
import com.f1v3.urlshortener.dto.ShortenResponse;
import com.f1v3.urlshortener.repository.UrlRepository;
import com.f1v3.urlshortener.util.Base62Conversion;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 정승조
 * @version 2024. 12. 09.
 */
@Service
@RequiredArgsConstructor
public class UrlService {

    private final Base62Conversion conversion;
    private final UrlRepository urlRepository;
    private final CacheManager cacheManager;

    @Transactional
    public ShortenResponse shortenUrl(ShortenRequest request) {

        String originalUrl = request.getOriginalUrl();

        if (urlRepository.existsByOriginalUrl(originalUrl)) {
            Url url = urlRepository.findByOriginalUrl(originalUrl)
                    .orElseThrow(IllegalArgumentException::new);

            Cache urlCache = cacheManager.getCache("urlCache");
            if (urlCache != null) {
                urlCache.put(url.getShortUrl(), url.getOriginalUrl());
            }

            return new ShortenResponse(url.getShortUrl());
        }

        Url url = Url.builder()
                .originalUrl(originalUrl)
                .build();

        Url savedUrl = urlRepository.save(url);

        String shortenUrl = conversion.encode(savedUrl.getId());
        savedUrl.updateShortUrl(shortenUrl);
        urlRepository.save(savedUrl);

        Cache urlCache = cacheManager.getCache("urlCache");
        if (urlCache != null) {
            urlCache.put(shortenUrl, originalUrl);
        }

        return new ShortenResponse(shortenUrl);
    }

    @Transactional(readOnly = true)
    public String getOriginalUrl(String shortUrl) {
        Cache urlCache = cacheManager.getCache("urlCache");
        String cachedUrl = null;

        if (urlCache.get(shortUrl) != null) {
            cachedUrl = urlCache.get(shortUrl).get().toString();
        }

        if (cachedUrl != null) {
            return cachedUrl;
        }

        long urlId = conversion.decode(shortUrl);
        Url url = urlRepository.findById(urlId)
                .orElseThrow(IllegalArgumentException::new);

        return url.getOriginalUrl();
    }
}
