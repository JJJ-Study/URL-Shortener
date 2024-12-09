package com.f1v3.urlshortener.serivce;

import com.f1v3.urlshortener.domain.Url;
import com.f1v3.urlshortener.dto.ShortenRequest;
import com.f1v3.urlshortener.dto.ShortenResponse;
import com.f1v3.urlshortener.repository.UrlRepository;
import com.f1v3.urlshortener.util.Base62Conversion;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public ShortenResponse shortenUrl(ShortenRequest request) {

        String originalUrl = request.getOriginalUrl();

        if (urlRepository.existsByOriginalUrl(originalUrl)) {
            Url url = urlRepository.findByOriginalUrl(originalUrl)
                    .orElseThrow(IllegalArgumentException::new);

            return new ShortenResponse(url.getShortUrl());
        }

        Url url = Url.builder()
                .originalUrl(originalUrl)
                .build();

        Url savedUrl = urlRepository.save(url);

        String shortenUrl = conversion.encode(savedUrl.getId());
        savedUrl.updateShortUrl(shortenUrl);
        urlRepository.save(savedUrl);

        return new ShortenResponse(shortenUrl);
    }

    @Transactional(readOnly = true)
    public String getOriginalUrl(String shortUrl) {
        long urlId = conversion.decode(shortUrl);

        Url url = urlRepository.findById(urlId)
                .orElseThrow(IllegalArgumentException::new);

        return url.getOriginalUrl();
    }
}
