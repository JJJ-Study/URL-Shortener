package com.f1v3.urlshortener.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * {class name}.
 *
 * @author 정승조
 * @version 2024. 12. 09.
 */
@Entity
@Table(name = "url")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortUrl;

    private String originalUrl;

    @Builder
    public Url(String shortUrl, String originalUrl) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
    }

    public void updateShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
