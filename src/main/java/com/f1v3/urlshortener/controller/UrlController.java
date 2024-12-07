package com.f1v3.urlshortener.controller;

import com.f1v3.urlshortener.dto.ShortenRequest;
import com.f1v3.urlshortener.dto.ShortenResponse;
import com.f1v3.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author 정승조
 * @version 2024. 12. 06.
 */
@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/api/v1/data/shorten")
    public ResponseEntity<ShortenResponse> createShortUrl(@RequestBody ShortenRequest request) {

        System.out.println("===================");
        System.out.println(request.getOriginalUrl());

        ShortenResponse response = urlService.shortenUrl(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrl(shortUrl);

        return ResponseEntity.status(302)
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }

}
