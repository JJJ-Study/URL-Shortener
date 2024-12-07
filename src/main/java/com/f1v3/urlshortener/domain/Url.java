package com.f1v3.urlshortener.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 정승조
 * @version 2024. 12. 06.
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
    public Url(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setShortUrl(String shortenUrl) {
        this.shortUrl = shortenUrl;
    }
}
