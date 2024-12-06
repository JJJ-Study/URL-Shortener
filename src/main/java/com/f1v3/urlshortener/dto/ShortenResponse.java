package com.f1v3.urlshortener.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author 정승조
 * @version 2024. 12. 06.
 */
@Getter
public class ShortenResponse {

    private String shortUrl;

    public ShortenResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
